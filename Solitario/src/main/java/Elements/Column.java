package Elements;

import Base.Deck;

import java.util.ArrayList;

import Base.Card;

public class Column extends Deck {

        public Card getCard(int pos){
                return deck.get(pos);
        }

        public Column getSequence(int upToIndex) {
                if(!getCard(upToIndex).isFaceUp()) return null;
                Column subColumn = new Column();
                if (!subColumn.addCards(deck.subList(0, upToIndex))) return null;
                return subColumn;
        }

        @Override
        public Card drawCard() {
                Card drawn = super.drawCard();
                if(!isEmpty() && !getLast().isFaceUp()) getLast().flip();
                return drawn;
        }

        public boolean removeSequence(Column sequence) {
                for (int i = 0; i < sequence.cardCount(); i++) {
                        if(!deck.get(0).isTheSameAs(sequence.getCard(i))) {
                                return false;
                        }
                        drawCard();
                }
                if(!isEmpty() && !getLast().isFaceUp()) getLast().flip();
                return true;
        }

        public boolean addCards(Column cards) {
                if (cards == null) return false;
                ArrayList<Card> cardsCollection = new ArrayList<>();
                for (int i = cards.cardCount() - 1; i >= 0;  i--) {
                        cardsCollection.add(0, cards.getCard(i));
                }
                return addCards(cardsCollection);
        }

}
