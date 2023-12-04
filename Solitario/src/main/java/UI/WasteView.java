package UI;

import Base.Card;
import Elements.Stock;
import Elements.Visitable;
import javafx.scene.layout.StackPane;

public class WasteView extends StackPane implements Clickable {

    private final Stock stock;
    private final int index;

    public WasteView(Stock stock){
        this.stock = stock;
        this.index = 0;
    }

    public void update() {
        if (!stock.isEmpty()) {
            Card card = stock.getLast();
            CardView wasteView = new CardView(card, 0);
            getChildren().clear();
            getChildren().add(wasteView);
        } else {
            getChildren().clear();
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getClickedCardIndex() {
        if (getChildren().isEmpty()) return -1;
        else return 0;
    }

    @Override
    public Visitable getDeck() {
        return stock;
    }

    @Override
    public void turnOffSelectedCard() {
        if(!getChildren().isEmpty()) ((CardView) getChildren().get(0)).unselectCard();
    }

}