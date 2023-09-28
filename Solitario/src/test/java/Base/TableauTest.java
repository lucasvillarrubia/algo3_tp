package Base;


import org.junit.Test;

import static org.junit.Assert.*;

public class TableauTest {

    @Test
    public void initializeTableauTest() {
        Tableau tableau = new Tableau();

    }

    @Test
     public void moveDeckLastCardToAnotherTableauDeck() {
         //arrange
        Tableau tableau = new Tableau();
        Deck fromDeck = new Deck();
        Deck toDeck = new Deck();
        Card lastCard = new Card(Suit.HEART, Value.NINE);
        Card swappedCard = new Card(Suit.CLUBS, Value.EIGHT);
        //act
        fromDeck.addCard(swappedCard);
        toDeck.addCard(lastCard);
        tableau.addPile(fromDeck);
        tableau.addPile(toDeck);
        tableau.swapCard(fromDeck, toDeck);
        //assert
        //System.out.println(tableau.getDeck(0).getLast().isTheSameAs(swappedCard));
        //assertTrue(tableau.getDeck(1).getLast().isTheSameAs(swappedCard));
        assertTrue(tableau.getDeck(0).isEmpty());
     }

    @Test
    public void wrongCardExchangeBySuitBetweenTableauDecks() {
        //arrange
        Tableau tableau = new Tableau();
        Deck fromDeck = new Deck();
        Deck toDeck = new Deck();
        Card lastCard = new Card(Suit.HEART, Value.NINE);
        Card wrongCard = new Card(Suit.DIAMOND, Value.EIGHT);
        //act
        fromDeck.addCard(wrongCard);
        toDeck.addCard(lastCard);
        tableau.addPile(fromDeck);
        tableau.addPile(toDeck);
        tableau.swapCard(fromDeck, toDeck);
        //assert
        assertFalse(tableau.getDeck(1).getLast().isTheSameAs(wrongCard));
    }

    @Test
    public void wrongCardExchangeByValueBetweenTableauDecks() {
        //arrange
        Tableau tableau = new Tableau();
        Deck fromDeck = new Deck();
        Deck toDeck = new Deck();
        Card lastCard = new Card(Suit.CLUBS, Value.TWO);
        Card wrongCard = new Card(Suit.DIAMOND, Value.EIGHT);
        //act
        fromDeck.addCard(wrongCard);
        toDeck.addCard(lastCard);
        tableau.addPile(fromDeck);
        tableau.addPile(toDeck);
        tableau.swapCard(fromDeck, toDeck);
        //assert
        assertFalse(tableau.getDeck(1).getLast().isTheSameAs(wrongCard));
    }

    // Por las reglas de Klondike, en una columna (mazo) vacía sólo
    // se pueden poner Reyes [K] primero 

    @Test
    public void emptyDeckGoodCardAddition () {
        //arrange
        Tableau tableau = new Tableau();
        Deck fromDeck = new Deck();
        Deck toDeck = new Deck();
        Card newCard = new Card(Suit.DIAMOND, Value.KING);
        //act
        fromDeck.addCard(newCard);
        tableau.addPile(fromDeck);
        tableau.addPile(toDeck);
        tableau.swapCard(fromDeck, toDeck);
        //assert
        assertTrue(tableau.getDeck(1).getLast().isTheSameAs(newCard));
    }

    @Test
    public void emptyDeckRejectedWrongCard () {
        //arrange
        Tableau tableau = new Tableau();
        Deck fromDeck = new Deck();
        Deck toDeck = new Deck();
        Card wrongNewCard = new Card(Suit.CLUBS, Value.EIGHT);
        //act
        fromDeck.addCard(wrongNewCard);
        tableau.addPile(fromDeck);
        tableau.addPile(toDeck);
        tableau.swapCard(fromDeck, toDeck);
        //assert
        assertTrue(tableau.getDeck(1).isEmpty());
    }

}