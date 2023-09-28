package Klondike;

import org.junit.Test;

import static org.junit.Assert.*;

public class KlondikeTest {

    @Test
    public void initKlondikeGameTest() {
        Klondike klondike = new Klondike(1);
        assertFalse(klondike.isGameWon());
        assertEquals(klondike.getCantMovements(),0);
    }

//    @Test
//    public void initKlondikeTableauTest() {
//        Klondike klondike = new Klondike(1);
////        klondike.initTableau();
//
//    }


}