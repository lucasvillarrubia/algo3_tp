package Visitor;

import Base.Card;

public interface RuleAcceptance {
        public boolean canDrawCard(RuleChecker cardSender);
        // isSequenceValid está dentro de admitsSequence
        public boolean admitsSequence(RuleChecker sequenceChecker);
        public boolean acceptsCard(RuleChecker cardAdder, Card card);

}
