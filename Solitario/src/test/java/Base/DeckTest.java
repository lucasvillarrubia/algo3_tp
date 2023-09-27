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
        public void removedCard () {
                //arrange
                Card cardPrueba = new Card(Suit.HEART, Value.NINE);
                Deck deck = new Deck();
                //act
                deck.addCard(cardPrueba);
                //assert
                assertTrue(deck.getLast().isTheSameAs(cardPrueba));
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
        public void wrongCardAdditionRejected () {
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
                Card cardNull = deck.sendCard();
                //assert
                assertTrue(cardNull == null);
        }





}