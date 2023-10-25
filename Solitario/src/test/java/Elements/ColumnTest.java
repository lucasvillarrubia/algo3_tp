package Elements;

import Auxiliar.Column;
import Base.Card;
import Base.Suit;
import Base.Value;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ColumnTest {

    @Test
    public void testIsEmpty() {
        Auxiliar.Column column = new Auxiliar.Column();
        assertTrue(column.isEmpty());
        column.addCard(new Card(Suit.HEART, Value.ACE));
        assertFalse(column.isEmpty());
    }

    @Test
    public void testAddCard() {
        Auxiliar.Column column = new Auxiliar.Column();
        Card card = new Card(Suit.DIAMOND, Value.TWO);
        column.addCard(card);
        assertEquals(card, column.getLast());
    }

    @Test
    public void testGetLast() {
        Auxiliar.Column column = new Auxiliar.Column();
        assertNull(column.getLast());
        Card card = new Card(Suit.HEART, Value.KING);
        column.addCard(card);
        assertEquals(card, column.getLast());
        column.drawCard();
        assertNull(column.getLast());
    }

    @Test
    public void testDrawCard() {
        Auxiliar.Column column = new Auxiliar.Column();
        Card card = new Card(Suit.CLUBS, Value.KING);
        column.addCard(card);
        Card drawnCard = column.drawCard();
        assertEquals(card, drawnCard);
        assertTrue(column.isEmpty());
    }

    @Test
    public void testDrawNullCard() {
        Auxiliar.Column column = new Auxiliar.Column();
        Card card = null;
        column.addCard(card);
        Card drawnCard = column.drawCard();
        assertNull(card);
    }

    @Test
    public void testSize() {
        Auxiliar.Column column = new Auxiliar.Column();
        assertEquals(0, column.size());
        column.addCard(new Card(Suit.SPADES, Value.QUEEN));
        assertEquals(1, column.size());
    }

    @Test
    public void testGetCard() {
        Auxiliar.Column column = new Auxiliar.Column();
        Card card = new Card(Suit.HEART, Value.TEN);
        column.addCard(card);
        assertEquals(card, column.getCard(0));
    }

    @Test
    public void testGetAllCards() {
        Auxiliar.Column column = new Column();
        List<Card> testCards = new ArrayList<>();
        testCards.add(new Card(Suit.DIAMOND, Value.FIVE));
        testCards.add(new Card(Suit.CLUBS, Value.JACK));
        column.addCard(testCards.get(0));
        column.addCard(testCards.get(1));
        assertEquals(testCards, column.getAllCards());
    }
}