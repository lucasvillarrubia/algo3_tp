package UI;

import Elements.Stock;
import Elements.VisitableDeck;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;



public class StockView extends HBox implements Clickable {

    private int number;

    private boolean clickState;

    private Stock stock;

    CardView cardView = new CardView();

    public Button showStock(Stock stock){
        this.stock = stock;
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; ");
        button.setGraphic(cardView.getBack());
        eventHandler(button);
        return button;
    }


    public Button showEmptyStock(){
        Button button = new Button();
        button.setStyle("-fx-border-color: black; -fx-background-color: green; -fx-border-radius:2 ; -fx-pref-width: 61;-fx-pref-height: 79");
        return button;
    }

    public void eventHandler(Button button) {
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Button) mouseEvent.getSource()).setStyle("-fx-padding: 3,1,1,3");
            }
        });

        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Button) mouseEvent.getSource()).setStyle("-fx-padding: 2,2,2,2");
            }
        });
    }

    public void toggleColumnClick() { clickState = !clickState; }

    public boolean isClicked(){
        return clickState;
    }

    @Override
    public void handleClick(MouseEvent event) {
        toggleColumnClick();
    }

    @Override
    public boolean estaClickeado() {
        return isClicked();
    }

   // @Override
    public VisitableDeck getDeck() {
        return stock;
    }

    @Override
    public void setIndex(int id) {
        this.number = id;
    }

    @Override
    public int getIndex() {
        return number;
    }
}
