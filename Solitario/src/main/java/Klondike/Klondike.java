package Klondike;

import Base.*;

import java.util.Stack;

public class Klondike extends Game {

    //logica de juego segun reglas Klondike
    private final int cantColumns = 7;
    private Deck waste = new Deck();

    public Klondike(int seed) {
        super(seed);
    }

    @Override
    public void initTableau(Tableau tableau, Deck deck) {
        for(int i = 0; i<cantColumns; i++){
            Deck pile = new Deck();
            for(int j = cantColumns; j >0 ; j--){
                Card card = deck.sendCard();
                if(j==i){
                    card.flip();
                }
                pile.addCard(card);
            }
            tableau.addPile(pile);
        }
    }




    // Otra función según Klondike, una vez que se termina el mazo, se puede "reiniciar"
    // Que es volver a poner todas las cartas del waste en el mismo orden sin mezclar.

    // Tiene que haber otra función para Stock para que
    // reciba la carta sin verificar nada
    // Jsjsjs borramos Stock

    // public void resetDeck(){
    //     for (int i = 0; i < waste.cardCount(); i++) {
    //         this.stock.addCard(waste.sendCard());
    //     }
    // }


}
