package GameType;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Solitaire.Game;
import Elements.Stock;
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
        column.addCards(card);
        assertEquals(1, column.cardCount());
        assertEquals(card, column.getCard(0));
        assertTrue(gameRules.acceptsCard(column, new Card(Suit.SPADES, Value.QUEEN)));
    }

    @Test
    public void columnAcceptWrongCardTest() {
        Column column = new Column();
        KlondikeRules gameRules = new KlondikeRules();
        Card invalidCard = new Card(Suit.DIAMOND, Value.NINE);
        assertFalse(gameRules.acceptsCard(column, invalidCard));
    }

    @Test
    public void foundationAcceptCardTest() {
        Foundation foundation = new Foundation(Suit.HEART);
        KlondikeRules gameRules = new KlondikeRules();
        Card card = new Card(Suit.HEART, Value.ACE);
        assertTrue(gameRules.acceptsCard(foundation, card));
        foundation.addCards(card);
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
    public void initKlondikeGameTest() {
        KlondikeRules rules = new KlondikeRules();
        Game game = new Game(rules, 10);
        assertFalse(game.gameStatus());
        assertFalse(game.isGameWon());
        assertEquals(game.getCantMovements(),0);
        for(Suit s: Suit.values()){
            assertNotNull(game.getFoundationBySuit(s));
        }
    }

    @Test
    public void moveFromTableauToFoundationCompleteTest() {
        KlondikeRules kr = new KlondikeRules();
        Card one = new Card(Suit.HEART, Value.ACE);
        Card two = new Card(Suit.HEART, Value.TWO);
        Card three = new Card(Suit.HEART, Value.THREE);
        ArrayList<Foundation> foundations = new ArrayList<>(4);
        foundations.add(new Foundation(Suit.HEART));
        foundations.get(0).addCards(one);
        foundations.get(0).addCards(two);
        foundations.get(0).addCards(three);
        ArrayList<Column> tableau = new ArrayList<>();
        Game game = new Game(kr, foundations, tableau,new Stock());
        assertFalse(game.isGameWon());
        assertEquals(game.getFoundationBySuit(Suit.HEART).getLast(), three);
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
        cardsToAdd.addCards(card1);
        cardsToAdd.addCards(card2);
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
        cardsToAdd.addCards(card1);
        cardsToAdd.addCards(card2);
        cardsToAdd.addCards(card3);
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
        cardsToAdd.addCards(card1);
        cardsToAdd.addCards(card2);
        cardsToAdd.addCards(card3);
        assertFalse(gameRules.admitsSequence(emptyFoundation, cardsToAdd));
    }

    //                  S  T  O  C  K

    @Test
    public void testRejectCardInStockTypeWithKlondikeRules() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.CLUBS, Value.SEVEN);
        Stock emptyStock = new Stock();
        assertFalse(gameRules.acceptsCard(emptyStock, card1));
    }

    @Test
    public void testStockCannotSendCardWithoutWasteCard() {
        KlondikeRules gameRules = new KlondikeRules();
        Stock emptyStock = new Stock();
        assertFalse(gameRules.givesCard(emptyStock));
    }

    @Test
    public void testStockTypeDoesNotAdmitSequencesWithKlondikeRules() {
        KlondikeRules gameRules = new KlondikeRules();
        Card card1 = new Card(Suit.CLUBS, Value.ACE);
        Card card2 = new Card(Suit.CLUBS, Value.TWO);
        Card card3 = new Card(Suit.CLUBS, Value.THREE);
        Column cardsToAdd = new Column();
        Stock stock = new Stock();
        cardsToAdd.addCards(card1);
        cardsToAdd.addCards(card2);
        stock.addCards(card3);
        assertFalse(gameRules.admitsSequence(stock, cardsToAdd));
    }

    //        D  R  A  W    C  A  R  D    F  R  O  M    S  T  O  C  K

    @Test
    public void testCannotDrawCardFromStockOfNullGame() {
        KlondikeRules kr = new KlondikeRules();
        assertFalse(kr.drawCardFromStock(null, null));
    }

    @Test
    public void testCannotDrawCardFromStockOfEmptyStock() {
        KlondikeRules kr = new KlondikeRules();
        Stock emptyStock = new Stock();
        List<Column> emptyTableau = new ArrayList<>();
        assertFalse(kr.drawCardFromStock(emptyStock, emptyTableau));
    }

    @Test
    public void testCannotDrawCardFromStockIfIsFilling() {
        KlondikeRules kr = new KlondikeRules();
        Stock emptyStock = new Stock();
        List<Column> emptyTableau = new ArrayList<>();
        assertFalse(kr.drawCardFromStock(emptyStock, emptyTableau));
    }

    @Test
    public void testDrawCardFromStockWhenGameJustStarted() {
        KlondikeRules kr = new KlondikeRules();
        Stock filledStock = new Stock();
        Card card1 = new Card(Suit.SPADES, Value.TEN);
        Card card2 = new Card(Suit.HEART, Value.NINE);
        Card card3 = new Card(Suit.CLUBS, Value.EIGHT);
        filledStock.addCards(card1);
        filledStock.addCards(card2);
        filledStock.addCards(card3);
        List<Column> emptyTableau = new ArrayList<>();
        assertTrue(kr.drawCardFromStock(filledStock, emptyTableau));
    }

    @Test
    public void testDrawCardFromStockIfWasteCardWasDrawn() {
        KlondikeRules kr = new KlondikeRules();
        Stock filledStock = new Stock();
        Card card1 = new Card(Suit.SPADES, Value.TEN);
        Card card2 = new Card(Suit.HEART, Value.NINE);
        Card card3 = new Card(Suit.CLUBS, Value.EIGHT);
        Card card4 = new Card(Suit.SPADES, Value.THREE);
        Card card5 = new Card(Suit.CLUBS, Value.FOUR);
        filledStock.addCards(card1);
        filledStock.addCards(card2);
        filledStock.addCards(card3);
        filledStock.addCards(card4);
        filledStock.addCards(card5);
        List<Column> emptyTableau = new ArrayList<>();
        List<Foundation> emptyFoundations = new ArrayList<>();
        Game game = new Game(kr, emptyFoundations, emptyTableau, filledStock);
        kr.drawCardFromStock(filledStock, emptyTableau);
        kr.drawCardFromStock(filledStock, emptyTableau);
        game.getStock().drawCard();
        assertTrue(kr.drawCardFromStock(filledStock, emptyTableau));
    }

    @Test
    public void testDrawCardFromStockGoesThroughNextCard() {
        KlondikeRules kr = new KlondikeRules();
        Stock filledStock = new Stock();
        Card card1 = new Card(Suit.SPADES, Value.TEN);
        Card card2 = new Card(Suit.HEART, Value.NINE);
        Card card3 = new Card(Suit.CLUBS, Value.EIGHT);
        Card card4 = new Card(Suit.SPADES, Value.THREE);
        Card card5 = new Card(Suit.CLUBS, Value.FOUR);
        filledStock.addCards(card1);
        filledStock.addCards(card2);
        filledStock.addCards(card3);
        filledStock.addCards(card4);
        filledStock.addCards(card5);
        List<Column> emptyTableau = new ArrayList<>();
        assertTrue(kr.drawCardFromStock(filledStock, emptyTableau));
        assertTrue(kr.drawCardFromStock(filledStock, emptyTableau));
        assertTrue(kr.drawCardFromStock(filledStock, emptyTableau));
    }

}