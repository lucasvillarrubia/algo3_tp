package Spider;

import Base.Card;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Solitaire.Rules;

public class SpiderRules implements Rules {
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
        return false;
    }

    @Override
    public boolean givesCard(Stock stock) {
        return false;
    }

    @Override
    public boolean givesCard(Foundation foundation) {
        return false;
    }

    @Override
    public boolean givesCard(Column column) {
        return false;
    }

    @Override
    public boolean admitsSequence(Stock stock) {
        return false;
    }

    @Override
    public boolean admitsSequence(Foundation foundation) {
        return false;
    }

    @Override
    public boolean admitsSequence(Column column) {
        return false;
    }

    @Override
    public boolean checkGameStatus(Game game) {
        return false;
    }

    @Override
    public void gameInit(Game game) {

    }
}
