package UI;

import Base.Suit;
import Elements.Foundation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class FoundationView extends StackPane {

    Suit suit;
    public FoundationView(Foundation foundation) {
        this.suit = foundation.getSuit();
        buildFoundation();
    }

    private void buildFoundation(){


    }

}
