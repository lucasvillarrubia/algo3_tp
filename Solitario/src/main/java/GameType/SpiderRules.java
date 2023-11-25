package GameType;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Stock;
import Solitaire.Rules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SpiderRules implements Rules, Serializable {

    private static final int AMOUNT_COLUMNS = 10;
    private static final int AMOUNT_CARDS_SHORT = 5;
    private static final int AMOUNT_COLUMNS_LONG = 4;
    private static final int AMOUNT_CARDS_LONG = 6;
    private static final int AMOUNT_FOUNDATIONS = 8;
    private static final int COMPLETE_FOUNDATION = 13;
    private static final Suit SPADES = Suit.SPADES;

    @Override
    public boolean isSequenceValid(Card prev, Card next) {
        int prevValue = prev.getNumber();
        int nextValue = next.getNumber();
        return ((prevValue - nextValue) == 1);
    }

    @Override
    public boolean acceptsCard(Stock stock, Card card) {
        return false;
    }

    @Override
    public boolean acceptsCard(Foundation foundation, Card card) {
        return false;
    }
    @Override
    public boolean acceptsCard(Column column, Card card) {
        if (column.isEmpty()) {
            return true;
        } else {
            Card topCard = column.getLast();
            return (card.getValue().getNumber() == (topCard.getValue().getNumber() - 1));
        }
    }

    @Override
    public boolean givesCard(Stock stock) { return false; }

    @Override
    public boolean givesCard(Foundation foundation) {
        return false;
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
        if (sequence.cardCount() == COMPLETE_FOUNDATION) {
            for (int i = COMPLETE_FOUNDATION -1 ; i > 0; i--){
                if (!isSequenceValid(sequence.getCard(i), sequence.getCard(i-1))) return false;
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean admitsSequence(Column column, Column sequence) {
        if (!acceptsCard(column, sequence.getCard(sequence.cardCount()-1))) { return false; }
        for (int i = sequence.cardCount() - 1; i > 0; i--) {
            if (!isSequenceValid(sequence.getCard(i), sequence.getCard(i - 1))) return false;
        }
        return true;
    }


    @Override
    public Stock initStock() {
       Stock stock = new Stock();
        for (Value value : Value.values()) {
            for (int j = 0; j < AMOUNT_FOUNDATIONS; j++) {
                Card card = new Card(SPADES, value);
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
        for (int i = 0; i < AMOUNT_COLUMNS_LONG; i++) {
            for (int j = 0; j < AMOUNT_CARDS_LONG; j++) {
                Card card = stock.drawCard();
                if (j == AMOUNT_CARDS_LONG-1) {
                    card.flip();
                }
                if(!tableau.get(i).addCards(card)) return null;
            }
        }
        for (int i = AMOUNT_COLUMNS_LONG; i < AMOUNT_COLUMNS; i++) {
            for (int j = 0; j < AMOUNT_CARDS_SHORT; j++) {
                Card card = stock.drawCard();
                if (j == AMOUNT_CARDS_SHORT-1) {
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
        for (int j = 0; j < AMOUNT_FOUNDATIONS; j++) {
            foundations.add(new Foundation(SPADES));
        }
        return foundations;
    }

    public boolean drawCardFromStock (Stock stock, List<Column> tableau) {
        if(stock.isEmpty()) return  false;
        for (int i = 0; i < AMOUNT_COLUMNS; i++) {
            if (tableau.get(i).isEmpty()){
                return false;
            }
        }
        for (int j = 0; j < AMOUNT_COLUMNS; j++) {
            Card card = stock.drawCard();
            tableau.get(j).addCards(card);
            tableau.get(j).getLast().flip();
        }
        return true;
    }

    public String getRulesString(){
        return "Spider";
    }
}
