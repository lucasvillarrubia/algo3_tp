package UI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;



public class StockView extends HBox {

    CardView cardView = new CardView();
    public Button showStock(){
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


}
