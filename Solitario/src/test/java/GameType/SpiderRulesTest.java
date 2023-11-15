package GameType;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Solitaire.Game;
import Elements.Stock;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpiderRulesTest {

    @Test
    public void isSequenceValidTest() {
        SpiderRules rules = new SpiderRules();
        assertTrue(rules.isSequenceValid(new Card(Suit.SPADES, Value.FOUR),new Card(Suit.SPADES, Value.THREE)));
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
    public void emptyColumnAcceptCards() {
        Column column = new Column();
        Card kingOfHearts = new Card(Suit.SPADES, Value.KING);
        SpiderRules spiderRules = new SpiderRules();
        assertTrue(spiderRules.acceptsCard(column, kingOfHearts));
    }


    @Test
    public void columnAcceptCardsTest() {
        Column column = new Column();
        Card kingOfSpades = new Card(Suit.SPADES, Value.KING);
        Card queenOfSpades = new Card(Suit.SPADES, Value.QUEEN);
        Card jackOfSpades = new Card(Suit.SPADES, Value.JACK);
        Card tenOfSpades = new Card(Suit.SPADES, Value.TEN);
        SpiderRules spiderRules = new SpiderRules();
        column.addCards(kingOfSpades);
        column.addCards(queenOfSpades);
        column.addCards(jackOfSpades);
        assertTrue(spiderRules.acceptsCard(column, tenOfSpades));
    }

    @Test
    public void columnAcceptWrongCardsTest() {
        Column column = new Column();
        Card kingOfSpades = new Card(Suit.SPADES, Value.KING);
        Card queenOfSpades = new Card(Suit.SPADES, Value.QUEEN);
        Card jackOfSpades = new Card(Suit.SPADES, Value.JACK);
        Card twoOfSpades = new Card(Suit.SPADES, Value.TWO);
        SpiderRules spiderRules = new SpiderRules();
        column.addCards(kingOfSpades);
        column.addCards(queenOfSpades);
        column.addCards(jackOfSpades);
        assertFalse(spiderRules.acceptsCard(column, twoOfSpades));
    }


    @Test
    public void emptyStockGiveCardTest(){
        Stock stock = new Stock();
        SpiderRules spiderRules = new SpiderRules();
        assertFalse(spiderRules.givesCard(stock));
    }


    @Test
    public void stockGivesCardTest() {
        SpiderRules spiderRules = new SpiderRules();
        Stock stock = new Stock();
        stock.addCards(new Card(Suit.SPADES, Value.NINE));
        stock.addCards(new Card(Suit.SPADES, Value.EIGHT));
        stock.addCards(new Card(Suit.SPADES, Value.FOUR));
        assertFalse(spiderRules.givesCard(stock));
    }

    @Test
    public void stockAdmitsSequenceTest() {
        SpiderRules spiderRules = new SpiderRules();
        Stock stock = new Stock();
        Column c= new Column();
        c.addCards(new Card(Suit.SPADES, Value.NINE));
        c.addCards(new Card(Suit.SPADES, Value.EIGHT));
        c.addCards(new Card(Suit.SPADES, Value.FOUR));
        assertFalse(spiderRules.admitsSequence(stock, c));
    }

    @Test
    public void emptyColumnAdmitsSequenceTest() {
        SpiderRules spiderRules = new SpiderRules();
        Column cards = new Column();
        Column column= new Column();
        Card c1 = new Card(Suit.SPADES, Value.KING);
        Card c2 = new Card(Suit.SPADES, Value.QUEEN);
        Card c3 = new Card(Suit.SPADES, Value.JACK);
        cards.addCards(c1);
        cards.addCards(c2);
        cards.addCards(c3);
        assertTrue(spiderRules.admitsSequence(column, cards));
    }

    @Test
    public void spiderGameInitTest() {
        SpiderRules spiderRules = new SpiderRules();
        Game game = new Game(spiderRules, 10);
        assertNotNull(game.getStock());
        assertEquals(game.getStock().cardCount(),50);
        for(int i = 0; i<10; i++){
            assertNotNull(game.getColumn(i));
        }
        for(int i = 0; i<10;i++){
            assertNotNull(game.getColumn(i));
        }
        for(int i = 0; i< 4; i++){
            assertEquals(game.getColumn(i).cardCount(),6);
        }
        for(int i = 5; i<10; i++){
            assertEquals(game.getColumn(i).cardCount(),5);
        }
        assertTrue(game.getColumn(0).getLast().isTheSameAs(new Card(Suit.SPADES, Value.NINE)));

    }

    @Test
    public void acceptSequenceSpiderTest() {
        SpiderRules sr = new SpiderRules();
        Foundation f = new Foundation(Suit.SPADES);
        Column cards = new Column();
        cards.addCards(new Card(Suit.SPADES, Value.KING));
        cards.addCards(new Card(Suit.SPADES, Value.QUEEN));
        cards.addCards(new Card(Suit.SPADES, Value.JACK));
        cards.addCards(new Card(Suit.SPADES, Value.TEN));
        cards.addCards(new Card(Suit.SPADES, Value.NINE));
        cards.addCards(new Card(Suit.SPADES, Value.EIGHT));
        cards.addCards(new Card(Suit.SPADES, Value.SEVEN));
        cards.addCards(new Card(Suit.SPADES, Value.SIX));
        cards.addCards(new Card(Suit.SPADES, Value.FIVE));
        cards.addCards(new Card(Suit.SPADES, Value.FOUR));
        cards.addCards(new Card(Suit.SPADES, Value.THREE));
        cards.addCards(new Card(Suit.SPADES, Value.TWO));
        cards.addCards(new Card(Suit.SPADES, Value.ACE));
        assertTrue(sr.admitsSequence(f, cards));
        cards.drawCard();
        assertFalse(sr.admitsSequence(f, cards));
        cards.addCards(new Card(Suit.SPADES, Value.ACE));
        cards.addCards(new Card(Suit.SPADES, Value.FOUR));
        assertFalse(sr.admitsSequence(f, cards));
    }

    @Test
    public void notDrawCardFromStockTest() {
        SpiderRules spiderRules = new SpiderRules();
        ArrayList<Foundation> foundations = new ArrayList<>();
        Stock stock = spiderRules.initStock();
        ArrayList<Column> tableau = new ArrayList<>();
        for(int i =0; i <10; i++){
            tableau.add(0, new Column());
        }
        Game game = new Game(spiderRules,foundations,tableau,stock);
        assertEquals(game.getStock().cardCount(),104);
        assertFalse(spiderRules.drawCardFromStock(stock, tableau));
    }


}