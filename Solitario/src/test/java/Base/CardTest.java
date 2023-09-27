package Base;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void cardInit(){
        //arrange//act
        Card card = new Card(Suit.HEART, Value.ACE);
        //assert
        assertFalse(card.isFaceUp());
        assertEquals(card.getSuit(), Suit.HEART);
        assertEquals(card.getValue(), Value.ACE);
    }

    @Test
    public void flipCard(){
        Card card = new Card(Suit.HEART, Value.ACE);
        card.flip();
        assertTrue(card.isFaceUp());
    }


    @Test
    public void compareTwoCards(){
        Card card1= new Card(Suit.SPADES, Value.SEVEN);
        Card card2= new Card(Suit.SPADES, Value.SEVEN);
        assertTrue(card1.isTheSameAs(card2));
    }


}