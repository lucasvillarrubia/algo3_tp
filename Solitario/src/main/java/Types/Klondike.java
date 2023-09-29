package Types;

import Base.*;


public class Klondike implements GameType{

    private final int cantColumns = 7;
    private Deck waste;

    public Klondike() {
        this.waste = new Deck();

    }

    @Override
    public Tableau initTableau(Deck stock) {
        Tableau tableau = new Tableau(cantColumns);
        for(int i = 0; i < cantColumns; i++){
            for(int j = 0; j < i + 1; j++){
                Card card = stock.drawCard();
                if(j==i) {
                    card.flip();
                }
                tableau.getDeck(i).addCard(card);
            }
        }
        return tableau;
    }

    @Override
    public Deck initStock(int seed) {
        Deck stock = new Deck();
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(suit, value);
                stock.addCard(card);
            }
        }
        stock.shuffle(seed);
        return stock;
    }

    @Override
    public boolean movimientoValido() {
        // la carta a mover y
        // el mazo de destino y ese mazo debería tener su
        // propia regla en su propia clase (ya sea tableau,
        // foundation o deck


        return false;
    }


    @Override
    public boolean isGameWon() {
        return false;
    }

    @Override
    public void move(Deck from, Deck to) {
            from.addCard(to.drawCard());
    }



//    public boolean move(Deck from, Deck to) {
//        if (queres mover entre mazos del tableau){
//            gameType.moverEntreTableau
//            return ture;
//        }
//        else if (queres mover a la foundation)
//        gameType.
//    }
//        return false;
//    }

//    public void moveToFoundation

    public void resetStock(Deck stock) {
        while(!waste.isEmpty()) {
            stock.addCard(waste.drawCard());
        }
    }
}
