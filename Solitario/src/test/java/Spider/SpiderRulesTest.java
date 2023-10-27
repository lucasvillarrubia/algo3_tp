package Spider;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;

import Elements.Game;
import Elements.Stock;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpiderRulesTest {

    @Test
    public void emptyFoundationAcceptCardTest() {
//        Foundation foundation = new Foundation(Suit.HEART);
//        Card aceOfHearts = new Card(Suit.HEART, Value.ACE);
//        SpiderRules spiderRules = new SpiderRules();
//        assertTrue(spiderRules.acceptsCard(foundation, aceOfHearts));
    }

    @Test
    public void emptyfoundationAcceptWrongCardTest() {
//        Foundation foundation = new Foundation(Suit.HEART);
//        Card tenOfHearts = new Card(Suit.HEART, Value.TEN);
//        SpiderRules spiderRules = new SpiderRules();
//        assertFalse(spiderRules.acceptsCard(foundation, tenOfHearts));
    }

    @Test
    public void foundationAcceptCardTest() {
//        Foundation foundation = new Foundation(Suit.HEART);
//        Card aceOfHearts = new Card(Suit.HEART, Value.ACE);
//        SpiderRules spiderRules = new SpiderRules();
//        foundation.acceptCard(spiderRules, aceOfHearts);
//        foundation.acceptCard(spiderRules, new Card(Suit.HEART, Value.TWO));
//        foundation.acceptCard(spiderRules, new Card(Suit.HEART, Value.THREE));
//        assertTrue(spiderRules.acceptsCard(foundation,new Card(Suit.HEART, Value.FOUR) ));
//        foundation.acceptCard(spiderRules, new Card(Suit.HEART, Value.FOUR));
//        assertFalse(spiderRules.acceptsCard(foundation,new Card(Suit.HEART, Value.SEVEN)));
    }



    @Test
    public void emptyColumnAccptCards() {
        Column column = new Column();
        Card kingOfhearts = new Card(Suit.HEART, Value.KING);
        SpiderRules spiderRules = new SpiderRules();
        assertTrue(column.acceptCard(spiderRules, kingOfhearts));
    }

    @Test
    public void columnAccptWrongCardsTest() {
        Column column = new Column();
        Card tenOfhearts = new Card(Suit.HEART, Value.TEN);
        SpiderRules spiderRules = new SpiderRules();
        assertFalse(spiderRules.acceptsCard(column, tenOfhearts));
    }

    @Test
    public void columnAccptCardsTest() {
        Column column = new Column();
        Card kingOfhearts = new Card(Suit.HEART, Value.KING);
        Card queenOfSpades = new Card(Suit.SPADES, Value.QUEEN);
        Card jackOfDiamond = new Card(Suit.DIAMOND, Value.JACK);
        Card tenOfClubs = new Card(Suit.CLUBS, Value.TEN);
        SpiderRules spiderRules = new SpiderRules();
        column.acceptCard(spiderRules, kingOfhearts);
        column.acceptCard(spiderRules, queenOfSpades);
        column.acceptCard(spiderRules, jackOfDiamond);
        assertTrue(spiderRules.acceptsCard(column, tenOfClubs));
    }

    @Test
    public void emptystockGiveCardTest(){
        Stock stock = new Stock();
        SpiderRules spiderRules = new SpiderRules();
        assertFalse(spiderRules.givesCard(stock));
    }

    @Test
    public void stockGiveCardTest(){
        Stock stock = new Stock();
        SpiderRules spiderRules = new SpiderRules();
        stock.acceptCard(spiderRules, new Card(Suit.DIAMOND, Value.EIGHT));
        assertFalse(spiderRules.givesCard(stock));
    }

    @Test
    public void gameStatusTest(){
        SpiderRules spiderRules = new SpiderRules();
        Game game =  new Game(spiderRules, 12);
        assertFalse(spiderRules.checkGameStatus(game));
    }

    @Test
    public void initSpiderGame() {
        SpiderRules spiderRules = new SpiderRules();
        Game game = new Game(spiderRules, 10);
        //ver que verificar
    }
}