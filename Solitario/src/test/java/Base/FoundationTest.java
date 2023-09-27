package Base;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoundationTest {

    @Test
    public void initializeFoundationTest() {
        //arrange
        Suit clubs = Suit.CLUBS;
        Foundation foundation = new Foundation(clubs);
        //act
        String black = Suit.CLUBS.getColor();
        Suit foundationSuit = foundation.getSuit();
        String foundationColor = foundation.getSuit().getColor();
        //assert
        assertEquals(black,foundationColor);
        assertEquals(clubs,foundationSuit);
    }

    @Test
    public void addCorrectCardWhenEmpty() {
        Foundation foundation = new Foundation(Suit.SPADES);
        Card card = new Card(Suit.SPADES, Value.TWO);
        foundation.addCard(card);
        assertEquals(foundation.getTopCard(),card);
    }

    @Test
    public void addCard(){

    }

    @Test
    public void removeCard() {
    }

    @Test
    public void removeCardWhenEmpty() {
    }

}