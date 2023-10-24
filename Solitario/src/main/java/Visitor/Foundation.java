package Visitor;

import Base.Suit;
import Base.Value;
import Base.Card;

public class Foundation extends Deck implements RuleAcceptance {

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
        public boolean acceptsCard(RuleChecker addingChecker, Card card) {
                return this.addCards(card) && addingChecker.checkRule(this);
        }

        @Override
        public boolean canDrawCard(RuleChecker removingChecker) {
                return removingChecker.checkRule(this);
        }
        
        @Override
        public boolean admitsSequence(RuleChecker sequenceChecker) {
                return sequenceChecker.checkRule(this);
        }

}
