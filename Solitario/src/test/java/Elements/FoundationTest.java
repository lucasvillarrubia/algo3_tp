package Elements;

import Base.Card;
import Base.Color;
import Base.Suit;
import Base.Value;
import GameType.KlondikeRules;
import GameType.SpiderRules;
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
        KlondikeRules klondikeRules = new KlondikeRules();
        for(Value value : Value.values()){
            Card card = new Card(Suit.DIAMOND, value);
            f.acceptCard(klondikeRules, card);
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
        KlondikeRules klondikeRules = new KlondikeRules();
        assertTrue(foundation.acceptCard(klondikeRules, aceOfHearts));
        assertEquals(aceOfHearts, foundation.getLast());
    }

    @Test
    public void dontAddWrongCardWhenEmptyTest() {
        Foundation foundation = new Foundation(Suit.SPADES);
        Card wrongCard = new Card(Suit.SPADES, Value.EIGHT);
        KlondikeRules klondikeRules = new KlondikeRules();
        foundation.acceptCard(klondikeRules, wrongCard);
        assertNull(foundation.getLast());
    }

    @Test
    public void cardsCanMoveToEmptyFoundationTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
        KlondikeRules klondikeRules = new KlondikeRules();
        assertTrue(f.acceptCard(klondikeRules, aceOfDiamond));
        Card aceOfSpades = new Card(Suit.SPADES, Value.ACE);
        Foundation f2 = new Foundation(Suit.DIAMOND);
        SpiderRules spiderRules = new SpiderRules();
        assertFalse(f2.acceptCard(spiderRules, aceOfSpades));
    }

    @Test
    public void cardsCanMoveToNotEmptyFoundationTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfDiamond = new Card(Suit.DIAMOND, Value.ACE);
        Card twoOfDiamonds = new Card(Suit.DIAMOND, Value.TWO);
        Card threeOfDiamonds = new Card(Suit.DIAMOND, Value.THREE);
        f.addCards(aceOfDiamond);
        f.addCards(twoOfDiamonds);
        KlondikeRules klondikeRules = new KlondikeRules();
        assertTrue(f.acceptCard(klondikeRules,threeOfDiamonds));
    }

    @Test
    public void cardsCantMoveWrongSuitTest() {
        Foundation f = new Foundation(Suit.DIAMOND);
        Card aceOfSpades = new Card(Suit.SPADES, Value.ACE);
        KlondikeRules klondikeRules = new KlondikeRules();
        assertFalse(f.acceptCard(klondikeRules, aceOfSpades));
    }

    @Test
    public void cardsCantMoveWrongValueTest() {
        Foundation f = new Foundation(Suit.HEART);
        Card aceOfHeart = new Card(Suit.HEART, Value.ACE);
        Card tenOfHeart = new Card(Suit.HEART, Value.TEN);
        KlondikeRules klondikeRules = new KlondikeRules();
        f.acceptCard(klondikeRules, aceOfHeart);
        assertFalse(f.acceptCard(klondikeRules,tenOfHeart));
    }


    @Test
    public void givesCardKlondikeTest() {
        Foundation foundation = new Foundation(Suit.HEART);
        KlondikeRules klondikeRules = new KlondikeRules();
        foundation.acceptCard(klondikeRules, new Card(Suit.HEART, Value.ACE));
        foundation.acceptCard(klondikeRules, new Card(Suit.HEART, Value.TWO));
        foundation.acceptCard(klondikeRules, new Card(Suit.HEART, Value.THREE));
        assertTrue(foundation.givesCard(klondikeRules));
    }

    @Test
    public void givesCardSpiderTest() {
        Foundation foundation = new Foundation(Suit.SPADES);
        SpiderRules spiderRules = new SpiderRules();
        for(Value v: Value.values()){
            foundation.acceptCard(spiderRules, new Card(Suit.SPADES, v));
        }
        assertFalse(foundation.givesCard(spiderRules));
    }
}