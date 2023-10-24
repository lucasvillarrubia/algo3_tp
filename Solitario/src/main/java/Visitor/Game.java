package Visitor;

import java.util.ArrayList;
import java.util.List;

import Base.Card;
import Base.Suit;
import Base.Value;

public class Game {
      // mover constante al init game de rules correspondiente  
    //private static final int AMOUNT_COLUMNS = 7;
    private Deck waste;
    private boolean gameOver;
    private boolean gameWon;
    private int cantMovements;
    private List<Foundation> foundations;
    private List<Column> tableau;
    private Stock stock;


    public Game(int seed) {
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
        this.stock = new Stock();
        this.stock.fill();
        this.stock.shuffle(seed);
        this.waste = new Deck();
        this.foundations = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            foundations.add(new Foundation(suit));
        }
    }

    public Game(ArrayList<Foundation> foundations, Stock stock) {
        this.foundations = foundations;
        this.stock = stock;
        this.gameOver = false;
        this.gameWon = false;
        this.waste = new Deck();
        this.cantMovements = 0;
    }


    public boolean isGameWon() {
        return gameWon;
    }

    // falta chequear que el tableau esté vacío
    public void winGame(){
        if (areAllFoundationsFull() && stock.isEmpty() && waste.isEmpty()) {
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


    public Deck initStock() {
        Deck stock = new Deck();
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(suit, value);
                stock.addCards(card);
            }
        }
        return stock;
    }

    // AGREGAR ESTO AL INIT GAME
    // public Tableau initTableau(Deck stock) {
    //     Tableau tableau = new Tableau(AMOUNT_COLUMNS);
    //     for (int i = 0; i < AMOUNT_COLUMNS; i++) {
    //         for (int j = 0; j < i + 1; j++) {
    //             Card card = stock.drawCard();
    //             if (j == i) {
    //                 card.flip();
    //             }
    //             tableau.getDeck(i).addCards(card);
    //         }
    //     }
    //     return tableau;
    // }

    public void resetStock() {
        while (!waste.isEmpty()) {
            Card card = waste.drawCard();
            card.flip();
            stock.addCards(card);
        }
    }

    public void showStockCard() {
        if (stock.isEmpty()) {
            resetStock();
        }
        waste.addCards(stock.drawCard());
        addMovement();
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

    public Deck getStock () {
        return this.stock;
    }

    public Deck getWaste () {
        return this.waste;
    }

    public boolean areAllFoundationsFull () {
        for (Foundation foundation: foundations) {
            if (!foundation.isFull()){
                return false;
            }
        }
        return true;
    }


    //                              M O V I M I E N T O S

    public boolean moveCard (Deck from, Deck to) {
        Card moved = from.getLast();
        if (moved == null) { return false; }
        else if (to.addCards(moved) && from.removeCard(moved)) {
            addMovement();
            winGame();
            return true;
        }
        return false;
    }

    // public boolean moveCardsInTableau (int from, int to, int index) {
    //     if (index == 0) { return tableau.moveCards(from, to); }
    //     else { return tableau.moveCards(from, to, index); }
    // }

    // acá todavía se mueve una secuencia cualquiera, falta chequear que todas las cartas
    // están en orden correcto y se <pueden> mover
    // método que estaría en game pidiendo Decks a Tableau:
    // public boolean moveCardsInTableau(int fromDeckPos, int toDeckPos, int cardIndex) {
    //     Collection<Card> cardsToMove = tableau.getDeck(fromDeckPos).getCards(cardIndex);
    //     if (cardsToMove == null) { return false; }
    //     else if (tableau.getDeck(toDeckPos).addCards(cardsToMove)) {
    //         for (Card card : cardsToMove) {
    //             if (!tableau.getDeck(fromDeckPos).removeCard(card)) { return false; }
    //         }
    //         addMovement();
    //         return true;
    //     }
    //     return false;
    // }

}
