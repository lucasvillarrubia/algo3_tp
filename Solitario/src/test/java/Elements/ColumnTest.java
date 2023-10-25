package Elements;

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
        Column column = new Column();
        assertTrue(column.isEmpty());
        column.addCards(new Card(Suit.HEART, Value.ACE));
        assertFalse(column.isEmpty());
    }

    @Test
    public void testAddCard() {
        Column column = new Column();
        Card card = new Card(Suit.DIAMOND, Value.TWO);
        column.addCards(card);
        assertEquals(card, column.getLast());
    }

    @Test
    public void testGetLast() {
        Column column = new Column();
        assertNull(column.getLast());
        Card card = new Card(Suit.HEART, Value.KING);
        column.addCards(card);
        assertEquals(card, column.getLast());
        column.drawCard();
        assertNull(column.getLast());
    }

    @Test
    public void testDrawCard() {
        Column column = new Column();
        Card card = new Card(Suit.CLUBS, Value.KING);
        column.addCards(card);
        Card drawnCard = column.drawCard();
        assertEquals(card, drawnCard);
        assertTrue(column.isEmpty());
    }

    @Test
    public void testDrawNullCard() {
        Column column = new Column();
        Card card = null;
        column.addCards(card);
        Card drawnCard = column.drawCard();
        assertNull(card);
    }

    @Test
    public void testSize() {
        Column column = new Column();
        assertEquals(0, column.cardCount());
        column.addCards(new Card(Suit.SPADES, Value.QUEEN));
        assertEquals(1, column.cardCount());
    }

    @Test
    public void testGetCard() {
        Column column = new Column();
        Card card = new Card(Suit.HEART, Value.TEN);
        column.addCards(card);
        assertEquals(card, column.getCard(0));
    }

    @Test
    public void testGetAllCards() {
        Column column = new Column();
        List<Card> testCards = new ArrayList<>();
        testCards.add(new Card(Suit.DIAMOND, Value.FIVE));
        testCards.add(new Card(Suit.CLUBS, Value.JACK));
        column.addCards(testCards.get(1));
        column.addCards(testCards.get(0));
        assertTrue(column.getCard(0).isTheSameAs(testCards.get(0)));
        assertTrue(column.getCard(1).isTheSameAs(testCards.get(1)));
    }
}


