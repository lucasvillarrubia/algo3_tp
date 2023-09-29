package Base;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {


        @Test
        public void initEmptyDeckTest(){
                Deck deck = new Deck();
                assertTrue(deck.isEmpty());
        }

        @Test
        public void addedCardToEmptyDeckTest() {
                Deck deck = new Deck();
                Card card = new Card(Suit.SPADES, Value.SEVEN);
                deck.addCard(card);
                assertFalse(deck.isEmpty());
                assertEquals(deck.getLast(), card);
        }

        @Test
        public void addedCardToDeckTest() {
                //arrange
                Deck emptyDeck = new Deck();
                Card card = new Card(Suit.SPADES, Value.SEVEN);
                Card anotherCard = new Card(Suit.CLUBS, Value.FIVE);
                Card extraCard = new Card(Suit.HEART, Value.ACE);
                //act
                emptyDeck.addCard(card);
                emptyDeck.addCard(extraCard);
                emptyDeck.addCard(anotherCard);
                emptyDeck.addCard(card);

                //assert
                assertFalse(emptyDeck.isEmpty());
                assertEquals(emptyDeck.getLast(), card);
        }

        @Test
        public void removedCardTest() {
                Card cardPrueba1 = new Card(Suit.HEART, Value.FIVE);
                Card cardPrueba2 = new Card(Suit.DIAMOND, Value.NINE);
                Card cardPrueba3 = new Card(Suit.CLUBS, Value.NINE);
                Card cardPrueba4 = new Card(Suit.HEART, Value.TWO);

                Deck deck = new Deck();
                deck.addCard(cardPrueba1);
                deck.addCard(cardPrueba2);
                deck.addCard(cardPrueba3);
                deck.addCard(cardPrueba4);
                deck.drawCard();
                assertTrue(deck.getLast().isTheSameAs(cardPrueba3));
        }

        @Test
        public void deckRemovesCardAndNowIsEmptyTest() {
                Card cardPrueba = new Card(Suit.HEART, Value.NINE);
                Deck deck = new Deck();
                deck.addCard(cardPrueba);
                deck.drawCard();
                assertTrue(deck.isEmpty());
        }



        @Test
        public void emptyDeckDoesntSendCardTest() {
                Deck deck = new Deck();
                Card cardNull = deck.drawCard();
                assertNull(cardNull);
        }

        @Test
        public void emptyDeckDoesntHaveAnyCardTest() {
                Deck deck = new Deck();
                Card cardNull = deck.getLast();
                assertNull(cardNull);
        }




}