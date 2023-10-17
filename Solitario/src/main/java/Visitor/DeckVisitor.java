package Visitor;

import Base.Card;

public interface DeckVisitor {
        public boolean checkCard(Stock stock, Card card);
        public boolean checkCard(Column column, Card card);
        public boolean checkCard(Foundation foundation, Card card);
}
