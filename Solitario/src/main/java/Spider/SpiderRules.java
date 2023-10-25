package Spider;

import Base.Card;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Solitaire.Rules;


public class SpiderRules implements Rules {


    private static final int AMOUNT_COLUMNS = 10;
    private static final int AMOUNT_FOUNDATIONS = 8; //ver si conviene hacer eso o repetir secuencia de klondike
    //depende de como hagamos el init

    //revisar flipped cards!!!1
    @Override
    public boolean acceptsCard(Stock stock, Card card) {
        //ver como seria q el stock accepte una carta (?
        return false;
    }

    @Override
    public boolean acceptsCard(Foundation foundation, Card card) {
        if(foundation.isEmpty()){
            return card.getValue().equals(Value.ACE); //no se si es con equals o hacer la comparacion con ==
        }else {
            Card lastCard =  foundation.getLast();
            boolean compareSuit = card.getSuit().equals(lastCard.getSuit());
            boolean compareValue = card.getValue().getNumber() == (lastCard.getValue().getNumber()+1);
            return compareSuit && compareValue; ///ver como simplificar
        }
    }

    @Override
    public boolean acceptsCard(Column column, Card card) {
        if (column.isEmpty()) {
            return card.getValue() == Value.KING;
        } else {
            Card topCard = column.getLast();
            return card.getValue().getNumber() == topCard.getValue().getNumber() - 1 && isOppositeColor(card, topCard);
        }
    }

    @Override
    public boolean givesCard(Stock stock) {
//        return !stock.isEmpty();
        //logica del movimiento

        return true;
    }

    @Override
    public boolean givesCard(Foundation foundation) {
        //ver esta logica
        return false;
    }

    @Override
    public boolean givesCard(Column column) {
        if (column.isEmpty()) {
            return false;
        } else {
            if (column.isEmpty()) {
                return false;
            }
           //ver esta logica
        }
        return false;
    }

    @Override
    public boolean admitsSequence(Stock stock, Column sequence) {
        //el stock tiene sequence? jaajajajaj
        return false;
    }

    @Override
    public boolean admitsSequence(Foundation foundation, Column sequence) {
        //pensar
        return false;
    }

    @Override
    public boolean admitsSequence(Column column, Column sequence) {
        //revisar esto pero...
        return column.isEmpty() || (column.getLast().getValue() == Value.KING);
    }

    @Override
    public boolean checkGameStatus(Game game) {
        return game.gameStatus();
    }

    @Override
    public void gameInit(Game game) {
//        Stock gameStock = initStock();
//        game.setStock(gameStock);
//        game.setTableau(initTableau(gameStock));
//        game.setFoundations(initFoundations());
    }





    private boolean isOppositeColor(Card card1, Card card2) {
        return (card1.getSuit().getColor() != card2.getSuit().getColor());
    }

    private boolean isSameSuit(Card card1, Card card2) {
        return (card1.getSuit() == card2.getSuit());
    }

}
