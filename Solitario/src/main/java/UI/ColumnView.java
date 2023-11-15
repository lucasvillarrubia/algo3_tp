package UI;

import Base.Card;
import Elements.Column;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ColumnView extends StackPane {

    CardView cardView = new CardView();


    public ColumnView(Column column) {
        buildColumn(column);
    }

    private void buildColumn(Column column) {
        int offset = 0;
        for (int i = 0; i < column.cardCount(); i++) {
            Card card = column.getCard(i);
            ImageView image = cardView.getImage(card);
            image.setTranslateY(17*offset);
            image.setStyle("-fx-border-color: black");
            offset++;
            getChildren().add(image);
        }
    }
}
