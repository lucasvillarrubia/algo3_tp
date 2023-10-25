package Auxiliar;

import static org.junit.Assert.*;

import Base.Card;

import Base.Suit;
import Base.Value;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ColumnTest {


    @Test
    public void testIsEmpty() {
        Column column = new Column();
        assertTrue(column.isEmpty());
        column.addCard(new Card(Suit.HEART, Value.ACE));
        assertFalse(column.isEmpty());
    }

    @Test
    public void testAddCard() {
        Column column = new Column();
        Card card = new Card(Suit.DIAMOND, Value.TWO);
        column.addCard(card);
        assertEquals(card, column.getLast());
    }

    @Test
    public void testGetLast() {
        Column column = new Column();
        assertNull(column.getLast());
        Card card = new Card(Suit.HEART, Value.KING);
        column.addCard(card);
        assertEquals(card, column.getLast());
        column.drawCard();
        assertNull(column.getLast());
    }

    @Test
    public void testDrawCard() {
        Column column = new Column();
        Card card = new Card(Suit.CLUBS, Value.KING);
        column.addCard(card);
        Card drawnCard = column.drawCard();
        assertEquals(card, drawnCard);
        assertTrue(column.isEmpty());
    }

    @Test
    public void testDrawNullCard() {
        Column column = new Column();
        Card card = null;
        column.addCard(card);
        Card drawnCard = column.drawCard();
        assertNull(card);
    }

    @Test
    public void testSize() {
        Column column = new Column();
        assertEquals(0, column.size());
        column.addCard(new Card(Suit.SPADES, Value.QUEEN));
        assertEquals(1, column.size());
    }

    @Test
    public void testGetCard() {
        Column column = new Column();
        Card card = new Card(Suit.HEART, Value.TEN);
        column.addCard(card);
        assertEquals(card, column.getCard(0));
    }

    @Test
    public void testGetAllCards() {
        Column column = new Column();
        List<Card> testCards = new ArrayList<>();
        testCards.add(new Card(Suit.DIAMOND, Value.FIVE));
        testCards.add(new Card(Suit.CLUBS, Value.JACK));
        column.addCard(testCards.get(0));
        column.addCard(testCards.get(1));
        assertEquals(testCards, column.getAllCards());
    }
}
