package Klondike;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Solitaire.Rules;

public class KlondikeRules extends Rules {

    private AddingChecker addingChecker;

    private SequenceChecker sequenceChecker;

    @Override
    protected void gameInit(Game game) {
        game.setStock(initStock());

    }

    public Stock initStock() {
        Stock stock = new Stock();
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(suit, value);
                stock.acceptsCard(addingChecker, card);
            }
        }
        return stock;
    }

    public boolean checkRule(Stock stock) {
        return true;
    }


    public boolean checkRule(Column column) {
        return true;
    }

    public boolean checkRule(Foundation foundation) {
        return true;
    }
}
