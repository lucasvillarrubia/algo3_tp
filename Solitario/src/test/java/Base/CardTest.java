package Base;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void cardInitTest(){
        //arrange//act
        Card card = new Card(Suit.HEART, Value.ACE);
        //assert
        assertFalse(card.isFaceUp());
        assertEquals(card.getSuit(), Suit.HEART);
        assertTrue(card.getValue().equals(Value.ACE));
    }

    @Test
    public void flipCardTest(){
        Card card = new Card(Suit.HEART, Value.ACE);
        assertFalse(card.isFaceUp());
        card.flip();
        assertTrue(card.isFaceUp());
    }


    @Test
    public void compareTwoCardsTest(){
        Card card1= new Card(Suit.SPADES, Value.SEVEN);
        Card card2= new Card(Suit.SPADES, Value.SEVEN);
        assertTrue(card1.isTheSameAs(card2));
    }


}