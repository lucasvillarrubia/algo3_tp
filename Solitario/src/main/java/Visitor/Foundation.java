package Visitor;

import Base.Suit;
import Base.Value;

public class Foundation extends Deck implements Addeable {

        private Suit suit;

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
        public boolean acceptCard(DeckVisitor cardAdder) {
                return cardAdder.checkCard(this, getLast());
        }

}
