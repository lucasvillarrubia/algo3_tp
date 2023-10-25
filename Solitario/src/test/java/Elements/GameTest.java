package Elements;

import Auxiliar.Deck;
import Auxiliar.Foundation;
import Auxiliar.Game;
import Auxiliar.Tableau;
import Base.Card;
import Base.Suit;
import Base.Value;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {


    @Test
    public void initKlondikeGameTest() {
        Auxiliar.Game klondike = new Auxiliar.Game(1);
        assertFalse(klondike.gameStatus());
        assertFalse(klondike.isGameWon());
        assertNotNull(klondike.getStock());
        assertEquals(klondike.getCantMovements(),0);
    }

    @Test
    public void initOneStepToWinGameTest() {
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>();
        Auxiliar.Foundation foundation = new Auxiliar.Foundation(Suit.CLUBS);
        foundations.add(foundation);
        Deck deck = new Deck();
        Tableau tableau = new Tableau(3);
        Auxiliar.Game game = new Auxiliar.Game(foundations, deck, tableau);
        assertNotNull(game);
        assertFalse(game.gameStatus());
        assertNotNull(game.getStock());
        assertFalse(game.isGameWon());
        assertEquals(0, game.getCantMovements());
        assertNotNull(game.getWaste());
        assertNotNull(game.getTableau());
        assertNotNull(game.getFoundationBySuit(Suit.CLUBS));
    }

    @Test
    public void moveFromTableauToFoundationCompleteTest() {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Auxiliar.Foundation(Suit.HEART));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        Tableau tableau = new Tableau(1);
        tableau.getDeck(0).addCard(three);
        Auxiliar.Game game = new Auxiliar.Game(foundations, new Deck(), tableau);
        assertTrue(game.moveFromTableauToFoundation(0));
        assertTrue(game.getTableau().getDeck(0).isEmpty());
        assertFalse(game.isGameWon());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), three);
    }

    @Test
    public void moveFromTableauToFoundationFailedTest() {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card wrongCard = new Card(Suit.HEART, Value.KING);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Auxiliar.Foundation(Suit.HEART));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        Tableau tableau = new Tableau(1);
        tableau.addCard(wrongCard, 0);
        Auxiliar.Game game = new Auxiliar.Game(foundations, new Deck(), tableau);
        game.moveFromTableauToFoundation(0);
        assertFalse(game.moveFromTableauToFoundation(0));
        assertEquals(game.getTableau().getLast(0), wrongCard);
        assertFalse(game.isGameWon());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), two);
    }

    @Test
    public void moveFromStockToFoundationCompleteTest() {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        Card four = new Card(Suit.HEART, Value.FOUR);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Auxiliar.Foundation(Suit.HEART));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        foundations.get(0).addCard(three);
        Deck stock = new Deck();
        stock.addCard(four);
        Auxiliar.Game game = new Auxiliar.Game(foundations, stock, new Tableau(1));
        game.showStockCard();
        assertTrue(game.moveFromStockToFoundation());
        assertTrue(game.getStock().isEmpty());
        assertFalse(game.isGameWon());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), four);
    }

    @Test
    public void moveFromStockToFoundationFailedTest() {
        Card one = new Card(Suit.CLUBS, Value.ACE);
        Card two = new Card(Suit.CLUBS, Value.TWO);
        Card three = new Card(Suit.CLUBS, Value.THREE);
        Card wrongCard = new Card(Suit.CLUBS, Value.JACK);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Auxiliar.Foundation(Suit.CLUBS));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        foundations.get(0).addCard(three);
        Deck stock = new Deck();
        stock.addCard(wrongCard);
        Auxiliar.Game game = new Auxiliar.Game(foundations, stock, new Tableau(1));
        game.showStockCard();
        game.moveFromStockToFoundation();
        assertFalse(game.moveFromStockToFoundation());
        assertEquals(game.getWaste().getLast(), wrongCard);
        assertFalse(game.isGameWon());
        assertEquals(game.getFoundationBySuit(Suit.CLUBS).getTopCard(), three);
    }

    @Test
    public void moveFromStockToTableauCompleteTest() {
        Card king = new Card(Suit.SPADES, Value.KING);
        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
        Card jack = new Card(Suit.CLUBS, Value.JACK);
        Card ten = new Card(Suit.HEART, Value.TEN);
        Tableau tableau = new Tableau(1);
        tableau.addCard(king, 0);
        tableau.addCard(queen, 0);
        tableau.addCard(jack, 0);
        Deck stock = new Deck();
        stock.addCard(ten);
        Auxiliar.Game game = new Auxiliar.Game(new ArrayList<>(4), stock, tableau);
        game.showStockCard();
        assertTrue(game.moveFromStockToTableauDeck(0));
        assertTrue(game.getStock().isEmpty());
        assertTrue(game.getWaste().isEmpty());
        assertEquals(game.getTableau().getLast(0), ten);
        assertFalse(game.isGameWon());
    }

    @Test
    public void moveFromStockToTableauFailedTest() {
        Card king = new Card(Suit.SPADES, Value.KING);
        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
        Card jack = new Card(Suit.CLUBS, Value.JACK);
        Card wrongCard = new Card(Suit.CLUBS, Value.NINE);
        Tableau tableau = new Tableau(1);
        tableau.addCard(king, 0);
        tableau.addCard(queen, 0);
        tableau.addCard(jack, 0);
        Deck stock = new Deck();
        stock.addCard(wrongCard);
        Auxiliar.Game game = new Auxiliar.Game(new ArrayList<>(4), stock, tableau);
        game.showStockCard();
        game.moveFromStockToTableauDeck(0);
        assertFalse(game.moveFromStockToTableauDeck(0));
        assertEquals(game.getTableau().getLast(0), jack);
        assertEquals(game.getWaste().getLast(), wrongCard);
        assertFalse(game.isGameWon());
    }

    @Test
    public void moveBetweenTableauDecksCompleteTest() {
        Card king = new Card(Suit.SPADES, Value.KING);
        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
        Card jack = new Card(Suit.CLUBS, Value.JACK);
        Card otherKing = new Card(Suit.CLUBS, Value.KING);
        Card otherQueen = new Card(Suit.HEART, Value.QUEEN);
        Tableau tableau = new Tableau(2);
        tableau.addCard(king, 0);
        tableau.addCard(queen, 0);
        tableau.addCard(jack, 0);
        tableau.addCard(otherKing, 1);
        tableau.addCard(otherQueen, 1);
        Auxiliar.Game game = new Auxiliar.Game(new ArrayList<>(4), new Deck(), tableau);
        assertTrue(game.moveBetweenTableauDecks(0, 1));
        assertEquals(game.getTableau().getLast(0), queen);
        assertEquals(game.getTableau().getLast(1), jack);
        assertFalse(game.isGameWon());
    }

    @Test
    public void moveBetweenTableauDecksFailedTest() {
        Card king = new Card(Suit.SPADES, Value.KING);
        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
        Card wrongJack = new Card(Suit.CLUBS, Value.JACK);
        Card otherKing = new Card(Suit.CLUBS, Value.KING);
        Tableau tableau = new Tableau(2);
        tableau.addCard(king, 0);
        tableau.addCard(queen, 0);
        tableau.addCard(wrongJack, 0);
        tableau.addCard(otherKing, 1);
        Auxiliar.Game game = new Auxiliar.Game(new ArrayList<>(4), new Deck(), tableau);
        assertFalse(game.moveBetweenTableauDecks(0, 1));
        assertEquals(game.getTableau().getLast(1), otherKing);
        assertEquals(game.getTableau().getLast(0), wrongJack);
        assertFalse(game.isGameWon());
    }

    @Test
    public void moveFromFoundationToTableauCompleteTest() {
        Card one = new Card(Suit.DIAMOND, Value.ACE);
        Card two = new Card(Suit.CLUBS, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Auxiliar.Foundation(Suit.DIAMOND));
        foundations.get(0).addCard(one);
        Tableau tableau = new Tableau(1);
        tableau.getDeck(0).addCard(three);
        tableau.addCard(two, 0);
        Auxiliar.Game game = new Auxiliar.Game(foundations, new Deck(), tableau);
        assertTrue(game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND));
        assertNull(game.getFoundationBySuit(Suit.DIAMOND).getTopCard());
        assertEquals(game.getTableau().getLast(0), one);
        assertFalse(game.isGameWon());
    }

    @Test
    public void moveFromFoundationToTableauFailedTest() {
        Card wrongOne = new Card(Suit.DIAMOND, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.SPADES, Value.THREE);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Auxiliar.Foundation(Suit.DIAMOND));
        foundations.get(0).addCard(wrongOne);
        Tableau tableau = new Tableau(1);
        tableau.getDeck(0).addCard(three);
        tableau.addCard(two, 0);
        Auxiliar.Game game = new Auxiliar.Game(foundations, new Deck(), tableau);
        assertFalse(game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND));
        assertEquals(game.getFoundationBySuit(Suit.DIAMOND).getTopCard(), wrongOne);
        assertEquals(game.getTableau().getLast(0), two);
        assertFalse(game.isGameWon());
    }


    @Test
    public void moveCardSequenceToEmptyDeckCompleteTest1() {
        Card king = new Card(Suit.SPADES, Value.KING);
        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
        Card jack = new Card(Suit.CLUBS, Value.JACK);
        Tableau tableau = new Tableau(2);
        tableau.addCard(king, 0);
        tableau.addCard(queen, 0);
        tableau.addCard(jack, 0);
        Auxiliar.Game game = new Auxiliar.Game(new ArrayList<>(4), new Deck(), tableau);
        assertTrue(game.moveSequenceInTableau(0, 1, 0));
        assertEquals(tableau.getDeck(0).size(), 0);
        assertEquals(tableau.getDeck(1).size(), 3);
        assertEquals(tableau.getLast(1), jack);
    }

    @Test
    public void moveCardSequenceBetweenTableauDecksCompleteTest() {
        Card otherKing = new Card(Suit.CLUBS, Value.KING);
        Card king = new Card(Suit.SPADES, Value.KING);
        Tableau tableau = new Tableau(2);
        tableau.addCard(king, 0);
        tableau.addCard(new Card(Suit.DIAMOND, Value.QUEEN), 0);
        tableau.addCard(new Card(Suit.CLUBS, Value.JACK), 0);
        tableau.addCard(new Card(Suit.HEART, Value.TEN), 0);
        tableau.addCard(new Card(Suit.SPADES, Value.NINE), 0);
        tableau.addCard(new Card(Suit.HEART, Value.EIGHT), 0);
        tableau.addCard(otherKing, 1);
        Auxiliar.Game game = new Auxiliar.Game(new ArrayList<>(4), new Deck(), tableau);
        boolean didMove = game.moveSequenceInTableau(0, 1, 1);
        assertTrue(didMove);
        assertEquals(1, tableau.getDeck(0).size());
        assertEquals(6, tableau.getDeck(1).size());
        assertEquals(Value.EIGHT, tableau.getLast(1).getValue());
        assertEquals(king, tableau.getLast(0));
    }


    @Test
    public void moveCardSequenceBetweenTableauDecksFailedTest() {
        Tableau tableau = new Tableau(2);
        tableau.addCard(new Card(Suit.SPADES, Value.KING), 0);
        tableau.addCard(new Card(Suit.DIAMOND, Value.QUEEN), 0);
        tableau.addCard(new Card(Suit.CLUBS, Value.JACK), 0);
        Auxiliar.Game game = new Auxiliar.Game(new ArrayList<>(4), new Deck(), tableau);
        assertFalse(game.moveSequenceInTableau(0, 1, 1));
    }

    @Test
    public void fullFoundationsTest() {
        Tableau tableau = new Tableau(7);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>();
        foundations.add(new Auxiliar.Foundation(Suit.HEART));
        foundations.add(new Auxiliar.Foundation(Suit.DIAMOND));
        foundations.add(new Auxiliar.Foundation(Suit.SPADES));
        foundations.add(new Auxiliar.Foundation(Suit.CLUBS));
        Auxiliar.Game game = new Auxiliar.Game(foundations, new Deck(), tableau);
        for (Auxiliar.Foundation foundation : foundations) {
            Suit suit = foundation.getSuit();
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                foundation.addCard(card);
            }
        }
        assertTrue(game.areAllFoundationsFull());
        game.winGame();
        assertTrue(game.isGameWon());
    }

    @Test
    public void lastMoveStockRestTest() {
        Tableau tableau = new Tableau(7);
        ArrayList<Auxiliar.Foundation> foundations = new ArrayList<>();
        foundations.add(new Auxiliar.Foundation(Suit.HEART));
        foundations.add(new Auxiliar.Foundation(Suit.DIAMOND));
        foundations.add(new Auxiliar.Foundation(Suit.SPADES));
        foundations.add(new Auxiliar.Foundation(Suit.CLUBS));
        for (Foundation foundation : foundations) {
            Suit suit = foundation.getSuit();
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                foundation.addCard(card);
            }
        }
        Deck stock =new Deck();
        stock.addCard(foundations.get(3).removeCard());
        Auxiliar.Game game = new Game(foundations, stock, tableau);
        game.showStockCard();
        game.showStockCard();
        assertTrue(game.moveFromStockToFoundation());
        assertTrue(game.isGameWon());
        assertEquals(game.getCantMovements(), 3);
    }

}