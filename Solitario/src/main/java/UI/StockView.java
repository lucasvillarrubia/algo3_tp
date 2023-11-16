package UI;

import Solitaire.Game;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;



public class StockView extends HBox {

    CardView cardView = new CardView();
    public Button showStock(){

        Button button = new Button();
        button.setStyle("-fx-border-color: black;-fx-padding: 2,2,2,2");
        button.setGraphic(cardView.getBack());
        button.setStyle("-fx-background-color: transparent");

        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Button)mouseEvent.getSource()).setStyle("-fx-padding: 3,1,1,3");
            }
        });

        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Button)mouseEvent.getSource()).setStyle("-fx-padding: 2,2,2,2");
            }
        });

        return button;
    }



    //Para obtener el valor de una carta
    // Card card = new Card(Suit.HEART, Value.SEVEN);
    //button.setGraphic(cardView.getImage(card));

}
