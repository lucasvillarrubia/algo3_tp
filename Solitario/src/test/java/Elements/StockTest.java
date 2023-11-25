package Elements;

import Base.Card;
import Base.Value;
import org.junit.Test;
import Base.Suit;


import static org.junit.Assert.*;

public class StockTest {

    @Test
    public void stockInitEmptyTest() {
        Stock stock = new Stock();
        assertTrue(stock.isEmpty());
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

        Card cardTest1 = new Card(Suit.HEART, Value.KING);
        Card cardTest2 = new Card(Suit.DIAMOND, Value.NINE);
        Card cardTest3 = new Card(Suit.CLUBS, Value.NINE);
        Card cardTest4 = new Card(Suit.HEART, Value.TWO);
        stock1.addCards(cardTest1);
        stock1.addCards(cardTest2);
        stock1.addCards(cardTest3);
        stock1.addCards(cardTest4);
        stock2.addCards(cardTest1);
        stock2.addCards(cardTest2);
        stock2.addCards(cardTest3);
        stock2.addCards(cardTest4);
        stock1.shuffle(1);
        stock2.shuffle(1);
        assertEquals(stock1.getLast().getValue(), stock2.getLast().getValue());
    }

    @Test
    public void addCardTest() {
        Stock stock = new Stock();
        assertTrue(stock.addCards(new Card(Suit.CLUBS, Value.SEVEN)));
    }


}