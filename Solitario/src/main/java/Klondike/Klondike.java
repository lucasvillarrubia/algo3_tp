package Klondike;

import Base.*;


public class Klondike extends Game {

    private final int cantColumns = 7;
    private Deck waste = new Deck();

    public Klondike(int seed) {
        super(seed);
    }

    @Override
    public void initTableau(Tableau tableau, Stock stock) {
        for(int i = 1; i< cantColumns; i++){
            Deck pile = new Deck();
            for(int j = cantColumns; j >1 ; j--){
                stock.showCard();
                Card card = stock.drawCard();
                if(j==i+1){
                    card.flip();
                }
                pile.addCard(card);
            }
            tableau.addPile(pile);
        }
    }



    @Override
    public boolean isGameWon() {
        boolean tableauIsEmpty = tableau.isEmpty();
        boolean wasteIsEmpty = waste.isEmpty();
        boolean foundationsComplete = areAllFoundationsComplete();
        return tableauIsEmpty && wasteIsEmpty && foundationsComplete && cantMovements>0;
    }


    @Override
    public boolean areAllFoundationsComplete () {
        boolean allComplete = true;
        for (Foundation foundation: foundations) {
            if (!foundation.isFull()) {
                allComplete = false;
            }
        }
        return allComplete;
    }


    public boolean play(Card card, Tableau source){
        return false;
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
