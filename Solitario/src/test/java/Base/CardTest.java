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
    }

    @Test
    public void flipCard(){
        Card card = new Card(Suit.HEART, Value.ACE);
        card.flip();
        assertTrue(card.isFaceUp());
    }



}