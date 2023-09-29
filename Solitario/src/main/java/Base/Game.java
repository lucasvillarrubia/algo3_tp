package Base;



import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean gameOver;
    private boolean gameWon;
    private int cantMovements;
    private List<Foundation> foundations;
    private Deck stock;
    private Tableau tableau;

    private GameType gameType;


    public Game(int seed, GameType gametype) {
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
        this.stock = new Deck();
        this.foundations = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            foundations.add(new Foundation(suit));
        }

        this.gameType = gametype;
        this.stock = gametype.initStock(seed);
        this.tableau = gametype.initTableau(stock);
    }


    public GameType getGameType() {
        return gameType;
    }


    public boolean gameStatus(){
        return false;
    }


    public int getCantMovements(){
        return cantMovements;
    }

    public int addMovement(){
        return cantMovements++;
    }

    public void move(Deck from, Deck to) {
        gameType.move(from, to);
    }


}
