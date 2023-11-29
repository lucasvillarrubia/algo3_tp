package Elements;

import Base.Card;
import Base.Suit;
import Base.Value;
import GameType.KlondikeRules;
import GameType.SpiderRules;
import Solitaire.Game;
import Solitaire.Movement;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void buildGameWIthStockAndFoundation() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        Stock stock = new Stock();
        ArrayList<Column> tableau = new ArrayList<>();
        KlondikeRules k = new KlondikeRules();
        Game game = new Game(k, foundations,tableau,stock);
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
        KlondikeRules k = new KlondikeRules();
        Game game = new Game(k, foundations,tableau, stock);
        assertFalse(game.gameStatus());
        game.winGame();
        assertTrue(game.gameStatus());
        assertTrue(game.isGameWon());
        assertTrue(game.isGameOver());
    }

    @Test
    public void wrongSequenceMoveKlondikeTest() {
        KlondikeRules k= new KlondikeRules();
        List<Column> tableau = new ArrayList<>();
        List<Foundation> emptyFoundations = new ArrayList<>();
        Stock stock = new Stock();
        Column cards = new Column();
        Column wrongSequence = new Column();

        Card card1 = new Card(Suit.HEART, Value.KING);
        Card card2 = new Card(Suit.SPADES, Value.QUEEN);
        Card card3 = new Card(Suit.SPADES, Value.TEN);
        Card card4 = new Card(Suit.HEART, Value.NINE);
        Card card5 = new Card(Suit.CLUBS, Value.EIGHT);

        card1.flip();
        card2.flip();
        card3.flip();
        card4.flip();
        card5.flip();

        cards.addCards(card1);
        cards.addCards(card2);
        wrongSequence.addCards(card3);
        wrongSequence.addCards(card4);
        wrongSequence.addCards(card5);

        tableau.add(cards);
        tableau.add(wrongSequence);

        Game game = new Game(k, emptyFoundations, tableau, stock);
        assertFalse(game.makeAMove(new Movement(game.getColumn(1), game.getColumn(0), 2)));
    }

    @Test
    public void addMovementTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        Stock stock = new Stock();
        ArrayList<Column> tableau = new ArrayList<>();
        KlondikeRules k = new KlondikeRules();
        Game game = new Game(k,foundations,tableau, stock);
        game.addMovement();
        assertEquals(1, game.getCantMovements());
    }

    @Test
    public void getFoundationBySuitTest() {
        ArrayList<Foundation> foundations  = new ArrayList<>();
        Stock stock = new Stock();
        ArrayList<Column> tableau = new ArrayList<>();
        foundations.add(new Foundation(Suit.CLUBS));
        KlondikeRules k = new KlondikeRules();
        Game game = new Game(k,foundations,tableau, stock);
        assertNotNull(game.getFoundation(Suit.CLUBS));
    }

    @Test
    public void fullFoundationsTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        foundations.add(new Foundation(Suit.HEART));
        foundations.add(new Foundation(Suit.DIAMOND));
        foundations.add(new Foundation(Suit.SPADES));
        foundations.add(new Foundation(Suit.CLUBS));
        ArrayList<Column> tableau = new ArrayList<>();
        KlondikeRules k = new KlondikeRules();
        Game game = new Game(k,foundations,tableau , new Stock());
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
        KlondikeRules k = new KlondikeRules();
        Game game = new Game(k, foundations, tableau,new Stock());
        for (Foundation foundation : foundations) {
            Suit suit = foundation.getSuit();
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                foundation.addCards(card);
            }
        }
        game.getFoundation(Suit.SPADES).drawCard();
        assertFalse(game.areAllFoundationsFull());
    }


    @Test public void serializationTestNew() throws IOException, ClassNotFoundException {
        ArrayList<Foundation> foundations = new ArrayList<>();
        foundations.add(new Foundation(Suit.CLUBS));
        ArrayList<Column> tableau = new ArrayList<>();
        KlondikeRules k = new KlondikeRules();
        Game game = new Game(k, foundations, tableau, new Stock());
        game.addMovement();
        File saveFile = new File("savedGame.txt");
        game.serialize();
        Game deserializedGame = Game.deserialize(saveFile);
        assertNotNull(deserializedGame);
        assertEquals(deserializedGame.getCantMovements(), 1);
    }


    @Test
    public void completeSerializationTest() throws IOException, ClassNotFoundException {
        KlondikeRules kr = new KlondikeRules();
        Stock stock = kr.initStock();
        int cant = 20;
        List<Foundation> foundations = kr.initFoundations();
        List<Column> tableau = new ArrayList<>();
        Game game = new Game(kr, false, false, cant,stock, foundations, tableau);
        game.addMovement();
        game.serialize();
        File saveFile = new File("savedGame.txt");
        Game deserializedGame = Game.deserialize(saveFile);
        assertNotNull(deserializedGame);
        assertEquals(deserializedGame.getCantMovements(), 21);
        assertNotNull(deserializedGame.getFoundation(Suit.HEART));
        assertTrue(deserializedGame.getStock().getLast().isTheSameAs(new Card(Suit.CLUBS,Value.KING)));
    }

    //Tests de Game con Klondike

    @Test
    public void klondikeGameTest() throws IOException, ClassNotFoundException {
        KlondikeRules klondikeRules = new KlondikeRules();
        Game game = new Game(klondikeRules, 10);
        assertEquals(game.getStock().cardCount(), 24);
        for(int i = 0; i< 7;i++){
            assertNotNull(game.getColumn(i));
            assertEquals(game.getColumn(i).cardCount(), i+1);
        }
        assertFalse(game.isGameWon());
        assertFalse(game.isGameOver());
        assertTrue(game.makeAMove(new Movement(game.getColumn(0), game.getColumn(4))));
        assertTrue(game.makeAMove(new Movement(game.getColumn(5), game.getFoundation(Suit.CLUBS))));
        assertTrue(game.drawCardFromStock());
        assertTrue(game.drawCardFromStock());
        assertFalse(game.makeAMove(new Movement(game.getColumn(2), game.getStock(), 1)));
        assertFalse(game.makeAMove(new Movement(game.getColumn(2), game.getStock())));
        assertFalse(game.makeAMove(new Movement(game.getColumn(1),game.getColumn(2), 1 )));

        game.serialize();
        File saveFile = new File("savedGame.txt");
        game = Game.deserialize(saveFile);
        assertEquals(4, game.getCantMovements());

        assertTrue(game.getFoundation(Suit.CLUBS).getLast().isTheSameAs(new Card(Suit.CLUBS, Value.ACE)));
    }

    //Tests de Game con Spider

    @Test
    public void spiderGameTest() {
        SpiderRules spiderRules = new SpiderRules();
        Game game = new Game(spiderRules, 10);
        assertEquals(game.getStock().cardCount(), 50);
        for(int i = 0; i<10;i++){
            assertNotNull(game.getColumn(i));
        }
        for(int i = 0; i< 4; i++){
            assertEquals(game.getColumn(i).cardCount(),6);
        }
        for(int i = 5; i<10; i++){
            assertEquals(game.getColumn(i).cardCount(),5);
        }
        assertFalse(game.isGameWon());
        assertTrue(game.drawCardFromStock());
        assertEquals(game.getStock().cardCount(), 40);
        assertFalse(game.makeAMove(new Movement(game.getStock(), game.getColumn(0))));
        assertFalse(game.makeAMove(new Movement(game.getFoundation(Suit.SPADES), game.getColumn(0))));
        assertTrue(game.makeAMove(new Movement(game.getColumn(0), game.getColumn(5))));
        assertFalse(game.makeAMove(new Movement(game.getColumn(2), game.getColumn(7))));
        assertTrue(game.makeAMove(new Movement(game.getColumn(4), game.getColumn(1))));
        assertFalse(game.makeAMove(new Movement(game.getColumn(2), game.getColumn(9), 3)));
        assertTrue(game.makeAMove(new Movement(game.getColumn(1), game.getColumn(9), 1)));
        assertFalse(game.makeAMove(new Movement(game.getColumn(4), game.getColumn(1))));

    }

}