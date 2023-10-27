package Elements;

import Base.Deck;
import Base.Card;
import Base.Color;
import Base.Suit;
import Base.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class FoundationTest {
//    public class P extends Deck {
//        @Override
//        public boolean addCards(Card card){
//            return super.addCards(card);
//        }
//    }

    @Test
    public void initializeFoundationTest() {
        Suit clubs = Suit.CLUBS;
        Foundation foundation = new Foundation(clubs);
        Color black = Suit.CLUBS.getColor();
        Suit foundationSuit = foundation.getSuit();
        Color foundationColor = foundation.getSuit().getColor();
        assertEquals(black,foundationColor);
        assertEquals(clubs,foundationSuit);
    }

    @Test
    public void addCorrectCardWhenEmptyTest() {
        Foundation foundation = new Foundation(Suit.SPADES);
        Card card = new Card(Suit.SPADES, Value.ACE);
        foundation.addCards(card);
        assertEquals(foundation.getLast(),card);
    }


    @Test
    public void removeCardTest(){
        Foundation f = new Foundation(Suit.CLUBS);
        Card aceOfClubs = new Card(Suit.CLUBS, Value.ACE);
        f.addCards(aceOfClubs);
        assertEquals(f.drawCard(),aceOfClubs);
        assertNull(f.getLast());
    }

    @Test
    public void fullFoundationTest(){
        Foundation f = new Foundation(Suit.DIAMOND);
        for(Value value : Value.values()){
            Card card = new Card(Suit.DIAMOND, value);
            f.addCards(card);
        }
        assertTrue(f.isFull());
    }

    @Test
    public void notFullFoundationTest(){
        Foundation f = new Foundation(Suit.DIAMOND);
        for(Value value : Value.values()){
            Card card = new Card(Suit.DIAMOND, value);
            f.addCards(card);
        }
        f.removeCard(f.getLast());
        f.removeCard(f.getLast());
        f.removeCard(f.getLast());
        assertFalse(f.isFull());
    }

    @Test
    public void addCardTest() {
        Foundation foundation = new Foundation(Suit.HEART);
        Card aceOfHearts = new Card(Suit.HEART, Value.ACE);
        assertTrue(foundation.addCards(aceOfHearts));
        assertEquals(aceOfHearts, foundation.getLast());
    }


    //    @Test
//    public void dontAddCorrectCardWhenEmptyTest() {
//        Foundation foundation = new Foundation(Suit.SPADES);
//        Card wrongCard = new Card(Suit.SPADES, Value.EIGHT);
//        foundation.addCards(wrongCard);
//        assertNull(foundation.getLast());
//    }

//    @Test
//    public void cardsCanMoveToEmptyFoundationTest() {
//        Foundation f = new Foundation(Suit.DIAMOND);
//        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
//        assertTrue(f.acceptsCard(aceOfDiamond));
//    }

//    @Test
//    public void cardsCanMoveToNotEmptyFoundationTest() {
//        Foundation f = new Foundation(Suit.DIAMOND);
//        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
//        Card twoOfDiamonds = new Card(Suit.DIAMOND, Value.TWO);
//        Card threeOfDiamonds = new Card(Suit.DIAMOND, Value.THREE);
//        f.addCards(aceOfDiamond);
//        f.addCards(twoOfDiamonds);
//        assertTrue(f.canReceive(threeOfDiamonds));
//    }

//    @Test
//    public void cardsCantMoveWrongSuitTest() {
//        Foundation f = new Foundation(Suit.DIAMOND);
//        Card aceOfSpades = new Card(Suit.SPADES, Value.ACE);
//        assertFalse(f.canReceive(aceOfSpades));
//    }

//    @Test
//    public void cardsCantMoveWrongValueTest() {
//        Foundation f = new Foundation(Suit.HEART);
//        Card aceOfHeart = new Card(Suit.HEART, Value.ACE);
//        Card tenOfHeart = new Card(Suit.HEART, Value.TEN);
//        f.addCard(aceOfHeart);
//        assertFalse(f.canReceive(tenOfHeart));
//    }
}