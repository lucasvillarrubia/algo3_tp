package UI;

import javafx.scene.input.MouseEvent;
import Base.Deck;

public interface Clickable {

    void handleClick(MouseEvent event);

    boolean estaClickeado();

    Deck getDeck();

    public void setIndex(int id);

    public int getIndex();

}
