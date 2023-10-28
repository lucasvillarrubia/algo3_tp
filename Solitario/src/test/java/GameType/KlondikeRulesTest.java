package GameType;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import Elements.Stock;
import GameType.KlondikeRules;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KlondikeRulesTest {


    @Test
    public void columnAcceptCardTest() {
        Column column = new Column();
        KlondikeRules gameRules = new KlondikeRules();
        Card card = new Card(Suit.HEART, Value.KING);
        assertTrue(gameRules.acceptsCard(column, card));
        column.acceptCard(gameRules, card);
        assertEquals(1, column.cardCount());
        assertEquals(card, column.getCard(0));
    }

    @Test
    public void columnAcceptWrongCardTest() {
        Column column = new Column();
        KlondikeRules gameRules = new KlondikeRules();
        Card invalidCard = new Card(Suit.DIAMOND, Value.NINE);
        column.toggleFillingState();
        assertFalse(gameRules.acceptsCard(column, invalidCard));
    }

    @Test
    public void foundationAcceptCardTest() {
        Foundation foundation = new Foundation(Suit.HEART);
        KlondikeRules gameRules = new KlondikeRules();
        Card card = new Card(Suit.HEART, Value.ACE);
        assertTrue(gameRules.acceptsCard(foundation, card));
        foundation.acceptCard(gameRules, card);
        assertEquals(1, foundation.cardCount());
        assertEquals(card, foundation.getLast());
    }

    @Test
    public void foundationAcceptWrongCardTest() {
        Foundation foundation = new Foundation(Suit.DIAMOND);
        KlondikeRules gameRules = new KlondikeRules();
        Card invalidCard = new Card(Suit.DIAMOND, Value.NINE);
        assertFalse(gameRules.acceptsCard(foundation, invalidCard));
        assertTrue(foundation.isEmpty());
    }

    @Test
    public void stockAcceptCardTest() {
        Stock stock = new Stock();
        KlondikeRules gameRules = new KlondikeRules();
        Card card = new Card(Suit.HEART, Value.QUEEN);
        assertTrue(gameRules.acceptsCard(stock, card));
        stock.acceptCard(gameRules, card);
        assertEquals(1, stock.cardCount());
        assertEquals(card, stock.getLast());
    }

    @Test
    public void fullStockAcceptWrongCardTest() {
        KlondikeRules gameRules = new KlondikeRules();
        Stock stock = gameRules.initStock();
        Card card = new Card(Suit.DIAMOND, Value.NINE);
        assertFalse(stock.isEmpty());
    }

    //revisar tests (son los ex-tests de game)

    @Test
    public void initKlondikeGameTest() {
        KlondikeRules rules = new KlondikeRules();
        Game game = new Game(rules, 1);
        rules.gameInit(game, 10);
        assertFalse(game.gameStatus());
        assertFalse(game.isGameWon());
        assertEquals(game.getCantMovements(),0);
        for(Suit s: Suit.values()){

            assertNotNull(game.getFoundationBySuit(s));
        }
    }

    @Test
    public void moveFromTableauToFoundationCompleteTest() {
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.HEART));
        KlondikeRules rules = new KlondikeRules();
        foundations.get(0).acceptCard(rules,one);
        foundations.get(0).acceptCard(rules, two);
        foundations.get(0).acceptCard(rules, three);
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(foundations, tableau,new Stock());
        assertFalse(game.isGameWon());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getLast(), three);
    }

    @Test
    public void columnAdmitsSequenceTest() {
        KlondikeRules rules = new KlondikeRules();
        Column column = new Column();
        List<Card> cardsSeq = new ArrayList<>();
        cardsSeq.add(new Card(Suit.SPADES, Value.QUEEN));
        cardsSeq.add(new Card(Suit.HEART, Value.JACK));
        cardsSeq.add(new Card(Suit.SPADES, Value.TEN));
        cardsSeq.add(new Card(Suit.HEART, Value.NINE));
        //column.acceptCard(rules, cardsSeq);
    }


    //                  C  O  L  U  M  N

    @Test
    public void testAcceptCardOnEmptyColumn() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.HEART, Value.KING);
        Column emptyColumn = new Column();
        assertTrue(gameRules.acceptsCard(emptyColumn, card1));
    }

    @Test
    public void testRejectCardOnEmptyColumn() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.CLUBS, Value.SEVEN);
        Column emptyColumn = new Column();
        emptyColumn.toggleFillingState();
        assertFalse(gameRules.acceptsCard(emptyColumn, card1));
    }

    @Test
    public void testColumnTypeCanSendCardWithKlondikeRules() {
        KlondikeRules gameRules = new KlondikeRules();
        Column emptyColumn = new Column();
        assertTrue(gameRules.givesCard(emptyColumn));
    }

    @Test
    public void testAcceptSequenceOnEmptyColumn() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.SPADES, Value.KING);
        Card card2 = new Card(Suit.HEART, Value.QUEEN);
        Column cardsToAdd = new Column();
        Column emptyColumn = new Column();
        cardsToAdd.acceptCard(gameRules, card1);
        cardsToAdd.acceptCard(gameRules, card2);
        cardsToAdd.toggleFillingState();
        emptyColumn.toggleFillingState();
        assertTrue(gameRules.admitsSequence(emptyColumn, cardsToAdd));
    }

    @Test
    public void testRejectSequenceOnEmptyColumn() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.SPADES, Value.TEN);
        Card card2 = new Card(Suit.HEART, Value.NINE);
        Card card3 = new Card(Suit.CLUBS, Value.EIGHT);
        Column cardsToAdd = new Column();
        Column emptyColumn = new Column();
        cardsToAdd.acceptCard(gameRules, card1);
        cardsToAdd.acceptCard(gameRules, card2);
        cardsToAdd.acceptCard(gameRules, card3);
        cardsToAdd.toggleFillingState();
        emptyColumn.toggleFillingState();
        assertFalse(gameRules.admitsSequence(emptyColumn, cardsToAdd));
    }

    //                  F  O  U  N  D  A  T  I  O  N

    @Test
    public void testAcceptCardOnEmptyFoundation() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.HEART, Value.ACE);
        Foundation emptyFoundation = new Foundation(Suit.HEART);
        assertTrue(gameRules.acceptsCard(emptyFoundation, card1));
    }

    @Test
    public void testRejectCardOnEmptyFoundation() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.CLUBS, Value.SEVEN);
        Foundation emptyFoundation = new Foundation(Suit.CLUBS);
        assertFalse(gameRules.acceptsCard(emptyFoundation, card1));
    }

    @Test
    public void testFoundationTypeCanSendCardWithKlondikeRules() {
        KlondikeRules gameRules = new KlondikeRules();
        Foundation emptyFoundation = new Foundation(Suit.HEART);
        assertTrue(gameRules.givesCard(emptyFoundation));
    }

    @Test
    public void testFoundationTypeDoesNotAdmitSequencesWithKlondikeRules() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.CLUBS, Value.ACE);
        Card card2 = new Card(Suit.CLUBS, Value.TWO);
        Card card3 = new Card(Suit.CLUBS, Value.THREE);
        Column cardsToAdd = new Column();
        Foundation emptyFoundation = new Foundation(Suit.CLUBS);
        cardsToAdd.acceptCard(gameRules, card1);
        cardsToAdd.acceptCard(gameRules, card2);
        cardsToAdd.acceptCard(gameRules, card3);
        cardsToAdd.toggleFillingState();
        assertFalse(gameRules.admitsSequence(emptyFoundation, cardsToAdd));
    }

    //                  S  T  O  C  K


}