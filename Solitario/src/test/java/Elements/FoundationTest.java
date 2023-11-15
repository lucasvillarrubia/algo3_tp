package Elements;

import Base.Card;
import Base.Color;
import Base.Suit;
import Base.Value;
import GameType.KlondikeRules;
import org.junit.Test;


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

//    @Test
//    public void dontAddWrongCardWhenEmptyTest() {
//        Foundation foundation = new Foundation(Suit.SPADES);
//        Card wrongCard = new Card(Suit.SPADES, Value.EIGHT);
//        foundation.addCards(wrongCard);
//        assertNull(foundation.getLast());
//    }

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
    public void acceptSequenceKlondikeTest() {
        Foundation foundation = new Foundation(Suit.HEART);
        Card card1 = new Card(Suit.HEART, Value.KING);
        Card card2 = new Card(Suit.HEART, Value.QUEEN);
        Card card3 = new Card(Suit.HEART, Value.JACK);
        Column cards = new Column();
        cards.addCards(card1);
        cards.addCards(card2);
        cards.addCards(card3);
        assertFalse(foundation.addCards(cards));
    }

    @Test
    public void acceptSequenceSpiderTest() {
        Foundation f = new Foundation(Suit.SPADES);
        Column cards = new Column();
        cards.addCards(new Card(Suit.SPADES, Value.KING));
        cards.addCards(new Card(Suit.SPADES, Value.QUEEN));
        cards.addCards(new Card(Suit.SPADES, Value.JACK));
        cards.addCards(new Card(Suit.SPADES, Value.TEN));
        cards.addCards(new Card(Suit.SPADES, Value.NINE));
        cards.addCards(new Card(Suit.SPADES, Value.EIGHT));
        cards.addCards(new Card(Suit.SPADES, Value.SEVEN));
        cards.addCards(new Card(Suit.SPADES, Value.SIX));
        cards.addCards(new Card(Suit.SPADES, Value.FIVE));
        cards.addCards(new Card(Suit.SPADES, Value.FOUR));
        cards.addCards(new Card(Suit.SPADES, Value.THREE));
        cards.addCards(new Card(Suit.SPADES, Value.TWO));
        cards.addCards(new Card(Suit.SPADES, Value.ACE));
        assertTrue(f.addCards(cards));
        cards.drawCard();
        assertFalse(f.addCards(cards));
        cards.addCards(new Card(Suit.SPADES, Value.FOUR));
        assertFalse(f.addCards(cards));
    }

//    @Test
//    public void givesCardKlondikeTest() {
//        Foundation foundation = new Foundation(Suit.HEART);
//        foundation.addCards(new Card(Suit.HEART, Value.ACE));
//        foundation.addCards(new Card(Suit.HEART, Value.TWO));
//        foundation.addCards(new Card(Suit.HEART, Value.THREE));
//        assertTrue(foundation.givesCard(klondikeRules));
//    }
//
//    @Test
//    public void givesCardSpiderTest() {
//        Foundation foundation = new Foundation(Suit.SPADES);
//        for(Value v: Value.values()){
//            foundation.acceptCard(spiderRules, new Card(Suit.SPADES, v));
//        }
//        assertFalse(foundation.givesCard(spiderRules));
//    }

}