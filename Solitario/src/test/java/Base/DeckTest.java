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
                assertEquals(deck.cardCount(), 1);
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
                assertEquals(emptyDeck.cardCount(),4);
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

//        @Test
//        public void shuffleTest(){
//                Deck deck = new Deck();
//                Card cardPrueba1 = new Card(Suit.HEART, Value.KING);
//                Card cardPrueba2 = new Card(Suit.DIAMOND, Value.NINE);
//                Card cardPrueba3 = new Card(Suit.HEART, Value.KING);
//                Card cardPrueba4 = new Card(Suit.DIAMOND, Value.NINE);
//                deck.addCard(cardPrueba1);
//                deck.addCard(cardPrueba2);
//                Deck shuffledDeck = new Deck();
//                shuffledDeck.addCard(cardPrueba3);
//                shuffledDeck.addCard(cardPrueba4);
//                shuffledDeck.shuffle(23);
//                deck.drawCard();
//                assertNotEquals(deck.getLast(), shuffledDeck.getLast());
//        }



        @Test
        public void getLastTest() {
                Deck deck = new Deck();
                assertNull(deck.getLast());
                Card card = new Card(Suit.SPADES, Value.ACE);
                deck.addCard(card);
                Card lastCard = deck.getLast();
                assertNotNull(lastCard);
                assertTrue(lastCard.isFaceUp());
        }

        @Test
        public void testDrawCard() {
                Deck deck = new Deck();
                assertNull(deck.drawCard());
                Card card = new Card(Suit.SPADES, Value.ACE);
                deck.addCard(card);
                Card drawnCard = deck.drawCard();
                assertNotNull(drawnCard);
                assertTrue(drawnCard.isFaceUp());
                assertEquals(0, deck.cardCount());
        }

        @Test
        public void testRemoveCard() {
                Deck deck = new Deck();
                Card card = new Card(Suit.SPADES, Value.ACE);
                deck.addCard(card);
                assertTrue(deck.removeCard(card));
                assertEquals(0, deck.cardCount());
                Card nonExistingCard = new Card(Suit.HEART, Value.KING);
                assertFalse(deck.removeCard(nonExistingCard));
        }



}

