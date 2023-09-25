package Base;


public abstract class Game {
    private boolean gameOver;
    private int cantMovements;
    private Deck deck;
    private Foundation foundation;
    private Deck stock;
    private Tableau tableau;

    public Game() {
        this.gameOver = false;
        this.cantMovements = 0;
        this.deck = new Deck();
        deck.initDeck();
        deck.shuffle(9);
        this.foundation = new Foundation();
        this.stock = new Deck();
        this.tableau = new Tableau();
        initTableau(this.tableau, this.deck);
    }

    public abstract void initTableau(Tableau tableau, Deck deck);




}
