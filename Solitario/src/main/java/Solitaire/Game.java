package Solitaire;

import java.io.*;
import java.util.List;

import Elements.Column;
import Elements.Foundation;
import Elements.Stock;
import Base.Card;
import Base.Suit;


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

    public boolean moveCards(Column from, Foundation to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Stock from, Foundation to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Foundation from, Foundation to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Column from, Column to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Stock from, Column to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Foundation from, Column to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Column from, Stock to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Stock from, Stock to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Foundation from, Stock to) {
        if (!gameRules.givesCard(from)) return false;
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (gameRules.acceptsCard(to, moved)) {
            from.drawCard();
            return to.addCards(moved);
        }
        return false;
    }

    public boolean moveCards(Column from, Column to, int index) {
        if (!gameRules.givesCard(from)) return false;
        Column moved = from.getSequence(index);
        if (moved == null) { return false; }
        else if (gameRules.admitsSequence(to, moved)) {
            return to.addCards(moved) && from.removeSequence(moved);
        }
        return false;
    }

    public boolean moveCards(Column from, Stock to, int index) {
        if (!gameRules.givesCard(from)) return false;
        Column moved = from.getSequence(index);
        if (moved == null) { return false; }
        else if (gameRules.admitsSequence(to, moved)) {
            return to.addCards(moved) && from.removeSequence(moved);
        }
        return false;
    }

    public boolean moveCards(Column from, Foundation to, int index) {
        if (!gameRules.givesCard(from)) return false;
        Column moved = from.getSequence(index);
        if (moved == null) { return false; }
        else if (gameRules.admitsSequence(to, moved)) {
            return to.addCards(moved) && from.removeSequence(moved);
        }
        return false;
    }

}
