package Klondike;

import java.util.ArrayList;
import java.util.List;

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

        private static final int AMOUNT_COLUMNS = 7;
        private static final String RULES_TYPE = "KLONDIKE";

        @Override
        public String getRulesType() { return RULES_TYPE; }

        public boolean isSequenceValid(Card prev, Card next) {
                int prevValue = prev.getNumber();
                int nextValue = next.getNumber();
                Color prevColor = prev.getColor();
                Color nextColor = next.getColor();
                boolean precedingValue = ((prevValue - nextValue) == 1);
                boolean sameColor = (prevColor == nextColor);
                return !sameColor && precedingValue;
        }

        @Override
        public boolean acceptsCard(Stock stock, Card card) {
                return !stock.wasFilled();
        }

        @Override
        public boolean acceptsCard(Foundation foundation, Card card) {
                if(foundation.isEmpty()){
                        return (card.getValue() == Value.ACE && card.getSuit() == foundation.getSuit());
                } else {
                        Card topCard = foundation.getLast();
                        return (card.getSuit()==foundation.getSuit() && card.getValue().getNumber() == topCard.getValue().getNumber() + 1);
                }
        }

        @Override
        public boolean acceptsCard(Column column, Card card) {
                if (column.isBeingFilled()) {
                        return true;
                }
                else if (column.isEmpty()) {
                        return card.getValue() == Value.KING;
                } else {
                        return isSequenceValid(column.getLast(), card);
                }
        }

        @Override
        public boolean givesCard(Stock stock) {
                return true;
        }

        @Override
        public boolean givesCard(Foundation foundation) {
                return true;
        }

        @Override
        public boolean givesCard(Column column) {
                return true;
        }

        @Override
        public boolean admitsSequence(Stock stock, Column sequence) {
                return false;
        }

        @Override
        public boolean admitsSequence(Foundation foundation, Column sequence) {
                return false;
        }

        @Override
        public boolean admitsSequence(Column column, Column sequence) {
                if (!acceptsCard(column, sequence.getCard(sequence.cardCount()-1)));
                for (int i = sequence.cardCount() - 1; i > 0; i--) {
                        if (!isSequenceValid(sequence.getCard(i), sequence.getCard(i-1))) return false;
                }
                return true;
        }

        @Override
        public boolean checkGameStatus(Game game) {
                return game.gameStatus();
        }

        @Override
        public void gameInit(Game game) {
                Stock gameStock = initStock();
                game.setStock(gameStock);
                game.setTableau(initTableau(gameStock));
                game.setFoundations(initFoundations());
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

        public List<Column> initTableau(Stock stock) {
                ArrayList<Column> tableau = new ArrayList<>();
                for (int i = 0; i < AMOUNT_COLUMNS; i++) {
                        tableau.add(new Column());
                }
                for (int i = 0; i < AMOUNT_COLUMNS; i++) {
                        for (int j = 0; j < i + 1; j++) {
                                Card card = stock.drawCard();
                                if (j == i) {
                                        card.flip();
                                }
                                if(!tableau.get(i).acceptCard(this, card)) return null;
                        }
                }
                return tableau;
        }

        public List<Foundation> initFoundations() {
                ArrayList<Foundation> foundations = new ArrayList<>();
                for (Suit suit : Suit.values()) {
                        foundations.add(new Foundation(suit));
                }
                return foundations;
        }

}



