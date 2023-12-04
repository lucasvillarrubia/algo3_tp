package GameType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Base.*;
import Elements.*;
import Solitaire.Rules;

public class KlondikeRules implements Rules,Serializable{

        private static final int AMOUNT_COLUMNS = 7;
        private Card waste = null;

        @Override
        public boolean isSequenceValid(Card prev, Card next) {
                if(prev == null || next == null) return false;
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
                return false;
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
                if (column.isEmpty()) {
                        return card.getValue() == Value.KING;
                } else {
                        return isSequenceValid(column.getLast(), card);
                }
        }

        @Override
        public boolean givesCard(Stock stock) {
                return waste != null && !stock.isEmpty();
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
        public boolean admitsSequence(Stock stock, Column sequence) { return false; }

        @Override
        public boolean admitsSequence(Foundation foundation, Column sequence) {
                return false;
        }

        @Override
        public boolean admitsSequence(Column column, Column sequence) {
                if (!acceptsCard(column, sequence.getCard(sequence.cardCount()-1))) { return false; }
                for (int i = sequence.cardCount() - 1; i >0; i--) {
                        if (!isSequenceValid(sequence.getCard(i), sequence.getCard(i-1))) return false;
                }
                return true;
        }

        @Override
        public Stock initStock() {
                Stock stock = new Stock();
                for (Value value : Value.values()) {
                        for (Suit suit : Suit.values()) {
                                Card card = new Card(suit, value);
                                stock.addCards(card);
                        }
                }
                return stock;
        }

        @Override
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
                                if(!tableau.get(i).addCards(card)) return null;
                        }
                }
                return tableau;
        }

        @Override
        public List<Foundation> initFoundations() {
                ArrayList<Foundation> foundations = new ArrayList<>();
                for (Suit suit : Suit.values()) {
                        foundations.add(new Foundation(suit));
                }
                return foundations;
        }

        @Override
        public boolean drawCardFromStock(Stock stock, List<Column> tableau) {
                if (stock == null || tableau == null || stock.isEmpty()) return false;
                if (waste != null) {
                        if (!stock.containsCard(waste)) {
                                stock.showPreviousCard();
                        } else {
                                stock.getLast().flip();
                                stock.showNextCard();
                        }
                }
                stock.getLast().flip();
                waste = stock.getLast();
                return true;
        }


        public SolitaireType getRulesString(){
                return SolitaireType.KLONDIKE;
        }

}