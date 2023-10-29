package Solitaire;

import Base.Card;
import Elements.*;

import java.io.Serializable;
import java.lang.reflect.GenericDeclaration;


public interface Rules {

        //protected transient Game game;

        //public abstract String getRulesType();

        boolean acceptsCard(Stock stock, Card card);
        boolean acceptsCard(Foundation foundation, Card card);
        boolean acceptsCard(Column column, Card card);

       // public abstract boolean acceptsCard(Deck deck, Card card);
        boolean givesCard(Stock stock);
        boolean givesCard(Foundation foundation);
        boolean givesCard(Column column);

        boolean admitsSequence(Stock stock, Column sequence);
        boolean admitsSequence(Foundation foundation, Column sequence);
        boolean admitsSequence(Column column, Column sequence);

        void gameInit(Game game, int seed);

        boolean drawCardFromStock(Game game);

}

