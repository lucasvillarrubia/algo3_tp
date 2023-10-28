package Elements;

import java.io.*;
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
    private Stock waste;

    public Game(Rules rules, int seed) {
        this.gameRules = rules;
        this.gameRules.gameInit(this, seed);
        this.stock.toggleFillingState();
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
        this.tableau.forEach(Column::toggleFillingState);
    }

    public Game(List<Foundation> foundations, List<Column> tableau, Stock stock) {
        this.foundations = foundations;
        this.tableau = tableau;
        this.stock = stock;
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
    }

    public Game(Rules rules, boolean gameOver, boolean gameWon, int cantMovements, Stock stock, List<Foundation> foundations, List<Column> tableau){
        this.gameRules = rules;
        this.gameOver = gameOver;
        this.gameWon = gameWon;
        this.cantMovements = cantMovements;
        this.stock = stock;
        this.foundations = foundations;
        this.tableau = tableau;
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

    public Stock getStock() {
        return stock;
    }

    public Column getColumn(int index) {
        return tableau.get(index);
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setTableau(List<Column> tableau) { this.tableau = tableau; }

    public void setFoundations(List<Foundation> foundations) { this.foundations = foundations; }

    public boolean areAllFoundationsFull () {
        for (Foundation foundation: foundations) {
            if (!foundation.isFull()){
                return false;
            }
        }
        return true;
    }

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


    public boolean drawCardFromStock(){
        stock.toggleFillingState();
        return gameRules.drawCardFromStock(this);

    }

    public boolean moveCards(Deck from, Deck to) {
        if (!from.givesCard(gameRules)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (to.acceptCard(gameRules, moved)) {
            addMovement();
            winGame();
            return from.removeCard(moved);
        }
        return false;
    }

    public boolean moveCards(Column from, Deck to, int index) {
        if (!from.givesCard(gameRules)) return false;
        Column moved = from.getSequence(index);
        if (moved == null) { return false; }
        else if (to.acceptSequence(gameRules, moved)) {
            addMovement();
            winGame();
            return from.removeCard(moved);
        }
        return false;
    }

}
