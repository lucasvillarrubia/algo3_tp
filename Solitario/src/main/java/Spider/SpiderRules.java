package Spider;

import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Solitaire.Rules;

public class SpiderRules extends Rules {


    @Override
    protected void gameInit(Game game) {

    }


    public boolean removeCard(Stock stock) {
        return true;
    }

    public boolean removeCard(Column column) {
        return true;
    }

    public boolean removeCard(Foundation foundation) {
        return true;
    }
}