package UI;

import Base.Suit;
import Elements.Foundation;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class FoundationView extends StackPane {

    CardView cardView = new CardView();
    Suit suit;
    public FoundationView(Foundation foundation) {
        this.suit = foundation.getSuit();
        buildFoundation();
        setOnMouseClicked(this::handleFoundationClick);
    }

    private void buildFoundation(){
        ImageView image = cardView.getFoundationImage(suit);
        image.setStyle("-fx-opacity: 10%");
        getChildren().add(image);
    }

    private void handleFoundationClick(MouseEvent event) {
        System.out.println("Column Clicked! Suit: " + suit);
    }
}
