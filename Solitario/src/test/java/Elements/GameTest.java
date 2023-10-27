package Elements;

import Base.Card;
import Base.Suit;
import Base.Value;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void buildGameWIthStockAndFoundation() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        Stock stock = new Stock();
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(foundations,tableau,stock);
        assertFalse(game.isGameWon());
        assertFalse(game.isGameOver());
        assertFalse(game.gameStatus());
        assertEquals(game.getCantMovements(),0);
    }

    @Test
    public void winGameTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        Stock stock = new Stock();
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(foundations,tableau, stock);
        assertFalse(game.gameStatus());
        game.winGame();
        assertTrue(game.gameStatus());
        assertTrue(game.isGameWon());
        assertTrue(game.isGameOver());
    }


    @Test
    public void addMovementTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        Stock stock = new Stock();
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(foundations,tableau, stock);
        game.addMovement();
        assertEquals(1, game.getCantMovements());
    }

    @Test
    public void getFoundationBySuitTest() {
        ArrayList<Foundation> foundations  = new ArrayList<>();
        Stock stock = new Stock();
        ArrayList<Column> tableau = new ArrayList<>();
        foundations.add(new Foundation(Suit.CLUBS));
        Game game = new Game(foundations,tableau, stock);
        assertNotNull(game.getFoundationBySuit(Suit.CLUBS));
    }

    @Test
    public void fullFoundationsTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        foundations.add(new Foundation(Suit.HEART));
        foundations.add(new Foundation(Suit.DIAMOND));
        foundations.add(new Foundation(Suit.SPADES));
        foundations.add(new Foundation(Suit.CLUBS));
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(foundations,tableau , new Stock());
        for (Foundation foundation : foundations) {
            Suit suit = foundation.getSuit();
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                foundation.addCards(card);
            }
        }
        assertTrue(game.areAllFoundationsFull());
        game.winGame();
        assertTrue(game.isGameWon());
    }

    @Test
    public void notFullFoundationsTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        foundations.add(new Foundation(Suit.HEART));
        foundations.add(new Foundation(Suit.DIAMOND));
        foundations.add(new Foundation(Suit.SPADES));
        foundations.add(new Foundation(Suit.CLUBS));
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(foundations, tableau,new Stock());
        for (Foundation foundation : foundations) {
            Suit suit = foundation.getSuit();
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                foundation.addCards(card);
            }
        }
        game.getFoundationBySuit(Suit.SPADES).drawCard();
        assertFalse(game.areAllFoundationsFull());
    }

    //Falta testar el metodo de set stock y el constructor de games segun rules (evitar loop)

    @Test public void serializationTest() throws IOException, ClassNotFoundException {
        ArrayList<Foundation> foundations = new ArrayList<>();
        foundations.add(new Foundation(Suit.CLUBS));
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(foundations, tableau, new Stock());
        game.addMovement();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        game.serialize(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        Game deserializedGame = Game.deserialize(inputStream);
        assertNotNull(deserializedGame);
        assertEquals(deserializedGame.getCantMovements(), 1);
    }

    //game serialization con el stock lleno de cartas y cosas(?


    //Testeos de Game con Klondike



    //Testeos de Game con Spider

//   @Test
//    public void initOneStepToWinGameTest() {
//        ArrayList<Foundation> foundations = new ArrayList<>();
//        Foundation foundation = new Foundation(Suit.CLUBS);
//        foundations.add(foundation);
//        Stock stock = new Stock();
//        Game game = new Game(foundations, stock);
//        assertNotNull(game);
//        assertFalse(game.gameStatus());
//        assertFalse(game.isGameWon());
//        assertEquals(0, game.getCantMovements());
//        assertNotNull(game.getFoundationBySuit(Suit.CLUBS));
//    }

//
//    @Test
//    public void moveFromTableauToFoundationFailedTest() {
//        Card one = new Card(Suit.HEART, Value.ACE);
//        Card two = new Card(Suit.HEART, Value.TWO);
//        Card wrongCard = new Card(Suit.HEART, Value.KING);
//        ArrayList<Foundation> foundations = new ArrayList<>(4);
//        foundations.add(new Foundation(Suit.HEART));
//        foundations.get(0).addCards(one);
//        foundations.get(0).addCards(two);
//        Tableau tableau = new Tableau(1);
//        tableau.addCards(wrongCard, 0);
//        Game game = new Game(foundations, new Deck(), tableau);
//        game.moveFromTableauToFoundation(0);
//        assertFalse(game.moveFromTableauToFoundation(0));
//        assertEquals(game.getTableau().getLast(0), wrongCard);
//        assertFalse(game.isGameWon());
//        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), two);
//    }
//
//    @Test
//    public void moveFromStockToFoundationCompleteTest() {
//        Card one = new Card(Suit.HEART, Value.ACE);
//        Card two = new Card(Suit.HEART, Value.TWO);
//        Card three = new Card(Suit.HEART, Value.THREE);
//        Card four = new Card(Suit.HEART, Value.FOUR);
//        ArrayList<Foundation> foundations = new ArrayList<>(4);
//        foundations.add(new Foundation(Suit.HEART));
//        foundations.get(0).addCards(one);
//        foundations.get(0).addCards(two);
//        foundations.get(0).addCards(three);
//        Deck stock = new Deck();
//        stock.addCards(four);
//        Game game = new Game(foundations, stock, new Tableau(1));
//        game.showStockCard();
//        assertTrue(game.moveFromStockToFoundation());
//        assertTrue(game.getStock().isEmpty());
//        assertFalse(game.isGameWon());
//        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), four);
//    }
//
//    @Test
//    public void moveFromStockToFoundationFailedTest() {
//        Card one = new Card(Suit.CLUBS, Value.ACE);
//        Card two = new Card(Suit.CLUBS, Value.TWO);
//        Card three = new Card(Suit.CLUBS, Value.THREE);
//        Card wrongCard = new Card(Suit.CLUBS, Value.JACK);
//        ArrayList<Foundation> foundations = new ArrayList<>(4);
//        foundations.add(new Foundation(Suit.CLUBS));
//        foundations.get(0).addCards(one);
//        foundations.get(0).addCards(two);
//        foundations.get(0).addCards(three);
//        Deck stock = new Deck();
//        stock.addCards(wrongCard);
//        Game game = new Game(foundations, stock, new Tableau(1));
//        game.showStockCard();
//        game.moveFromStockToFoundation();
//        assertFalse(game.moveFromStockToFoundation());
//        assertEquals(game.getWaste().getLast(), wrongCard);
//        assertFalse(game.isGameWon());
//        assertEquals(game.getFoundationBySuit(Suit.CLUBS).getTopCard(), three);
//    }
//
//    @Test
//    public void moveFromStockToTableauCompleteTest() {
//        Card king = new Card(Suit.SPADES, Value.KING);
//        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
//        Card jack = new Card(Suit.CLUBS, Value.JACK);
//        Card ten = new Card(Suit.HEART, Value.TEN);
//        Tableau tableau = new Tableau(1);
//        tableau.addCards(king, 0);
//        tableau.addCards(queen, 0);
//        tableau.addCards(jack, 0);
//        Deck stock = new Deck();
//        stock.addCards(ten);
//        Game game = new Game(new ArrayList<>(4), stock, tableau);
//        game.showStockCard();
//        assertTrue(game.moveFromStockToTableauDeck(0));
//        assertTrue(game.getStock().isEmpty());
//        assertTrue(game.getWaste().isEmpty());
//        assertEquals(game.getTableau().getLast(0), ten);
//        assertFalse(game.isGameWon());
//    }
//
//    @Test
//    public void moveFromStockToTableauFailedTest() {
//        Card king = new Card(Suit.SPADES, Value.KING);
//        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
//        Card jack = new Card(Suit.CLUBS, Value.JACK);
//        Card wrongCard = new Card(Suit.CLUBS, Value.NINE);
//        Tableau tableau = new Tableau(1);
//        tableau.addCards(king, 0);
//        tableau.addCards(queen, 0);
//        tableau.addCards(jack, 0);
//        Deck stock = new Deck();
//        stock.addCards(wrongCard);
//        Game game = new Game(new ArrayList<>(4), stock, tableau);
//        game.showStockCard();
//        game.moveFromStockToTableauDeck(0);
//        assertFalse(game.moveFromStockToTableauDeck(0));
//        assertEquals(game.getTableau().getLast(0), jack);
//        assertEquals(game.getWaste().getLast(), wrongCard);
//        assertFalse(game.isGameWon());
//    }
//
//    @Test
//    public void moveBetweenTableauDecksCompleteTest() {
//        Card king = new Card(Suit.SPADES, Value.KING);
//        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
//        Card jack = new Card(Suit.CLUBS, Value.JACK);
//        Card otherKing = new Card(Suit.CLUBS, Value.KING);
//        Card otherQueen = new Card(Suit.HEART, Value.QUEEN);
//        Tableau tableau = new Tableau(2);
//        tableau.addCards(king, 0);
//        tableau.addCards(queen, 0);
//        tableau.addCards(jack, 0);
//        tableau.addCards(otherKing, 1);
//        tableau.addCards(otherQueen, 1);
//        Game game = new Game(new ArrayList<>(4), new Deck(), tableau);
//        assertTrue(game.moveBetweenTableauDecks(0, 1));
//        assertEquals(game.getTableau().getLast(0), queen);
//        assertEquals(game.getTableau().getLast(1), jack);
//        assertFalse(game.isGameWon());
//    }
//
//    @Test
//    public void moveBetweenTableauDecksFailedTest() {
//        Card king = new Card(Suit.SPADES, Value.KING);
//        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
//        Card wrongJack = new Card(Suit.CLUBS, Value.JACK);
//        Card otherKing = new Card(Suit.CLUBS, Value.KING);
//        Tableau tableau = new Tableau(2);
//        tableau.addCards(king, 0);
//        tableau.addCards(queen, 0);
//        tableau.addCards(wrongJack, 0);
//        tableau.addCards(otherKing, 1);
//        Game game = new Game(new ArrayList<>(4), new Deck(), tableau);
//        assertFalse(game.moveBetweenTableauDecks(0, 1));
//        assertEquals(game.getTableau().getLast(1), otherKing);
//        assertEquals(game.getTableau().getLast(0), wrongJack);
//        assertFalse(game.isGameWon());
//    }
//
//    @Test
//    public void moveFromFoundationToTableauCompleteTest() {
//        Card one = new Card(Suit.DIAMOND, Value.ACE);
//        Card two = new Card(Suit.CLUBS, Value.TWO);
//        Card three = new Card(Suit.HEART, Value.THREE);
//        ArrayList<Foundation> foundations = new ArrayList<>(4);
//        foundations.add(new Foundation(Suit.DIAMOND));
//        foundations.get(0).addCards(one);
//        Tableau tableau = new Tableau(1);
//        tableau.getDeck(0).addCards(three);
//        tableau.addCards(two, 0);
//        Game game = new Game(foundations, new Deck(), tableau);
//        assertTrue(game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND));
//        assertNull(game.getFoundationBySuit(Suit.DIAMOND).getTopCard());
//        assertEquals(game.getTableau().getLast(0), one);
//        assertFalse(game.isGameWon());
//    }
//
//    @Test
//    public void moveFromFoundationToTableauFailedTest() {
//        Card wrongOne = new Card(Suit.DIAMOND, Value.ACE);
//        Card two = new Card(Suit.HEART, Value.TWO);
//        Card three = new Card(Suit.SPADES, Value.THREE);
//        ArrayList<Foundation> foundations = new ArrayList<>(4);
//        foundations.add(new Foundation(Suit.DIAMOND));
//        foundations.get(0).addCards(wrongOne);
//        Tableau tableau = new Tableau(1);
//        tableau.getDeck(0).addCards(three);
//        tableau.addCards(two, 0);
//        Game game = new Game(foundations, new Deck(), tableau);
//        assertFalse(game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND));
//        assertEquals(game.getFoundationBySuit(Suit.DIAMOND).getTopCard(), wrongOne);
//        assertEquals(game.getTableau().getLast(0), two);
//        assertFalse(game.isGameWon());
//    }
//
//
//    @Test
//    public void moveCardSequenceToEmptyDeckCompleteTest1() {
//        Card king = new Card(Suit.SPADES, Value.KING);
//        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
//        Card jack = new Card(Suit.CLUBS, Value.JACK);
//        Tableau tableau = new Tableau(2);
//        tableau.addCards(king, 0);
//        tableau.addCards(queen, 0);
//        tableau.addCards(jack, 0);
//        Game game = new Game(new ArrayList<>(4), new Deck(), tableau);
//        assertTrue(game.moveSequenceInTableau(0, 1, 0));
//        assertEquals(tableau.getDeck(0).size(), 0);
//        assertEquals(tableau.getDeck(1).size(), 3);
//        assertEquals(tableau.getLast(1), jack);
//    }
//
//    @Test
//    public void moveCardSequenceBetweenTableauDecksCompleteTest() {
//        Card otherKing = new Card(Suit.CLUBS, Value.KING);
//        Card king = new Card(Suit.SPADES, Value.KING);
//        Tableau tableau = new Tableau(2);
//        tableau.addCards(king, 0);
//        tableau.addCards(new Card(Suit.DIAMOND, Value.QUEEN), 0);
//        tableau.addCards(new Card(Suit.CLUBS, Value.JACK), 0);
//        tableau.addCards(new Card(Suit.HEART, Value.TEN), 0);
//        tableau.addCards(new Card(Suit.SPADES, Value.NINE), 0);
//        tableau.addCards(new Card(Suit.HEART, Value.EIGHT), 0);
//        tableau.addCards(otherKing, 1);
//        Game game = new Game(new ArrayList<>(4), new Deck(), tableau);
//        boolean didMove = game.moveSequenceInTableau(0, 1, 1);
//        assertTrue(didMove);
//        assertEquals(1, tableau.getDeck(0).size());
//        assertEquals(6, tableau.getDeck(1).size());
//        assertEquals(Value.EIGHT, tableau.getLast(1).getValue());
//        assertEquals(king, tableau.getLast(0));
//    }
//
//
//    @Test
//    public void moveCardSequenceBetweenTableauDecksFailedTest() {
//        Tableau tableau = new Tableau(2);
//        tableau.addCards(new Card(Suit.SPADES, Value.KING), 0);
//        tableau.addCards(new Card(Suit.DIAMOND, Value.QUEEN), 0);
//        tableau.addCards(new Card(Suit.CLUBS, Value.JACK), 0);
//        Game game = new Game(new ArrayList<>(4), new Deck(), tableau);
//        assertFalse(game.moveSequenceInTableau(0, 1, 1));
//    }
//

//    @Test
//    public void lastMoveStockRestTest() {
//        ArrayList<Foundation> foundations = new ArrayList<>();
//        foundations.add(new Foundation(Suit.HEART));
//        foundations.add(new Foundation(Suit.DIAMOND));
//        foundations.add(new Foundation(Suit.SPADES));
//        foundations.add(new Foundation(Suit.CLUBS));
//        Deck stock =new Deck();
//        stock.addCards(foundations.get(3).removeCard());
//        Game game = new Game(foundations, stock, tableau);
//        game.showStockCard();
//        game.showStockCard();
//        assertTrue(game.moveFromStockToFoundation());
//        assertTrue(game.isGameWon());
//        assertEquals(game.getCantMovements(), 3);
//    }

}