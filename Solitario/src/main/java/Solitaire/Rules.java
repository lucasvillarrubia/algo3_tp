package Solitaire;

import Elements.Stock;
import Elements.Foundation;
import Elements.Column;
import Elements.Game;

public interface Rules {
        public boolean acceptsCard(Stock stock);
        public boolean acceptsCard(Foundation foundation);
        public boolean acceptsCard(Column column);
        public boolean givesCard(Stock stock);
        public boolean givesCard(Foundation foundation);
        public boolean givesCard(Column column);
        public boolean admitsSequence(Stock stock);
        public boolean admitsSequence(Foundation foundation);
        public boolean admitsSequence(Column column);
        public boolean checkGameStatus(Game game);
        public void gameInit(Game game);
}
