package Spider;

import Base.Card;
import Base.Color;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Stock;
import Solitaire.RuleChecker;

public class AddingChecker implements RuleChecker {

        @Override
        public boolean checkRule(Stock stock) {
            return false;
        }

        @Override
        public boolean checkRule(Column column) {
                return false;
        }
        
        @Override
        public boolean checkRule(Foundation foundation) {
               return false;
        }
        
}
