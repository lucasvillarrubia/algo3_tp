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

        @Test
        public void shuffleTest(){
                Deck deck = new Deck();
                Card cardPrueba1 = new Card(Suit.HEART, Value.KING);
                Card cardPrueba2 = new Card(Suit.DIAMOND, Value.NINE);
                Card cardPrueba3 = new Card(Suit.HEART, Value.KING);
                Card cardPrueba4 = new Card(Suit.DIAMOND, Value.NINE);
                deck.addCard(cardPrueba1);
                deck.addCard(cardPrueba2);
                Deck shuffledDeck = new Deck();
                shuffledDeck.addCard(cardPrueba3);
                shuffledDeck.addCard(cardPrueba4);
                shuffledDeck.shuffle(23);
                deck.drawCard();
                assertNotEquals(deck.getLast(), shuffledDeck.getLast());
        }

        @Test
        public void sameSeedShuffleTest(){
                Deck deck1 = new Deck();
                Deck deck2 = new Deck();
                Card cardPrueba1 = new Card(Suit.HEART, Value.KING);
                Card cardPrueba2 = new Card(Suit.DIAMOND, Value.NINE);
                Card cardPrueba3 = new Card(Suit.CLUBS, Value.NINE);
                Card cardPrueba4 = new Card(Suit.HEART, Value.TWO);
                Card cardPrueba5 = new Card(Suit.HEART, Value.TWO);
                deck1.addCard(cardPrueba1);
                deck1.addCard(cardPrueba2);
                deck1.addCard(cardPrueba3);
                deck1.addCard(cardPrueba4);
                deck2.addCard(cardPrueba1);
                deck2.addCard(cardPrueba2);
                deck2.addCard(cardPrueba3);
                deck2.addCard(cardPrueba4);
                deck1.shuffle(1);
                deck2.shuffle(1);
                assertEquals(deck1.getLast().getValue(), deck2.getLast().getValue());
        }




}

