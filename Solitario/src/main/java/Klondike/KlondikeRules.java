package Klondike;

import Base.Card;
import Base.Color;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Solitaire.Rules;

public class KlondikeRules implements Rules {
        public boolean acceptsCard(Stock stock, Card card) {
                return !stock.contains(card) && !stock.wasFilled();
        }
        public boolean acceptsCard(Foundation foundation, Card card) {
                if(foundation.isEmpty()){
                        return (card.getValue() == Value.ACE && card.getSuit() == foundation.getSuit());
                } else {
                        Card topCard = foundation.getLast();
                        return (card.getSuit()==foundation.getSuit() && card.getValue().getNumber() == topCard.getValue().getNumber() + 1);
                }
        }
        public boolean acceptsCard(Column column, Card card) {
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
        public boolean givesCard(Stock stock) { return true; }
        public boolean givesCard(Foundation foundation) { return true; }
        public boolean givesCard(Column column) { return true; }
        public boolean admitsSequence(Stock stock) { return false; }
        public boolean admitsSequence(Foundation foundation) { return false; }
        public boolean admitsSequence(Column column) { return true; }
        public boolean checkGameStatus(Game game) { return true; }
        public void gameInit(Game game) {
                game.setStock(initStock());
        }

        public Stock initStock() {
                Stock stock = new Stock();
                for (Value value : Value.values()) {
                        for (Suit suit : Suit.values()) {
                                Card card = new Card(suit, value);
                                stock.acceptCard(this, card);
                        }
                }
                return stock;
        }

}



