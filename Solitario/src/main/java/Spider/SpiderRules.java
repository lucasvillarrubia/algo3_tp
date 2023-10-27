package Spider;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Solitaire.Rules;

import java.util.ArrayList;
import java.util.List;


public class SpiderRules implements Rules {

    private static final String RULES_TYPE = "SPIDER";
    private static final int AMOUNT_COLUMNS = 10;
    private static final int AMOUNT_COLUMNS_SHORT = 6;
    private static final int AMOUNT_CARDS_SHORT = 5;
    private static final int AMOUNT_COLUMNS_LONG = 4;
    private static final int AMOUNT_CARDS_LONG = 6;
    private static final int AMOUNT_FOUNDATIONS = 8;
    private static final int COMPLETE_FOUNDATION =13;
    private static final Suit SPADES = Suit.SPADES;

    @Override
    public String getRulesType() { return RULES_TYPE; }


    public boolean isSequenceValid(Card prev, Card next) {
        int prevValue = prev.getNumber();
        int nextValue = next.getNumber();
        return ((prevValue - nextValue) != 1);
    }

    @Override
    public boolean acceptsCard(Stock stock, Card card) {
        return !stock.wasFilled();
    }

    @Override
    public boolean acceptsCard(Foundation foundation, Card card) {
        return false;
    }

    @Override
    public boolean acceptsCard(Column column, Card card) {
        if (column.isBeingFilled()) {
            return true;
        }
        else if (column.isEmpty()) {
            return card.getValue() == Value.KING;
        } else {
            Card topCard = column.getLast();
            return (card.getValue().getNumber() == topCard.getValue().getNumber() - 1);
        }
    }

    @Override
    public boolean givesCard(Stock stock) {
        //ESTA DUDA
        return true;
    }

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
         return isSequenceComplete(sequence);
    }

    private boolean isSequenceComplete(Column sequence){
        if (sequence.cardCount() == COMPLETE_FOUNDATION) {
            for (int i = 0; i <COMPLETE_FOUNDATION; i++){
                if (!isSequenceValid(sequence.getCard(i), sequence.getCard(i - 1))) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean admitsSequence(Column column, Column sequence) {
        for (int i = sequence.cardCount() - 1; i > 0; i--) {
            if (isSequenceValid(sequence.getCard(i), sequence.getCard(i - 1))) return false;
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
            for (int j = 0; j < AMOUNT_FOUNDATIONS; j++) {
                Card card = new Card(SPADES, value);
                stock.acceptCard(this, card);
            }
        }
        return stock;
    }


    public List<Column> initTableau(Stock stock) {
        ArrayList<Column> tableau = new ArrayList<>();
        int[] columnCounts = {AMOUNT_COLUMNS_SHORT, AMOUNT_COLUMNS_LONG};
        int[] cardCounts = {AMOUNT_CARDS_SHORT, AMOUNT_CARDS_LONG};
        for (int i = 0; i < AMOUNT_COLUMNS; i++) {
            tableau.add(new Column());
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < columnCounts[i]; j++) {
                Column column = new Column();
                tableau.add(column);
                for (int k = 0; k < cardCounts[i]; k++) {
                    Card card = stock.drawCard();
                    if (k == j) {
                        card.flip();
                    }
                    if (!column.acceptCard(this, card)) {
                        return null;
                    }
                }
            }
        }
        return tableau;
    }

    public List<Foundation> initFoundations() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        for (int j = 0; j < AMOUNT_FOUNDATIONS; j++) {
            foundations.add(new Foundation(SPADES));
        }
        return foundations;
    }

}
