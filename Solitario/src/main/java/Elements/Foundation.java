package Elements;

import java.util.ArrayList;

import Base.Deck;
import Base.Suit;
import Base.Value;
import Base.Card;


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

        public boolean addCards(Column cards) {
                if (cards == null) return false;
                ArrayList<Card> cardsCollection = new ArrayList<>();
                for (int i = cards.cardCount() - 1; i >= 0;  i--) {
                        cardsCollection.add(0, cards.getCard(i));
                }
                return addCards(cardsCollection);
        }

}
