package Base;


public abstract class Game {
    private boolean gameOver;
    private int cantMovements;
    private Deck deck;
    private Foundation foundation;
    private Stock stock;
    private Tableau tableau;

    public Game() {
        this.gameOver = false;
        this.cantMovements = 0;
        this.deck = new Deck();
        this.foundation = new Foundation();
        this.stock = new Stock();
        this.tableau = new Tableau();
    }
}
