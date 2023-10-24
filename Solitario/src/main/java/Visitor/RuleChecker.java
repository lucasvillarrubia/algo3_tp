package Visitor;

public interface RuleChecker {
        public boolean checkRule(Stock stock);
        public boolean checkRule(Column column);
        public boolean checkRule(Foundation foundation);
}
