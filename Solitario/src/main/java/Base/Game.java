package Base;


import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    protected boolean gameOver;
    protected boolean gameWon;
    protected int cantMovements;
    protected List<Foundation> foundations;
    protected Stock stock;
    protected Tableau tableau;


    public Game(int seed) {
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
        this.stock = new Stock();
        stock.initStock();
        this.foundations = new ArrayList<>();
        this.tableau = new Tableau();
        initTableau(this.tableau, this.stock);

        for (Suit suit : Suit.values()) {
            foundations.add(new Foundation(suit));
        }
    }

    public abstract void initTableau(Tableau tableau, Stock stock);

    public abstract boolean isGameWon();

    public abstract boolean areAllFoundationsComplete();

    public int getCantMovements(){
        return cantMovements;
    }

    public int sumarMovimiento(){
        return cantMovements + 1;
    }
    




}
