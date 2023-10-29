package Elements;

import Base.Card;
import Base.Value;
import GameType.KlondikeRules;
import GameType.SpiderRules;
import org.junit.Test;
import Base.Suit;


import static org.junit.Assert.*;

public class StockTest {

    @Test
    public void stockInitEmptyTest() {
        Stock stock = new Stock();
        assertTrue(stock.isEmpty());
        assertFalse(stock.isFilling());
        assertEquals(0, stock.cardCount());
    }


    @Test
    public void testContains() {
        Stock stock = new Stock();
        Card testCard1 = new Card(Suit.HEART, Value.ACE);
        Card testCard2 = new Card(Suit.HEART, Value.TWO);
        Card testCard3 = new Card(Suit.HEART, Value.THREE);
        Card testCard4 = new Card(Suit.HEART, Value.FOUR);
        stock.addCards(testCard1);
        stock.addCards(testCard2);
        stock.addCards(testCard3);
        stock.addCards(testCard4);
        assertTrue(stock.containsCard(testCard2));
    }

    @Test
    public void sameSeedShuffleTest(){
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();

        Card cardPrueba1 = new Card(Suit.HEART, Value.KING);
        Card cardPrueba2 = new Card(Suit.DIAMOND, Value.NINE);
        Card cardPrueba3 = new Card(Suit.CLUBS, Value.NINE);
        Card cardPrueba4 = new Card(Suit.HEART, Value.TWO);
        stock1.addCards(cardPrueba1);
        stock1.addCards(cardPrueba2);
        stock1.addCards(cardPrueba3);
        stock1.addCards(cardPrueba4);
        stock2.addCards(cardPrueba1);
        stock2.addCards(cardPrueba2);
        stock2.addCards(cardPrueba3);
        stock2.addCards(cardPrueba4);
        stock1.shuffle(1);
        stock2.shuffle(1);
        assertEquals(stock1.getLast().getValue(), stock2.getLast().getValue());
    }

    @Test
    public void acceptCardKlondikeTest() {
        KlondikeRules k=new KlondikeRules();
        Stock stock = k.initStock();
        assertTrue(stock.acceptCard(k, new Card(Suit.CLUBS, Value.SEVEN)));
    }

    @Test
    public void acceptSequenceTest() {
        KlondikeRules klondikeRules = new KlondikeRules();
        Stock s = new Stock();
        Column column = new Column();
        column.acceptCard(klondikeRules, new Card(Suit.CLUBS, Value.TEN));
        column.acceptCard(klondikeRules, new Card(Suit.HEART, Value.FIVE));
        assertFalse(s.acceptSequence(klondikeRules, column));
    }


//revisar para klondike hay algo que no termina de cerrar :/
    @Test
    public void givesCardTest() {
//        KlondikeRules klondikeRules = new KlondikeRules();
//        Stock sKlondike = klondikeRules.initStock();
//        assertTrue(sKlondike.givesCard(klondikeRules));
        SpiderRules spiderRules = new SpiderRules();
        Stock sSpider = spiderRules.initStock();
        assertTrue(sSpider.givesCard(spiderRules));
    }


    @Test
    public void emptyStockGivesCardTest() {
        KlondikeRules klondikeRules = new KlondikeRules();
        SpiderRules spiderRules = new SpiderRules();
        Stock s = new Stock();
        assertFalse(s.givesCard(klondikeRules));
        assertFalse(s.givesCard(spiderRules));
    }

    @Test
    public void acceptCardTest() {
        KlondikeRules klondikeRules = new KlondikeRules();
        Stock s = new Stock();
        Card c = new Card(Suit.SPADES, Value.QUEEN);
        s.acceptCard(klondikeRules, c);
        assertFalse(s.acceptCard(klondikeRules, c));
    }
}