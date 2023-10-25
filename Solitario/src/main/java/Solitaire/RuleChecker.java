package Solitaire;

import Elements.Column;
import Elements.Foundation;
import Elements.Stock;

public interface RuleChecker {
        public boolean checkRule(Stock stock);
        public boolean checkRule(Column column);
        public boolean checkRule(Foundation foundation);
}
