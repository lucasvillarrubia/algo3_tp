package Base;

import Types.Klondike;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void initKlondikeGameTest() {
        Game klondike = new Game(1, new Klondike());
        klondike.getGameType().movimientoValido();


    }
}