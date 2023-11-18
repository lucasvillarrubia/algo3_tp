package Solitaire;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Elements.*;
import Base.Card;
import Base.Suit;
import Base.Deck;


public class Game implements Serializable {

    private final Rules gameRules;
    private boolean gameOver;
    private boolean gameWon;
    private int cantMovements;
    private final List<Foundation> foundations;
    private final List<Column> tableau;
    private final Stock stock;

    public Game(Rules rules, int seed) {
        this.gameRules = rules;
        this.stock = gameRules.initStock();
        this.stock.shuffle(seed);
        this.tableau = gameRules.initTableau(this.stock);
        this.foundations = gameRules.initFoundations();
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
    }

    public Game(Rules rules, List<Foundation> foundations, List<Column> tableau, Stock stock) {
        this.gameRules = rules;
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

    public void winGame(){
        if (areAllFoundationsFull() && stock.isEmpty() && tableau.isEmpty()) {
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

    public boolean drawCardFromStock(){
        return gameRules.drawCardFromStock(this.stock, this.tableau);
    }

    public boolean makeAMove(Visitable from, Visitable to) {
        if (from == null || to == null) { return false; }
        if (!gameRules.givesCard(from)) { return false; }
        return gameRules.acceptsCard(from, to);
    }

    public boolean makeAMove(Column from, Visitable to, int index) {
        if (from == null || to == null) { return false; }
        if (!gameRules.givesCard(from)) { return false; }
        return (gameRules.admitsSequence(to, from.getSequence(index)));
    }

    public boolean moveCards(Deck from, Deck to) {
        if (from.getLast() == null) { return false; }
        return to.addCards(from.drawCard());
    }

    public boolean moveCards(Column from, Deck to, int index) {
        Column moved = from.getSequence(index);
        if (moved == null) { return false; }
        return to.addCards(moved) && from.removeSequence(moved);
    }

}
