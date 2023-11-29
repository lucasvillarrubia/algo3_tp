package Elements;

import Base.Card;
import Base.Color;
import Base.Suit;
import Base.Value;
import GameType.KlondikeRules;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class FoundationTest {

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
        f.drawCard();
        f.drawCard();
        f.drawCard();
        assertFalse(f.isFull());
    }

    @Test
    public void addCardTest() {
        Foundation foundation = new Foundation(Suit.HEART);
        Card aceOfHearts = new Card(Suit.HEART, Value.ACE);
        assertTrue(foundation.addCards(aceOfHearts));
        assertEquals(aceOfHearts, foundation.getLast());
    }

    @Test
    public void cardsCanMoveToEmptyFoundationTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
        assertTrue(f.addCards(aceOfDiamond));
        Card aceOfSpades = new Card(Suit.SPADES, Value.ACE);
        Foundation f2 = new Foundation(Suit.DIAMOND);
        assertTrue(f2.addCards(aceOfSpades));
    }

    @Test
    public void cardsCanMoveToNotEmptyFoundationTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
        Card twoOfDiamonds = new Card(Suit.DIAMOND, Value.TWO);
        Card threeOfDiamonds = new Card(Suit.DIAMOND, Value.THREE);
        assertTrue(f.addCards(aceOfDiamond));
        assertTrue(f.addCards(twoOfDiamonds));
        assertTrue(f.addCards(threeOfDiamonds));
    }

    @Test
    public void cardsCantMoveWrongSuitTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfSpades = new Card(Suit.SPADES, Value.ACE);
        assertTrue(f.addCards(aceOfSpades));
    }

    @Test
    public void cardsCantMoveWrongValueTest() {
        Foundation f = new Foundation(Suit.HEART);
        KlondikeRules klondikeRules = new KlondikeRules();
        Card aceOfHeart = new Card(Suit.HEART, Value.ACE);
        Card tenOfHeart = new Card(Suit.HEART, Value.TEN);
        f.addCards(aceOfHeart);
        assertFalse(klondikeRules.acceptsCard(f, tenOfHeart));
    }

    @Test
    public void addSequenceTest() {
        Column cards = new Column();
        Card card1 = new Card(Suit.SPADES, Value.ACE);
        Card card2 = new Card(Suit.SPADES, Value.TWO);
        cards.addCards(card1);
        cards.addCards(card2);
        Foundation to = new Foundation(Suit.SPADES);
        ArrayList<Card> cardsCollection = new ArrayList<>();
        for (int i = cards.cardCount() - 1; i >= 0;  i--) {
            cardsCollection.add(0, cards.getCard(i));
        }
        assertTrue(to.addCards(cardsCollection));
    }


}