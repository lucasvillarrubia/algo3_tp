package Base;

import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {

    @Test
    public void initDeckTest(){
        Stock stock = new Stock();
        stock.initStock();
        assertEquals(stock.cardCount(), 52);
    }

    @Test
    public void shuffleTest(){
        Stock stock = new Stock();
        stock.initStock();
        Stock shuffledStock = new Stock();
        shuffledStock.initStock();
        shuffledStock.shuffle(1);
        stock.showCard();
        assertNotEquals(stock.getLast(), shuffledStock.getLast());
    }

    @Test
    public void sameSeedShuffleTest(){
        Stock stock1 = new Stock();
        stock1.initStock();
        stock1.shuffle(1);
        Stock stock2 = new Stock();
        stock2.initStock();
        stock2.shuffle(1);
        stock1.showCard();
        stock2.showCard();
        assertEquals(stock1.getLast().getValue(), stock2.getLast().getValue());
    }

    @Test
    public void stockRemovedLastCardAndNowIsEmpty () {
        Stock stock = new Stock();
        stock.initStock();
        while (!stock.isEmpty()){
            stock.showCard();
            stock.drawCard();
        }
        assertTrue(stock.isEmpty());
    }

    @Test
    public void stockResetTest () {
        Stock stock = new Stock();
        stock.initStock();
        stock.showCard();
        stock.showCard();
        stock.showCard();
        stock.showCard();
        stock.reset();
        assertTrue(stock.noCardOnDisplay());
    }

    @Test
    public void stockDisplayOneCard () {
        Stock stock = new Stock();
        stock.initStock();
        stock.showCard();
        assertFalse(stock.noCardOnDisplay());
    }

    @Test
    public void emptyStockDoesntShowCard () {
        Stock stock = new Stock();
        assertFalse(stock.showCard());
    }

}