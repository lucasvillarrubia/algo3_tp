package Klondike;



import Base.Card;
import Base.Deck;
import Base.Suit;
import Base.Value;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final int cantColumns = 7;
    private Deck waste;
    private boolean gameOver;
    private boolean gameWon;
    private int cantMovements;
    private List<Foundation> foundations;
    private Deck stock;
    private Tableau tableau;


    public Game(int seed) {
        this.gameOver = false;
        this.gameWon = false;
        this.cantMovements = 0;
        this.stock = initStock();
        this.stock.shuffle(seed);
        this.waste = new Deck();
        this.foundations = new ArrayList<>();
        this.tableau = initTableau(stock);

        for (Suit suit : Suit.values()) {
            foundations.add(new Foundation(suit));
        }
    }

    public Game(ArrayList<Foundation> foundations, Deck stock, Tableau tableau) {
        this.foundations = foundations;
        this.stock = stock;
        this.tableau = tableau;
        this.gameOver = false;
        this.gameWon = false;
        this.waste = new Deck();
        this.cantMovements = 0;
    }


    public boolean isGameWon() {
        return gameWon;
    }

    public void winGame(){
        if (tableau.isEmpty() && areAllFoundationsFull() && stock.isEmpty() && waste.isEmpty()) {
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
                stock.addCard(card);
            }
        }
        return stock;
    }

    public Tableau initTableau(Deck stock) {
        Tableau tableau = new Tableau(cantColumns);
        for (int i = 0; i < cantColumns; i++) {
            for (int j = 0; j < i + 1; j++) {
                Card card = stock.drawCard();
                if (j == i) {
                    card.flip();
                }
                tableau.getDeck(i).add(card);
            }
        }
        return tableau;
    }

    public void resetStock() {
        while (!waste.isEmpty()) {
            Card card = waste.drawCard();
            card.flip();
            stock.addCard(card);
        }
    }

    public void showStockCard() {
        if (stock.isEmpty()) {
            resetStock();
        }
        waste.addCard(stock.drawCard());
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

    public Tableau getTableau () {
        return this.tableau;
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

    public boolean moveFromStockToFoundation() {
        Card cardToMove = waste.drawCard();
        if (cardToMove!= null && getFoundationBySuit(cardToMove.getSuit()).canReceive(cardToMove)) {
            getFoundationBySuit(cardToMove.getSuit()).addCard(cardToMove);
            addMovement();
            winGame();
            return true;
        } else {
            waste.addCard(cardToMove);
            return false;
        }
    }

    public boolean moveBetweenTableauDecks(int fromDeckPos, int toDeckPos) {
        Card cardToMove = tableau.drawCard(fromDeckPos);
        if (tableau.canReceive(cardToMove, toDeckPos)) {
            tableau.addCard(cardToMove, toDeckPos);
            addMovement();
            winGame();
            return true;
        }
        else {
            tableau.getDeck(fromDeckPos).add(cardToMove);
            return false;
        }
    }

    public boolean moveSequenceInTableau(int fromDeckPos, int toDeckPos, int cardIndex) {
        int fromDeckSize = tableau.getDeck(fromDeckPos).size();
        if (tableau.canReceive(tableau.getDeck(fromDeckPos).get(cardIndex), toDeckPos)) {
            List<Card> cardsToMove = tableau.getDeck(fromDeckPos).subList(cardIndex, fromDeckSize);
            tableau.addCardSequence(cardsToMove, toDeckPos);
            for (int i = 0; i < fromDeckSize - cardIndex; i++) {
                tableau.drawCard(fromDeckPos);
            }
            addMovement();
            winGame();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean moveFromTableauToFoundation(int deckPos) {
        Card cardToMove = tableau.drawCard(deckPos);
        if (cardToMove != null && getFoundationBySuit(cardToMove.getSuit()).canReceive(cardToMove)) {
            getFoundationBySuit(cardToMove.getSuit()).addCard(cardToMove);
            addMovement();
            winGame();
            return true;
        }
        else {
            tableau.getDeck(deckPos).add(cardToMove);
            return false;
        }
    }

    public boolean moveFromStockToTableauDeck(int deckPos) {
        Card cardToMove = waste.drawCard();
        if (cardToMove!= null && tableau.canReceive(cardToMove, deckPos)) {
            tableau.addCard(cardToMove, deckPos);
            addMovement();
            winGame();
            return true;
        } else {
            waste.addCard(cardToMove);
            return false;
        }
    }

    public boolean moveFromFoundationToTableauDeck(int deckPos, Suit suit) {
        Card cardToMove = getFoundationBySuit(suit).removeCard();
        if (cardToMove!= null && tableau.canReceive(cardToMove, deckPos)) {
            tableau.addCard(cardToMove, deckPos);
            addMovement();
            winGame();
            return true;
        }
        else {
            getFoundationBySuit(suit).addCard(cardToMove);
            return false;
        }
    }



}
