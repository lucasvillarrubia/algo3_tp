package Spider;

import Elements.Column;
import Elements.Foundation;
import Elements.Stock;
import Solitaire.RuleChecker;

public class RemovingChecker implements RuleChecker {
        
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