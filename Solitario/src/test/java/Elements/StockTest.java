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
        assertFalse(stock.isFilling());
        assertEquals(0, stock.cardCount());
    }

   //FILL PERTENECE  A LAS REGLAS

//    @Test
//    public void stockFillingTest() {
//        Stock stock = new Stock();
//        stock.fill();
//        //assertTrue(stock.wasFilled());
//        assertEquals(52, stock.cardCount());
//    }
//
//
//    @Test
//    public void shuffleTest() {
//        Stock stock = new Stock();
//        stock.fill();
//        Stock shuffledStock = new Stock();
//        shuffledStock.fill();
//        shuffledStock.shuffle(10);
//        assertNotEquals(stock.toString(), shuffledStock.toString());
//    }

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

}