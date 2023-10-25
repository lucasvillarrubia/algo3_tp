package Klondike;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import Spider.SpiderRules;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KlondikeRulesTest {


    @Test
    public void columnAcceptCardTest() {
        Column column = new Column();
        KlondikeRules gameRules = new KlondikeRules();
        Card card = new Card(Suit.HEART, Value.KING);
        assertTrue(gameRules.acceptsCard(column, card));
        column.acceptCard(gameRules, card);
        assertEquals(1, column.cardCount());
        assertEquals(card, column.getCard(0));
    }

    @Test
    public void columnAcceptWrongCardTest() {
        Column column = new Column();
        KlondikeRules gameRules = new KlondikeRules();
        Card invalidCard = new Card(Suit.DIAMOND, Value.NINE);
        assertFalse(gameRules.acceptsCard(column, invalidCard));
    }

    @Test
    public void foundationAcceptCardTest() {
        Foundation foundation = new Foundation(Suit.HEART);
        KlondikeRules gameRules = new KlondikeRules();
        Card card = new Card(Suit.HEART, Value.ACE);
        assertTrue(gameRules.acceptsCard(foundation, card));
        foundation.acceptCard(gameRules, card);
        assertEquals(1, foundation.cardCount());
        assertEquals(card, foundation.getLast());
    }

    @Test
    public void foundationAcceptWrongCardTest() {
        Foundation foundation = new Foundation(Suit.DIAMOND);
        KlondikeRules gameRules = new KlondikeRules();
        Card invalidCard = new Card(Suit.DIAMOND, Value.NINE);
        assertFalse(gameRules.acceptsCard(foundation, invalidCard));
        assertTrue(foundation.isEmpty());
    }

    @Test
    public void stockAcceptCardTest() {
        Stock stock = new Stock();
        KlondikeRules gameRules = new KlondikeRules();
        Card card = new Card(Suit.HEART, Value.QUEEN);
        assertTrue(gameRules.acceptsCard(stock, card));
        stock.acceptCard(gameRules, card);
        assertEquals(1, stock.cardCount());
        assertEquals(card, stock.getLast());
    }

    @Test
    public void fullStockAcceptWrongCardTest() {
        KlondikeRules gameRules = new KlondikeRules();
        Stock stock = gameRules.initStock();
        Card card = new Card(Suit.DIAMOND, Value.NINE);
        assertFalse(gameRules.acceptsCard(stock, card));
        //assertTrue(stock.wasFilled()); ->CREO QUE DEPENDE DE GAMES!!!! OJO
        assertFalse(stock.isEmpty());
    }





    //revisar tests (son los ex-tests de game)

    @Test
    public void initKlondikeGameTest() {
        KlondikeRules rules = new KlondikeRules();
        Game game = new Game(rules, 1);
        rules.gameInit(game);
        assertFalse(game.gameStatus());
        assertFalse(game.isGameWon());
        assertEquals(game.getCantMovements(),0);
        for(Suit s: Suit.values()){
            assertNotNull(game.getFoundationBySuit(s));
        }
    }

    @Test
    public void moveFromTableauToFoundationCompleteTest() {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.HEART));
        KlondikeRules rules = new KlondikeRules();
        foundations.get(0).acceptCard(rules,one);
        foundations.get(0).acceptCard(rules, two);
        foundations.get(0).acceptCard(rules, three);
        Game game = new Game(foundations, new Stock());
        assertFalse(game.isGameWon());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getLast(), three);
    }

    //testear o no los metodos de givesCard (dependeniendo de que definimos)

    //admitsSequence(Column column, Column sequence)


    @Test
    public void columnAdmitsSequenceTest() {
        KlondikeRules rules = new KlondikeRules();
        Column column = new Column();
        List<Card> cardsSeq = new ArrayList<>();
        cardsSeq.add(new Card(Suit.SPADES, Value.QUEEN));
        cardsSeq.add(new Card(Suit.HEART, Value.JACK));
        cardsSeq.add(new Card(Suit.SPADES, Value.TEN));
        cardsSeq.add(new Card(Suit.HEART, Value.NINE));
        //column.acceptCard(rules, cardsSeq);


    }

    @Test
    public void gameStatusTest(){
        KlondikeRules rules = new KlondikeRules();
        Game game =  new Game(rules, 12);
        assertFalse(rules.checkGameStatus(game));
    }


}