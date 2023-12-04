package UI;

import Base.Suit;
import Elements.Foundation;
import Elements.Visitable;
import javafx.scene.layout.StackPane;

public class FoundationView extends StackPane implements Clickable {

    Suit suit;
    Foundation foundation;
    private final int index;
    private static final int ERROR = -1;
    private static final int OK = 0;

    public FoundationView(Foundation foundation, int index) {
        this.suit = foundation.getSuit();
        this.foundation = foundation;
        this.index = index;
        buildFoundation();
    }

    private void buildFoundation(){
        if(!foundation.isEmpty()) {
            CardView image = new CardView(foundation.getLast(), foundation.cardCount()-1);
            getChildren().add(image);
        } else {
            CardView image = new CardView(foundation.getSuit());
            image.setStyle("-fx-opacity: 10%");
            getChildren().add(image);
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getClickedCardIndex() {
        if (foundation.isEmpty()) return ERROR;
        else return OK;
    }

    @Override
    public Visitable getDeck() { return foundation; }

    @Override
    public void turnOffSelectedCard() {
        if (!getChildren().isEmpty()) ((CardView) getChildren().get(getClickedCardIndex())).unselectCard();
    }

}