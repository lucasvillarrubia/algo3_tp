package Klondike;

import Base.Deck;
import Base.Suit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void initKlondikeGameTest() {
        Game klondike = new Game(1);
        assertFalse(klondike.gameStatus());
        assertFalse(klondike.isGameWon());
        assertEquals(klondike.getCantMovements(),0);

    }

    @Test
    public void initOneStepToWinGameTest() {
        ArrayList<Foundation> foundations = new ArrayList<>();
        Deck deck = new Deck();
        Tableau tableau = new Tableau(3);
        Game game = new Game(foundations, deck, tableau);
        assertNotNull(game);
        assertFalse(game.gameStatus());
        assertFalse(game.isGameWon());
        assertEquals(0, game.getCantMovements());

    }



//    Inicializar el juego a partir de una semilla aleatoria
//    Inicializar el juego en un estado particular
//    Verificar si el estado actual es de “juego ganado”
//    Hacer un movimiento, y verificar si es válido o no

    //
//        @Test
//        public void initDeckTest(){
//                Stock stock = new Stock();
//                stock.initStock();
//                assertEquals(stock.cardCount(), 52);
//        }
////
////        @Test
////        public void stockRemovedLastCardAndNowIsEmpty () {
////                Stock stock = new Stock();
////                stock.initStock();
////                while (!stock.isEmpty()){
////                        stock.showCard();
////                        stock.drawCard();
////                }
////                assertTrue(stock.isEmpty());
////        }
////
////        @Test
////        public void stockResetTest () {
////                Stock stock = new Stock();
////                stock.initStock();
////                stock.showCard();
////                stock.showCard();
////                stock.showCard();
////                stock.showCard();
////                stock.reset();
////                assertTrue(stock.noCardOnDisplay());
////        }
////
////        @Test
////        public void stockDisplayOneCard () {
////                Stock stock = new Stock();
////                stock.initStock();
////                stock.showCard();
////                assertFalse(stock.noCardOnDisplay());
////        }
}