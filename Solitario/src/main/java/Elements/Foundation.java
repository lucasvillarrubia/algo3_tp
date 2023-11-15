package Elements;

import Base.Deck;
import Base.Suit;
import Base.Value;


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

}
