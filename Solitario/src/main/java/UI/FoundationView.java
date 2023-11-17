package UI;

import Base.Suit;
import Elements.Foundation;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class FoundationView extends StackPane {

    CardView cardView = new CardView();
    Suit suit;
    Foundation foundation;
    public FoundationView(Foundation foundation) {
        this.suit = foundation.getSuit();
        this.foundation = foundation;
        buildFoundation();
        setOnMouseClicked(this::handleFoundationClick);
    }

    private void buildFoundation(){
        if(foundation.isEmpty()) {
            ImageView image = cardView.getFoundationImage(suit);
            image.setStyle("-fx-opacity: 10%");
            getChildren().add(image);
        } else {
            ImageView image = cardView.getImage(foundation.getLast());
            getChildren().add(image);
        }
    }

    private void handleFoundationClick(MouseEvent event) {
        System.out.println("Column Clicked! Suit: " + suit);
    }

    public Foundation getFoundation() {
        return foundation;
    }
}
