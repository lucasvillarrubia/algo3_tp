package Base;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {


        @Test
        public void addedCardToEmptyDeck () {
                //arrange
                Deck emptyDeck = new Deck();
                Card card = new Card(Suit.SPADES, Value.SEVEN);
                //act
                emptyDeck.addCard(card);
                //assert
                assertFalse(emptyDeck.isEmpty());
        }
        
        // Por las reglas de Klondike, en una columna (mazo) vacía sólo
        // se pueden poner Reyes [K] primero 

        @Test
        public void wrongCardCouldntMakeItToEmptyDeck () {
                //arrange
                Deck emptyDeck = new Deck();
                Card card = new Card(Suit.CLUBS, Value.QUEEN); 
                //act

                emptyDeck.addCard(card);
                //assert
                assertFalse(emptyDeck.isEmpty());
        }
        
        @Test
        public void removedCard (Card card) {
                //arrange
                Deck deck = new Deck();
                //act
                deck.addCard(card);
                //assert
                assertTrue(deck.getLast().isTheSameAs(card));
        }
        
        @Test
        public void goodCardAdditionAccepted () {
                //arrange
                Deck deck = new Deck();
                Card lastCardInDeck = new Card(Suit.DIAMOND, Value.EIGHT);
                Card goodCard = new Card(Suit.CLUBS, Value.SEVEN);
                //act
                deck.addCard(lastCardInDeck);
                deck.addCard(goodCard);
                //assert
                assertTrue(deck.getLast().isTheSameAs(goodCard));
        }
        
        @Test
        public void wrongCardAdditionRejected (Card card) {
                //arrange
                Deck deck = new Deck();
                Card lastCardInDeck = new Card(Suit.CLUBS, Value.JACK);
                Card wrongCard = new Card(Suit.DIAMOND, Value.ACE);
                //act
                deck.addCard(lastCardInDeck);
                deck.addCard(wrongCard);
                //assert
                assertFalse(deck.getLast().isTheSameAs(wrongCard));
        }
        
        // Este test está mal por donde lo mires, pero no se me ocurre en
        // DÓNDE verificar que el mazo no manda una carta si está vacío
        @Test
        public void emptyDeckDoesntSendCard () {
                //arrange
                Deck deck = new Deck();
                //act
                deck.sendCard();
                //assert
                assertTrue(deck.isEmpty());
        }





}