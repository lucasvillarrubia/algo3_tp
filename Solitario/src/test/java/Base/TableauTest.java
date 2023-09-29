package Base;


import org.junit.Test;

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
        tableau.getDeck(0).addCard(card);
        assertFalse(tableau.isEmpty());
    }



    @Test
    public void initializeTableauWithTest() {
        Tableau tableau = new Tableau(2);
        assertTrue(tableau.isEmpty());
    }


//    @Test
//     public void moveDeckLastCardToAnotherTableauDeckTest() {
//        //arrange
//        Tableau tableau = new Tableau(2);
//        Deck fromDeck = tableau.getDeck(0);
//        Deck toDeck = tableau.getDeck(1);
//        Card lastCard = new Card(Suit.HEART, Value.NINE);
//        Card swappedCard = new Card(Suit.CLUBS, Value.EIGHT);
//        //act
//        fromDeck.addCard(swappedCard);
//        toDeck.addCard(lastCard);
//        tableau.swapCard(fromDeck, toDeck);
//        //assert
//        assertTrue(fromDeck.isEmpty());
//     }
//
//    @Test
//    public void wrongCardExchangeBySuitBetweenTableauDecksTest() {
//        //arrange
//        Tableau tableau = new Tableau(2);
//        Card lastCard = new Card(Suit.HEART, Value.NINE);
//        Card wrongCard = new Card(Suit.DIAMOND, Value.EIGHT);
//        //act
//        tableau.getDeck(0).addCard(wrongCard);
//        tableau.getDeck(1).addCard(lastCard);
//        tableau.swapCard(tableau.getDeck(0), tableau.getDeck(1));
//        //assert
//        assertFalse(tableau.getDeck(1).getLast().isTheSameAs(wrongCard));
//    }
//
//    @Test
//    public void wrongCardExchangeByValueBetweenTableauDecksTest() {
//        //arrange
//        Tableau tableau = new Tableau(2);
//        Card lastCard = new Card(Suit.CLUBS, Value.TWO);
//        Card wrongCard = new Card(Suit.DIAMOND, Value.EIGHT);
//        //act
//        tableau.getDeck(0).addCard(wrongCard);
//        tableau.getDeck(1).addCard(lastCard);
//        tableau.swapCard(tableau.getDeck(0), tableau.getDeck(1));
//        //assert
//        assertFalse(tableau.getDeck(1).getLast().isTheSameAs(wrongCard));
//    }
//
//    // Por las reglas de Klondike, en una columna (mazo) vacía sólo
//    // se pueden poner Reyes [K] primero
//
//    @Test
//    public void emptyDeckGoodCardAdditionTest() {
//        //arrange
//        Tableau tableau = new Tableau(2);
//        Card newCard = new Card(Suit.DIAMOND, Value.KING);
//        //act
//        tableau.getDeck(0).addCard(newCard);
//        tableau.swapCard(tableau.getDeck(0), tableau.getDeck(1));
//        //assert
//        assertTrue(tableau.getDeck(1).getLast().isTheSameAs(newCard));
//    }
//
//    @Test
//    public void emptyDeckRejectedWrongCardTest() {
//        //arrange
//        Tableau tableau = new Tableau(2);
//        Deck fromDeck = new Deck();
//        Deck toDeck = new Deck();
//        Card wrongNewCard = new Card(Suit.CLUBS, Value.EIGHT);
//        //act
//        fromDeck.addCard(wrongNewCard);
//        tableau.addDeck(fromDeck);
//        tableau.addDeck(toDeck);
//        tableau.swapCard(fromDeck, toDeck);
//        //assert
//        assertTrue(tableau.getDeck(1).isEmpty());
//    }

}