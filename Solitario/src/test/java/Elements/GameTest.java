package Elements;

import Base.Card;
import Base.Suit;
import Base.Value;
import GameType.KlondikeRules;
import GameType.SpiderRules;
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
    @Test
    public void completeSerializationTest() throws IOException, ClassNotFoundException {
        KlondikeRules kr = new KlondikeRules();
        Stock stock = kr.initStock();
        int cant = 20;
        List<Foundation> foundations = kr.initFoundations();
        List<Column> tableau = new ArrayList<>();
        Game game = new Game(kr, false, false, cant, stock, foundations, tableau);
        game.addMovement();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        game.serialize(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        Game deserializedGame = Game.deserialize(inputStream);
        assertNotNull(deserializedGame);
        assertEquals(deserializedGame.getCantMovements(), 21);
        assertNotNull(deserializedGame.getFoundationBySuit(Suit.HEART));
        assertTrue(deserializedGame.getStock().getLast().isTheSameAs(new Card(Suit.CLUBS,Value.KING)));
    }

    //Testeos de Game con Klondike

    @Test
    public void klondikeGameTest(){
        KlondikeRules klondikeRules = new KlondikeRules();
        Game game = new Game(klondikeRules, 10);
        assertEquals(game.getStock().cardCount(), 24);
        for(int i = 0; i< 7;i++){
            assertNotNull(game.getColumn(i));
            assertEquals(game.getColumn(i).cardCount(), i+1);
        }
        assertFalse(game.isGameWon());
        assertFalse(game.isGameOver());
        assertTrue(game.moveCards(game.getColumn(0), game.getColumn(4)));
        assertTrue(game.moveCards(game.getColumn(5), game.getFoundationBySuit(Suit.CLUBS)));
        assertTrue(game.drawCardFromStock());
        assertTrue(game.drawCardFromStock());
        assertFalse(game.moveCards(game.getColumn(1),game.getColumn(2), 1 ));
        //assertEquals(game.getStock().cardCount(), 22);
    }


    //Testeos de Game con Spider

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
        assertTrue(game.moveCards(game.getColumn(0), game.getColumn(5)));
        assertFalse(game.moveCards(game.getColumn(2), game.getColumn(7)));
        assertTrue(game.moveCards(game.getColumn(4), game.getColumn(1)));
        assertFalse(game.moveCards(game.getColumn(2), game.getColumn(9), 3));
        assertTrue(game.moveCards(game.getColumn(1), game.getColumn(9), 1));
        assertTrue(game.moveCards(game.getColumn(4), game.getColumn(1)));
        assertEquals(game.getCantMovements(), 4);
        //assertEquals(game.getStock().cardCount(), 41);
    }

}