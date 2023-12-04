package UI;

import Elements.Visitable;
public interface Clickable {

    int getIndex();

    int getClickedCardIndex();

    Visitable getDeck();

    void turnOffSelectedCard();

}
