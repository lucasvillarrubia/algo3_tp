package Base;


import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private boolean gameOver;
    private int cantMovements;
    private Deck deck;
    private List<Foundation> foundations;
    private Deck stock;
    private Tableau tableau;

    public Game(int seed) {
        this.gameOver = false;
        this.cantMovements = 0;
        this.deck = new Deck();
        deck.initDeck();
        deck.shuffle(seed);
        this.foundations = new ArrayList<>();
        this.stock = new Deck();
        this.tableau = new Tableau();
        initTableau(this.tableau, this.deck);

        for (Suit suit : Suit.values()) {
            foundations.add(new Foundation(suit));
        }
    }

    public abstract void initTableau(Tableau tableau, Deck deck);

    




}
