package Solitaire;

import Base.Card;
import Elements.*;

import java.util.List;


public interface Rules {
        boolean acceptsCard(Stock stock, Card card);
        boolean acceptsCard(Foundation foundation, Card card);
        boolean acceptsCard(Column column, Card card);

        boolean givesCard(Stock stock);
        boolean givesCard(Foundation foundation);
        boolean givesCard(Column column);

        boolean admitsSequence(Stock stock, Column sequence);
        boolean admitsSequence(Foundation foundation, Column sequence);
        boolean admitsSequence(Column column, Column sequence);
        Stock initStock();
        List<Column> initTableau(Stock stock);
        List<Foundation> initFoundations();
        boolean drawCardFromStock(Game game);

        boolean isSequenceValid(Card prev, Card next);

}

