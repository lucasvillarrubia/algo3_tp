package UI;


import Base.Card;
import Base.Suit;
import Base.Value;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



public class StockView extends HBox {

    CardView cardView = new CardView();
    public Button showStock(){
        Button button = new Button();
        button.setStyle("-fx-border-color: black;-fx-padding: 5,5,5,5");
        button.setGraphic(cardView.getBack());
        button.setStyle("-fx-background-color: transparent");
        return button;
    }



    //Para obtener el valor de una carta
    // Card card = new Card(Suit.HEART, Value.SEVEN);
    //button.setGraphic(cardView.getImage(card));

}
