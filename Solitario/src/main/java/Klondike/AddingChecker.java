package Klondike;

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
                Card card = stock.drawCard();
                return !stock.contains(card) && !stock.wasFilled();
        }

        @Override
        public boolean checkRule(Column column) {
                Card card = column.drawCard();
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
        public boolean checkRule(Foundation foundation) {
                Card card = foundation.drawCard();
                if(foundation.isEmpty()){
                        return (card.getValue() == Value.ACE && card.getSuit() == foundation.getSuit());
                } else {
                        Card topCard = foundation.getLast();
                        return (card.getSuit()==foundation.getSuit() && card.getValue().getNumber() == topCard.getValue().getNumber() + 1);
                }
        }
        
}
