package Klondike;


import Base.Card;
import Base.Suit;
import Base.Value;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TableauTest {

    @Test
    public void initializeTableauTest() {
        Tableau tableau = new Tableau(2);
        assertTrue(tableau.isEmpty());
    }
    @Test
    public void tableauWithCardsNotEmptyTest() {
        Tableau tableau = new Tableau(1);
        Card card = new Card(Suit.HEART, Value.NINE);
        tableau.getDeck(0).add(card);
        assertFalse(tableau.isEmpty());
    }

    @Test
    public void addCardToEmptyDeckTest() {
        Tableau tableau = new Tableau(2);
        Card kingOfHearts = new Card(Suit.HEART, Value.KING);
        assertTrue(tableau.canReceive(kingOfHearts, 0));
        tableau.addCard(kingOfHearts, 0);
        assertFalse(tableau.isEmpty());
    }

    @Test
    public void addInvalidCardToEmptyDeckTest() {
        Tableau tableau = new Tableau(2);
        Card queenOfSpades = new Card(Suit.SPADES, Value.QUEEN);
        assertFalse(tableau.canReceive(queenOfSpades, 0));
        tableau.addCard(queenOfSpades, 0);
        assertTrue(tableau.isEmpty());
    }

    @Test
    public void emptyDeckGoodCardAdditionTest() {
        Tableau tableau = new Tableau(2);
        Card newCard = new Card(Suit.DIAMOND, Value.KING);
        tableau.getDeck(0).add(newCard);
        assertTrue(tableau.canReceive(newCard, 1));
        tableau.addCard(newCard, 1);
        assertTrue(tableau.getLast(1).isTheSameAs(newCard));
    }

    @Test
    public void drawCardFromEmptyColumnTest() {
        Tableau tableau = new Tableau(3);
        tableau.addCard(new Card(Suit.HEART, Value.KING), 0);
        tableau.addCard(new Card(Suit.SPADES, Value.QUEEN), 1);
        Card drawnCard = tableau.drawCard(2);
        assertNull(drawnCard);
    }

    @Test
    public void moveDeckLastCardToAnotherTableauDeckTest() {
        Tableau tableau = new Tableau(2);
        List fromDeck = tableau.getDeck(0);
        List toDeck = tableau.getDeck(1);
        Card lastCard = new Card(Suit.HEART, Value.NINE);
        Card swappedCard = new Card(Suit.CLUBS, Value.EIGHT);
        fromDeck.add(swappedCard);
        toDeck.add(lastCard);
        assertTrue(tableau.canReceive(swappedCard,1));
        tableau.addCard(tableau.drawCard(0), 1);
        assertTrue(fromDeck.isEmpty());
        assertTrue(tableau.getLast(1).isTheSameAs(swappedCard));
    }



    @Test
    public void wrongCardExchangeBySuitBetweenTableauDecksTest() {
        Tableau tableau = new Tableau(2);
        Card wrongCard = new Card(Suit.DIAMOND, Value.EIGHT);
        Card lastCard = new Card(Suit.HEART, Value.NINE);
        tableau.getDeck(0).add(wrongCard);
        tableau.getDeck(1).add(lastCard);
        assertFalse(tableau.canReceive(wrongCard, 1));
        tableau.addCard(wrongCard, 1);
        assertFalse(tableau.getLast(1).isTheSameAs(wrongCard));
    }

    @Test
    public void wrongCardExchangeByValueBetweenTableauDecksTest() {
        //arrange
        Tableau tableau = new Tableau(2);
        Card lastCard = new Card(Suit.CLUBS, Value.TWO);
        Card wrongCard = new Card(Suit.DIAMOND, Value.EIGHT);
        //act
        tableau.getDeck(0).add(wrongCard);
        tableau.getDeck(1).add(lastCard);
        assertFalse(tableau.canReceive(wrongCard, 1));
        tableau.addCard(wrongCard, 1);
        assertFalse(tableau.getLast(1).isTheSameAs(wrongCard));
    }

    @Test
    public void emptyDeckRejectedWrongCardTest() {
        //arrange
        Tableau tableau = new Tableau(1);
        Card wrongNewCard = new Card(Suit.CLUBS, Value.EIGHT);
        //act
        tableau.addCard(wrongNewCard, 0);
        //assert
        assertTrue(tableau.getDeck(0).isEmpty());
    }

    @Test
    public void testAddCardSequenceValid() {
        Tableau tableau = new Tableau(1);
        tableau.addCard(new Card(Suit.HEART, Value.KING), 0);
        tableau.addCard(new Card(Suit.SPADES, Value.QUEEN), 0);
        List<Card> cardsToAdd = new ArrayList<>();
        cardsToAdd.add(new Card(Suit.HEART, Value.JACK));
        cardsToAdd.add(new Card(Suit.CLUBS, Value.TEN));
        cardsToAdd.add(new Card(Suit.DIAMOND, Value.NINE));
        tableau.addCardSequence(cardsToAdd, 0);
        List<Card> deck = tableau.getDeck(0);
        assertEquals(5, deck.size());
        assertEquals(Value.NINE, deck.get(deck.size() - 1).getValue());
        assertEquals(Value.TEN, deck.get(deck.size() - 2).getValue());
        assertEquals(Value.JACK, deck.get(deck.size() -3).getValue());
    }


}