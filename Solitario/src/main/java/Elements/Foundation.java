package Elements;

import Base.Suit;
import Base.Value;

public class Foundation extends VisitableDeck {


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


        @Override public void accept(DeckVisitor visitor) { visitor.visit(this); }

}
