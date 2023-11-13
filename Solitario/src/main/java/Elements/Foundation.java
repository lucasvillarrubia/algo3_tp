package Elements;

import Base.Deck;
import Base.Suit;
import Base.Value;
import Base.Card;
import java.util.ArrayList;
import java.util.Collection;

public class Foundation extends Deck {

        private final Suit suit;

        public Foundation(Suit suit) {
                super();
                this.suit = suit;
        }

        public boolean isFull(){
                if (deck.isEmpty()) return false;
                return this.getLast().getValue() == Value.KING;
        }

        public Suit getSuit(){
                return this.suit;
        }

        @Override
        public boolean addCards(Card card) {
                return super.addCards(card);
        }

        protected boolean addCards(Column cards) {
                if (cards == null) return false;
                Collection<Card> cardsCollection = new ArrayList<>();
                for (int i = cards.cardCount()-1; i >= 0;  i--) {
                        cardsCollection.add(cards.getCard(i));
                }
                return addCards(cardsCollection);
        }

        private boolean addCards(Collection<Card> cards) {
                if (cards == null) return false;
                deck.addAll(0, cards);
                return true;
        }

}
