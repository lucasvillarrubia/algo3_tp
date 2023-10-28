package GameType;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Stock;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpiderRulesTest {

    @Test
    public void isSequenceValidTest() {
        SpiderRules rules = new SpiderRules();
        assertTrue(rules.isSequenceValid(new Card(Suit.SPADES, Value.THREE),new Card(Suit.SPADES, Value.FOUR)));
    }
    @Test
    public void isSequenceNotValidTest() {
        SpiderRules rules = new SpiderRules();
        assertFalse(rules.isSequenceValid(new Card(Suit.SPADES, Value.EIGHT),new Card(Suit.SPADES, Value.KING)));
    }

    @Test
    public void stockAcceptsCardTest() {
        Stock stock = new Stock();
        SpiderRules spiderRules = new SpiderRules();
        assertFalse(spiderRules.acceptsCard(stock, new Card(Suit.SPADES, Value.NINE)));
    }

    @Test
    public void emptyFoundationAcceptCardTest() {
        Foundation foundation = new Foundation(Suit.SPADES);
        Card aceOfHearts = new Card(Suit.SPADES, Value.ACE);
        SpiderRules spiderRules = new SpiderRules();
        assertFalse(spiderRules.acceptsCard(foundation, aceOfHearts));
    }

    @Test
    public void emptyColumnAccptCards() {
        Column column = new Column();
        Card kingOfhearts = new Card(Suit.SPADES, Value.KING);
        SpiderRules spiderRules = new SpiderRules();
        assertTrue(column.acceptCard(spiderRules, kingOfhearts));
    }


    @Test
    public void columnAccptCardsTest() {
        Column column = new Column();
        Card kingOfSpades = new Card(Suit.SPADES, Value.KING);
        Card queenOfSpades = new Card(Suit.SPADES, Value.QUEEN);
        Card jackOfSpades = new Card(Suit.SPADES, Value.JACK);
        Card tenOfSpades = new Card(Suit.SPADES, Value.TEN);
        SpiderRules spiderRules = new SpiderRules();
        column.acceptCard(spiderRules, kingOfSpades);
        column.acceptCard(spiderRules, queenOfSpades);
        column.acceptCard(spiderRules, jackOfSpades);
        assertTrue(spiderRules.acceptsCard(column, tenOfSpades));
    }

    @Test
    public void columnAccptWrongCardsTest() {
        Column column = new Column();
        Card kingOfSpades = new Card(Suit.SPADES, Value.KING);
        Card queenOfSpades = new Card(Suit.SPADES, Value.QUEEN);
        Card jackOfSpades = new Card(Suit.SPADES, Value.JACK);
        Card twoOfSpades = new Card(Suit.SPADES, Value.TEN);
        SpiderRules spiderRules = new SpiderRules();
        column.acceptCard(spiderRules, kingOfSpades);
        column.acceptCard(spiderRules, queenOfSpades);
        column.acceptCard(spiderRules, jackOfSpades);
        assertFalse(spiderRules.acceptsCard(column, twoOfSpades));
    }


    @Test
    public void emptystockGiveCardTest(){
        Stock stock = new Stock();
        SpiderRules spiderRules = new SpiderRules();
        assertFalse(spiderRules.givesCard(stock));
    }

    @Test
    public void drawCardFromStockTest() {

    }
}