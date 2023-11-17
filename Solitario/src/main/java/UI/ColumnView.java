package UI;

import Base.Card;
import Elements.Column;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;


public class ColumnView extends StackPane {

    CardView cardView = new CardView();
    private final static int OFFSET = 20;

    private final Column column;

    private int id;

    public ColumnView(Column column) {
        this.column = column;
        buildColumn(column);
        setOnMouseClicked(this::handleColumnClick);
    }

    public Column getColumn(){
        return column;
    }

    private void buildColumn(Column column) {
        int offset = 0;
        for (int i = column.cardCount()-1; 0<=i ; i--) {
            Card card = column.getCard(i);
            ImageView image = cardView.getImage(card);
            image.setTranslateY(OFFSET*offset);
            offset++;
            getChildren().add(image);

        }
    }

    public void setId(int id){
        this.id = id;
    }


    private void handleColumnClick(MouseEvent event) {
        System.out.println("Column Clicked! Column ID: " + id);
    }





}
