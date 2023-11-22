package UI;


import javafx.scene.input.MouseEvent;

public interface Clickable {

    void handleClick(MouseEvent event);

    boolean estaClickeado();

//    VisitableDeck getDeck();

    public void setIndex(int id);

    public int getIndex();

}
