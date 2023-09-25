package Klondike;

import Base.Card;
import Base.Deck;
import Base.Game;
import Base.Tableau;

public class Klondike extends Game {

    //logica de juego segun reglas Klondike
    private final int cantColumnas = 7;

    public Klondike() {
        super();
    }

    @Override
    public void initTableau(Tableau tableau, Deck deck) {
        for(int i = 0; i<cantColumnas; i++){
            Deck pile = new Deck();
            for(int j = cantColumnas; j >0 ; j--){
                Card card = deck.getLast();
                if(j==i){
                    card.flip();
                }
                pile.addCard(card);
            }
            tableau.addPile(pile);
        }
    }


}
