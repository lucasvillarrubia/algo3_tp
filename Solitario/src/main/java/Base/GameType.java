package Base;

public interface GameType {

    Tableau initTableau(Deck stock);

    Deck initStock(int seed);

    boolean movimientoValido();

    boolean isGameWon();

    void move(Deck from, Deck to);



}
