package UI;

import Base.Card;
import Elements.Column;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ColumnView extends StackPane {

    CardView cardView = new CardView();


    public ColumnView(Column column) {
        buildColumn(column);
    }

    private void buildColumn(Column column) {
        int offset = 0;
        for (int i = column.cardCount()-1; 0<=i ; i--) {
            Card card = column.getCard(i);
            ImageView image = cardView.getImage(card);
            image.setTranslateY(20*offset);
            offset++;
            getChildren().add(image);
        }
    }


}
