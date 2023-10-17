package Visitor;

import Base.Card;
import Base.Color;
import Base.Value;

public class DeckCardAdder implements DeckVisitor {

        @Override
        public boolean checkCard(Stock stock, Card card) {
                return !stock.contains(card) && !stock.wasFilled();
        }

        @Override
        public boolean checkCard(Column column, Card card) {
                if (column.isEmpty()) {
                        return card.getValue() == Value.KING;
                } else {
                        int lastValue = column.getLast().getValue().getNumber();
                        int receivedValue = card.getValue().getNumber();
                        Color lastColor = column.getLast().getSuit().getColor();
                        Color receivedColor = card.getSuit().getColor();
                        boolean precedingValue = (lastValue - receivedValue) == 1;
                        boolean sameColor = lastColor == receivedColor;
                        return !sameColor && precedingValue;
                }
        }

        @Override
        public boolean checkCard(Foundation foundation, Card card) {
                if(foundation.isEmpty()){
                        return (card.getValue() == Value.ACE && card.getSuit() == foundation.getSuit());
                } else {
                        Card topCard = foundation.getLast();
                        return (card.getSuit()==foundation.getSuit() && card.getValue().getNumber() == topCard.getValue().getNumber() + 1);
                }
        }
        
}
