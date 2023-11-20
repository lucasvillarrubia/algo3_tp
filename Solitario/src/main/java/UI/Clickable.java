package UI;

import Elements.VisitableDeck;
import javafx.scene.input.MouseEvent;
import Base.Deck;

public interface Clickable {

    void handleClick(MouseEvent event);

    boolean estaClickeado();

//    VisitableDeck getDeck();

    public void setIndex(int id);

    public int getIndex();

}
