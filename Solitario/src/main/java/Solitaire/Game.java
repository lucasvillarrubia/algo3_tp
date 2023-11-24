package Solitaire;

import java.io.*;
import java.util.List;
import Elements.*;
import Base.Suit;


public class Game implements Serializable {

    private final Rules gameRules;
    private boolean gameOver;
    private boolean gameWon;
    private int cantMovements;
    private List<Foundation> foundations;
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
        if (areAllFoundationsFull() && stock.isEmpty() && areAllColumnsEmpty()) {
            gameWon = true;
            gameOver = true;
        }
//        System.out.println("Foundations: " + areAllFoundationsFull());
//        System.out.println("Stock: " + stock.isEmpty());
//        System.out.println("COlumns: " + areAllColumnsEmpty());
    }

    public boolean gameStatus() {
        return gameOver && gameWon;
    }

    public int getCantMovements() {
        return cantMovements;
    }

    public void addMovement() {
        cantMovements++;
    }

    public Foundation getFoundation(Suit suit) {
        Foundation foundation = null;
        for (Foundation foundationBySuit: foundations) {
            if (foundationBySuit.getSuit() == suit) {
                foundation = foundationBySuit;
            }
        }
        return foundation;
    }

    public Foundation getFoundation(int index) {
        return foundations.get(index);
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

    public boolean areAllColumnsEmpty() {
        for (Column column: tableau) {
            if (!column.isEmpty()){
                return false;
            }
        }
        return true;
    }

//    public void serializeOld(OutputStream os) throws IOException {
//        ObjectOutputStream objectOutStream = new ObjectOutputStream(os);
//        objectOutStream.writeObject(this);
//        objectOutStream.flush();
//    }
    public void serialize() throws IOException {
        File file = new File("savedGame.txt");
        try (ObjectOutputStream objectOutStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutStream.writeObject(this);
            objectOutStream.flush();
        }
    }

//    public static Game deserializeOld(InputStream is) throws IOException, ClassNotFoundException {
//        ObjectInputStream objectInStream = new ObjectInputStream(is);
//        return (Game) objectInStream.readObject();
//    }

    public static Game deserialize(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Game) objectInStream.readObject();
        }
    }

    public boolean drawCardFromStock(){
        addMovement();
        return gameRules.drawCardFromStock(this.stock, this.tableau);
    }


    public boolean makeAMove (Movement move) {
        if (move.checkMoveByRules(gameRules)) {
            addMovement();
            winGame();
            return true;
        }
        else return false;
    }

    public String getGameRules(){
        return gameRules.getRulesString();
    }


}
