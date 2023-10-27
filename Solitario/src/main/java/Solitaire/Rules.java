package Solitaire;

import Base.Card;
import Elements.Stock;
import Elements.Foundation;
import Elements.Column;
import Elements.Game;

import java.util.List;

public interface Rules {
        String getRulesType();
        boolean acceptsCard(Stock stock, Card card);
        boolean acceptsCard(Foundation foundation, Card card);
        boolean acceptsCard(Column column, Card card);
        boolean givesCard(Stock stock);
        boolean givesCard(Foundation foundation);
        boolean givesCard(Column column);
        boolean admitsSequence(Stock stock, Column sequence);
        boolean admitsSequence(Foundation foundation, Column sequence);
        boolean admitsSequence(Column column, Column sequence);
        boolean checkGameStatus(Game game);
        void gameInit(Game game);



}
