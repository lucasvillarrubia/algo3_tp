package Elements;

import Base.Deck;
import Base.Suit;
import Base.Value;

public class Foundation extends Deck implements Visitable {

        private final Suit suit;

        public Foundation(Suit suit) {
                super();
                this.suit = suit;
        }

        public boolean isFull(){
                if (deck.isEmpty()) return false;
                return getLast().getNumber() == 13 || getLast().getNumber() == 1;
        }

        public Suit getSuit(){
                return this.suit;
        }


        @Override public void accept(DeckVisitor visitor) { visitor.visit(this); }

}
