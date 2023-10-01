package Klondike;

import Base.Card;
import Base.Suit;
import Base.Value;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoundationTest {

    @Test
    public void initializeFoundationTest() {
        Suit clubs = Suit.CLUBS;
        Foundation foundation = new Foundation(clubs);
        String black = Suit.CLUBS.getColor();
        Suit foundationSuit = foundation.getSuit();
        String foundationColor = foundation.getSuit().getColor();
        assertEquals(black,foundationColor);
        assertEquals(clubs,foundationSuit);
    }

    @Test
    public void addCorrectCardWhenEmptyTest() {
        Foundation foundation = new Foundation(Suit.SPADES);
        Card card = new Card(Suit.SPADES, Value.ACE);
        foundation.addCard(card);
        assertEquals(foundation.getTopCard(),card);
    }

    @Test
    public void dontAddCorrectCardWhenEmptyTest() {
        Foundation foundation = new Foundation(Suit.SPADES);
        Card wrongCard = new Card(Suit.SPADES, Value.EIGHT);
        foundation.addCard(wrongCard);
        assertNull(foundation.getTopCard());
    }

    @Test
    public void cardsCanMoveToEmptyFoundationTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
        assertTrue(f.canReceive(aceOfDiamond));
    }

    @Test
    public void cardsCanMoveToNotEmptyFoundationTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
        Card twoOfDiamonds = new Card(Suit.DIAMOND, Value.TWO);
        Card threeOfDiamonds = new Card(Suit.DIAMOND, Value.THREE);
        f.addCard(aceOfDiamond);
        f.addCard(twoOfDiamonds);
        assertTrue(f.canReceive(threeOfDiamonds));
    }

    @Test
    public void cardsCantMoveWrongSuitTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfSpades = new Card(Suit.SPADES, Value.ACE);
        assertFalse(f.canReceive(aceOfSpades));
    }

    @Test
    public void cardsCantMoveWrongValueTest() {
        Foundation f = new Foundation(Suit.HEART);
        Card aceOfHeart = new Card(Suit.HEART, Value.ACE);
        Card tenOfHeart = new Card(Suit.HEART, Value.TEN);
        f.addCard(aceOfHeart);
        assertFalse(f.canReceive(tenOfHeart));
    }

    @Test
    public void removeCardTest(){
        Foundation f = new Foundation(Suit.CLUBS);
        Card aceOfClubs = new Card(Suit.CLUBS, Value.ACE);
        f.addCard(aceOfClubs);
        assertEquals(f.removeCard(),aceOfClubs);
        assertNull(f.getTopCard());
    }

    @Test
    public void fullFoundationTest(){
        Foundation f = new Foundation(Suit.DIAMOND);
        for(Value value : Value.values()){
            Card card = new Card(Suit.DIAMOND, value);
            f.addCard(card);
        }
        assertTrue(f.isFull());
    }

    @Test
    public void notFullFoundationTest(){
        Foundation f = new Foundation(Suit.DIAMOND);
        for(Value value : Value.values()){
            Card card = new Card(Suit.DIAMOND, value);
            f.addCard(card);
        }
        f.removeCard();
        f.removeCard();
        f.removeCard();
        assertFalse(f.isFull());
    }


}