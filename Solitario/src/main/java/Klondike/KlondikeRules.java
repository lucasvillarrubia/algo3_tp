package Klondike;

import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Solitaire.Rules;

public class KlondikeRules implements Rules {
        public boolean acceptsCard(Stock stock) { return true; }
        public boolean acceptsCard(Foundation foundation) { return true; }
        public boolean acceptsCard(Column column) { return true; }
        public boolean givesCard(Stock stock) { return true; }
        public boolean givesCard(Foundation foundation) { return true; }
        public boolean givesCard(Column column) { return true; }
        public boolean admitsSequence(Stock stock) { return true; }
        public boolean admitsSequence(Foundation foundation) { return true; }
        public boolean admitsSequence(Column column) { return true; }
        public boolean checkGameStatus(Game game) { return true; }
        public void gameInit(Game game) {}
}
