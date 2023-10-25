package Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Solitaire.Rules;
import Base.Deck;
import Base.Card;
import Base.Suit;


public class Game implements Serializable {
    private Rules gameRules;
    private boolean gameOver;
    private boolean gameWon;
    private int cantMovements;
    private List<Foundation> foundations;
    private List<Column> tableau;
    private Stock stock;


    public Game(Rules rules, int seed) {
        this.gameRules = rules;
        this.gameRules.gameInit(this);
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
        this.stock.shuffle(seed);
        this.stock.setFilled();

//        this.stock = new Stock();
//        this.stock.fill();
//        this.foundations = new ArrayList<>();
//        for (Suit suit : Suit.values()) {
//            foundations.add(new Foundation(suit));
//        }
    }

    public Game(ArrayList<Foundation> foundations, Stock stock) {
        this.foundations = foundations;
        this.stock = stock;
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
    }


    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // falta chequear que el tableau esté vacío
    public void winGame(){
        if (areAllFoundationsFull() && stock.isEmpty()) {
            gameWon = true;
            gameOver = true;
        }
    }

    public boolean gameStatus() {
        return gameOver;
    }


    public int getCantMovements() {
        return cantMovements;
    }

    public void addMovement() {
        cantMovements++;
    }

    public Foundation getFoundationBySuit (Suit suit) {
        Foundation foundation = null;
        for (Foundation foundationBySuit: foundations) {
            if (foundationBySuit.getSuit() == suit) {
                foundation = foundationBySuit;
            }
        }
        return foundation;
    }


    public boolean areAllFoundationsFull () {
        for (Foundation foundation: foundations) {
            if (!foundation.isFull()){
                return false;
            }
        }
        return true;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setTableau(List<Column> tableau) { this.tableau = tableau; }

    public void setFoundations(List<Foundation> foundations) { this.foundations = foundations; }

    //Serializacion
    public void serialize(OutputStream os) throws IOException {
        ObjectOutputStream objectOutStream = new ObjectOutputStream(os);
        objectOutStream.writeObject(this);
        objectOutStream.flush();
    }

    public static Game deserialize(InputStream is) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInStream = new ObjectInputStream(is);
        return (Game) objectInStream.readObject();
    }

    //                              M O V I M I E N T O S

    // MOVER A STOCK ??
    // public void showStockCard() {
    //     if (stock.isEmpty()) {
    //         resetStock();
    //     }
    //     waste.addCards(stock.drawCard());
    //     addMovement();
    // }

//     public void showStockCard() {
//         if (stock.isEmpty()) {
//             resetStock();
//         }
//         gameRules.showStockCard();
//         //-> en el klondike haces game.getWaste.add
//         //->SPIDER column.add(stocke.get(0)
//
//     }



    //mover cartas
    //pedirle al stock
    //pedirle una carta a la foundation




    public boolean moveCards (Deck from, Deck to) {
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (to.acceptCard(gameRules, moved)) {
            addMovement();
            winGame();
            return from.removeCard(moved);
        }
        return false;
    }

    public boolean moveCards (Column from, Deck to, int index) {
        Column moved = from.getSequence(index);
        if (moved == null) { return false; }
        else if (to.acceptSequence(gameRules, moved)) {
            addMovement();
            winGame();
            return from.removeCard(moved);
        }
        return false;
    }


//    public boolean moveCards(Stock from, Column to ){
//        return false;
//    }

}
