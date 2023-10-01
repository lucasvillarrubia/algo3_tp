package Klondike;

import Base.Card;
import Base.Deck;
import Base.Suit;
import Base.Value;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void initKlondikeGameTest() {
        Game klondike = new Game(1);
        assertFalse(klondike.gameStatus());
        assertFalse(klondike.isGameWon());
        assertEquals(klondike.getCantMovements(),0);

    }

    @Test
    public void initOneStepToWinGameTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        Deck deck = new Deck();
        Tableau tableau = new Tableau(3);
        Game game = new Game(foundations, deck, tableau);
        assertNotNull(game);
        assertFalse(game.gameStatus());
        assertFalse(game.isGameWon());
        assertEquals(0, game.getCantMovements());

    }

    @Test
    public void moveFromTableauToFoundationComplete () {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.HEART));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        Tableau tableau = new Tableau(1);
        tableau.getDeck(0).add(three);
        Game game = new Game(foundations, new Deck(), tableau);
        game.moveFromTableauToFoundation(0);
        assertTrue(game.moveFromTableauToFoundation(0));
        assertTrue(game.getTableau().getDeck(0).isEmpty());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), three);
    }

    @Test
    public void moveFromTableauToFoundationFailed () {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card wrongCard = new Card(Suit.HEART, Value.KING);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.HEART));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        Tableau tableau = new Tableau(1);
        tableau.addCard(wrongCard, 0);
        Game game = new Game(foundations, new Deck(), tableau);
        game.moveFromTableauToFoundation(0);
        assertFalse(game.moveFromTableauToFoundation(0));
        assertEquals(game.getTableau().getLast(0), wrongCard);
        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), two);
    }

    @Test
    public void moveFromStockToFoundationComplete () {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        Card four = new Card(Suit.HEART, Value.FOUR);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.HEART));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        foundations.get(0).addCard(three);
        Deck stock = new Deck();
        stock.addCard(four);
        Game game = new Game(foundations, stock, new Tableau(1));
        game.showStockCard();
        game.moveFromStockToFoundation();
        assertTrue(game.moveFromStockToFoundation());
        assertTrue(game.getStock().isEmpty());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getTopCard(), four);
    }

    @Test
    public void moveFromStockToFoundationFailed () {
        Card one = new Card(Suit.CLUBS, Value.ACE);
        Card two = new Card(Suit.CLUBS, Value.TWO);
        Card three = new Card(Suit.CLUBS, Value.THREE);
        Card wrongCard = new Card(Suit.CLUBS, Value.JACK);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.CLUBS));
        foundations.get(0).addCard(one);
        foundations.get(0).addCard(two);
        foundations.get(0).addCard(three);
        Deck stock = new Deck();
        stock.addCard(wrongCard);
        Game game = new Game(foundations, stock, new Tableau(1));
        game.showStockCard();
        game.moveFromStockToFoundation();
        assertFalse(game.moveFromStockToFoundation());
        assertEquals(game.getWaste().getLast(), wrongCard);
        assertEquals(game.getFoundationBySuit(Suit.CLUBS).getTopCard(), three);
    }

    @Test
    public void moveFromStockToTableauComplete () {
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
        Game game = new Game(new ArrayList<>(4), stock, tableau);
        game.showStockCard();
        game.moveFromStockToTableauDeck(0);
        assertTrue(game.moveFromStockToTableauDeck(0));
        assertTrue(game.getStock().isEmpty());
        assertTrue(game.getWaste().isEmpty());
        assertEquals(game.getTableau().getLast(0), ten);
    }

    @Test
    public void moveFromStockToTableauFailed () {
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
        Game game = new Game(new ArrayList<>(4), stock, tableau);
        game.showStockCard();
        game.moveFromStockToTableauDeck(0);
        assertFalse(game.moveFromStockToTableauDeck(0));
        assertEquals(game.getTableau().getLast(0), jack);
        assertEquals(game.getWaste().getLast(), wrongCard);
    }

    @Test
    public void moveBetweenTableauDecksComplete () {
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
        Game game = new Game(new ArrayList<>(4), new Deck(), tableau);
        game.moveBetweenTableauDecks(0, 1);
        assertTrue(game.moveBetweenTableauDecks(0, 1));
        assertEquals(game.getTableau().getLast(1), jack);
        assertEquals(game.getTableau().getLast(0), otherQueen);
    }

    @Test
    public void moveBetweenTableauDecksFailed () {
        Card king = new Card(Suit.SPADES, Value.KING);
        Card queen = new Card(Suit.DIAMOND, Value.QUEEN);
        Card wrongJack = new Card(Suit.CLUBS, Value.JACK);
        Card otherKing = new Card(Suit.CLUBS, Value.KING);
        Tableau tableau = new Tableau(2);
        tableau.addCard(king, 0);
        tableau.addCard(queen, 0);
        tableau.addCard(wrongJack, 0);
        tableau.addCard(otherKing, 1);
        Game game = new Game(new ArrayList<>(4), new Deck(), tableau);
        game.moveBetweenTableauDecks(0, 1);
        assertFalse(game.moveBetweenTableauDecks(0, 1));
        assertEquals(game.getTableau().getLast(1), otherKing);
        assertEquals(game.getTableau().getLast(0), wrongJack);
    }

    @Test
    public void moveFromFoundationToTableauComplete () {
        Card one = new Card(Suit.DIAMOND, Value.ACE);
        Card two = new Card(Suit.CLUBS, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.DIAMOND));
        foundations.get(0).addCard(one);
        Tableau tableau = new Tableau(1);
        tableau.getDeck(0).add(three);
        tableau.addCard(two, 0);
        Game game = new Game(foundations, new Deck(), tableau);
        game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND);
        assertTrue(game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND));
        assertNull(game.getFoundationBySuit(Suit.DIAMOND).getTopCard());
        assertEquals(game.getTableau().getLast(0), one);
    }

    @Test
    public void moveFromFoundationToTableauFailed () {
        Card wrongOne = new Card(Suit.DIAMOND, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.SPADES, Value.THREE);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.DIAMOND));
        foundations.get(0).addCard(wrongOne);
        Tableau tableau = new Tableau(1);
        tableau.getDeck(0).add(three);
        tableau.addCard(two, 0);
        Game game = new Game(foundations, new Deck(), tableau);
        game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND);
        assertFalse(game.moveFromFoundationToTableauDeck(0, Suit.DIAMOND));
        assertEquals(game.getFoundationBySuit(Suit.DIAMOND).getTopCard(), wrongOne);
        assertEquals(game.getTableau().getLast(0), two);
    }


    // jjsjs te los regalo
//    @Test
//    public void moveCardSequenceBetweenTableauDecksComplete () {
//
//    }
//
//    @Test
//    public void moveCardSequenceBetweenTableauDecksFailed () {
//
//    }


//    Acciones en juego:
//    Inicializar el juego a partir de una semilla aleatoria (done)
//    Inicializar el juego en un estado particular
//    Verificar si el estado actual es de “juego ganado” (done)
//    Hacer un movimiento, y verificar si es válido o no (done)

    //
//        @Test
//        public void initDeckTest(){
//                Stock stock = new Stock();
//                stock.initStock();
//                assertEquals(stock.cardCount(), 52);
//        }
////
////        @Test
////        public void stockRemovedLastCardAndNowIsEmpty () {
////                Stock stock = new Stock();
////                stock.initStock();
////                while (!stock.isEmpty()){
////                        stock.showCard();
////                        stock.drawCard();
////                }
////                assertTrue(stock.isEmpty());
////        }
////
////        @Test
////        public void stockResetTest () {
////                Stock stock = new Stock();
////                stock.initStock();
////                stock.showCard();
////                stock.showCard();
////                stock.showCard();
////                stock.showCard();
////                stock.reset();
////                assertTrue(stock.noCardOnDisplay());
////        }
////
////        @Test
////        public void stockDisplayOneCard () {
////                Stock stock = new Stock();
////                stock.initStock();
////                stock.showCard();
////                assertFalse(stock.noCardOnDisplay());
////        }
}