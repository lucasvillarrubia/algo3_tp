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
        assertFalse(stock.wasFilled());
        assertEquals(0, stock.cardCount());
    }

    @Test
    public void stockFillingTest() {
        Stock stock = new Stock();
        stock.fill();
        //assertTrue(stock.wasFilled());
        assertEquals(52, stock.cardCount());
    }


    @Test
    public void shuffleTest() {
        Stock stock = new Stock();
        stock.fill();
        Stock shuffledStock = new Stock();
        shuffledStock.fill();
        shuffledStock.shuffle(10);
        assertNotEquals(stock.toString(), shuffledStock.toString());
    }

//    @Test
//    public void testContains() {
//        Stock stock = new Stock();
//        stock.fill();
//        Card testCard = new Card(Suit.HEART, Value.ACE);
//        assertTrue(stock.contains(testCard));
//    }

//    @Test
//    public void sameSeedShuffleTest(){
//        Stock stock1 = new Stock();
//        Stock stock2 = new Stock();
//
//        Card cardPrueba1 = new Card(Suit.HEART, Value.KING);
//        Card cardPrueba2 = new Card(Suit.DIAMOND, Value.NINE);
//        Card cardPrueba3 = new Card(Suit.CLUBS, Value.NINE);
//        Card cardPrueba4 = new Card(Suit.HEART, Value.TWO);
//        stock1.acceptsCard(cardPrueba1);
//        stock1.addCard(cardPrueba2);
//        stock1.addCard(cardPrueba3);
//        stock1.addCard(cardPrueba4);
//        stock2.addCard(cardPrueba1);
//        stock2.addCard(cardPrueba2);
//        stock2.addCard(cardPrueba3);
//        stock2.addCard(cardPrueba4);
//        stock1.shuffle(1);
//        stock2.shuffle(1);
//        assertEquals(stock1.getLast().getValue(), stock2.getLast().getValue());
//    }

}