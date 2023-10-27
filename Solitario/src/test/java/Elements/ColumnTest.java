package Elements;

import Base.Card;
import Base.Suit;
import Base.Value;
import org.junit.Test;


import static org.junit.Assert.*;

public class ColumnTest {

    @Test
    public void getCardTest() {
        Column column = new Column();
        Card card1 = new Card(Suit.HEART, Value.ACE);
        Card card2 = new Card(Suit.DIAMOND, Value.KING);

        column.addCards(card1);
        column.addCards(card2);

        assertEquals(card1, column.getCard(1));
        assertEquals(card2, column.getCard(0));
    }

    @Test
    public void testGetSequence() {
        Column column =new Column();
        Card card1 = new Card(Suit.HEART, Value.ACE);
        Card card2 = new Card(Suit.DIAMOND, Value.KING);
        Card card3 = new Card(Suit.CLUBS, Value.QUEEN);

        column.addCards(card1);
        column.addCards(card2);
        column.addCards(card3);

        Column subColumn = column.getSequence(3);

        //REVISAR!!!
        assertNotNull(subColumn);
        assertEquals(3, subColumn.cardCount());
        assertEquals(card1, subColumn.getCard(2));
        assertEquals(card2, subColumn.getCard(1));
        assertEquals(card3, subColumn.getCard(0));
    }

    @Test
    public void testAddCards() {
        Column column =new Column();
        Card card1 = new Card(Suit.SPADES, Value.JACK);
        Card card2 = new Card(Suit.CLUBS, Value.TEN);

        assertTrue(column.addCards(card1));
        assertTrue(column.addCards(card2));

        assertEquals(2, column.cardCount());
        assertTrue(column.getCard(0).isTheSameAs(card2));
        assertTrue(column.getCard(1).isTheSameAs(card1));
    }

    @Test
    public void isEmptyTest() {
        Column column = new Column();
        assertTrue(column.isEmpty());
        column.addCards(new Card(Suit.HEART, Value.ACE));
        assertFalse(column.isEmpty());
    }

    @Test
    public void addCardTest() {
        Column column = new Column();
        Card card = new Card(Suit.DIAMOND, Value.TWO);
        column.addCards(card);
        assertEquals(card, column.getLast());
    }



    @Test
    public void getLastTest() {
        Column column = new Column();
        assertNull(column.getLast());
        Card card = new Card(Suit.HEART, Value.KING);
        column.addCards(card);
        assertEquals(card, column.getLast());
        column.drawCard();
        assertNull(column.getLast());
    }

    @Test
    public void drawCardTest() {
        Column column = new Column();
        Card card = new Card(Suit.CLUBS, Value.KING);
        column.addCards(card);
        Card drawnCard = column.drawCard();
        assertEquals(card, drawnCard);
        assertTrue(column.isEmpty());
    }

    @Test
    public void drawNullCardTest() {
        Column column = new Column();
        Card card = null;
        column.addCards(card);
        Card drawnCard = column.drawCard();
        assertNull(card);
    }

    @Test
    public void sizeTest() {
        Column column = new Column();
        assertEquals(0, column.cardCount());
        column.addCards(new Card(Suit.SPADES, Value.QUEEN));
        assertEquals(1, column.cardCount());
    }

    @Test
    public void testRemoveCard() {
        Column column = new Column();
        Card card1 = new Card(Suit.HEART, Value.ACE);
        column.addCards(card1);
        assertTrue(column.removeCard(card1));
        assertEquals(0, column.cardCount());
    }

    @Test
    public void testRemoveCardFromColumn() {
        Column column = new Column();
        Card card1 = new Card(Suit.HEART, Value.ACE);
        Card card2 = new Card(Suit.SPADES, Value.KING);
        column.addCards(card1);
        column.addCards(card2);
        Column cardsToRemove = new Column();
        cardsToRemove.addCards(card2);
        assertTrue(column.removeCard(cardsToRemove));
        assertEquals(1, column.cardCount());
        assertEquals(card1, column.getCard(0));
    }

}


