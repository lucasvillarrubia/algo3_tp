package Visitor;

public class SequenceChecker implements RuleChecker {
        
        @Override
        public boolean checkRule(Stock stock) {
                return true;
        }

        @Override
        public boolean checkRule(Column column) {
                return true;
        }
        
        @Override
        public boolean checkRule(Foundation foundation) {
                return true;
        }

}
