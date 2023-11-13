package Elements;

import Base.Deck;
import Base.Card;
import java.util.ArrayList;
import java.util.Collection;

public class Column extends Deck {

        private boolean isFilling;

        public Column () {
                super();
                this.isFilling = true;
        }

        public void toggleFillingState() {
                this.isFilling = !isFilling;
        }

        public boolean isBeingFilled() {
                return this.isFilling;
        }

        public Card getCard(int pos){
                return deck.get(pos);
        }

        public Column getSequence(int upToIndex) {
                if(!getCard(upToIndex).isFaceUp()) return null;
                Column subColumn = new Column();
                subColumn.toggleFillingState();
                if (!subColumn.addCards(deck.subList(0, upToIndex))) return null;
                return subColumn;
        }

        @Override
        public boolean addCards(Card card) {
                return super.addCards(card);
        }

        private boolean addCards(Collection<Card> cards) {
                if (cards == null) return false;
                deck.addAll(0, cards);
                return true;
        }

        protected boolean addCards(Column cards) {
                if (cards == null) return false;
                Collection<Card> cardsCollection = new ArrayList<>();
                for (int i = cards.cardCount() - 1; i >= 0;  i--) {
                        cardsCollection.add(cards.getCard(i));
                }
                return addCards(cardsCollection);
        }

        @Override
        public Card drawCard() {
                Card drawn = super.drawCard();
                if(!isEmpty() && !getLast().isFaceUp()) getLast().flip();
                return drawn;
        }

        public boolean removeSequence(Column cards) {
                for (int i = 0; i < cards.cardCount(); i++) {
                        if(!deck.get(i).isTheSameAs(cards.getCard(i))) {
                                return false;
                        }
                        drawCard();
                }
                if(!isEmpty() && !getLast().isFaceUp()) getLast().flip();
                return true;
        }

}
