package UI;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class StockView extends HBox{


    public Button showStock() {
        Button button = new Button();
        button.setStyle("-fx-background-color: transparent; ");
        button.setGraphic(new CardView());
        eventHandler(button);
        return button;
    }

    public Button showEmptyStock() {
        Button button = new Button();
        button.setStyle("-fx-border-color: black; -fx-background-color: green; -fx-border-radius:2 ; -fx-pref-width: 74;-fx-pref-height: 96");
        return button;
    }

    public void eventHandler(Button button) {
        button.setOnMousePressed(mouseEvent -> ((Button) mouseEvent.getSource()).setStyle("-fx-padding: 3,1,1,3"));
        button.setOnMouseReleased(mouseEvent -> ((Button) mouseEvent.getSource()).setStyle("-fx-padding: 2,2,2,2"));
    }

}
