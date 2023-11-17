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

    private int number;

    private boolean clickState;

    public ColumnView(Column column) {
        this.column = column;
        buildColumn(column);
        clickState = false;
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
            setOnMouseClicked(this::handleColumnClick);
        }
    }



    public void setNumber(int id){
        this.number = id;
    }

    public int getNumber(){
        return this.number;
    }

    public void toggleColumnClick() { clickState = !clickState; }

    public void handleColumnClick(MouseEvent event) {
        toggleColumnClick();
        System.out.println("Column Clicked! Column ID: " + column.getCard(0).getValue()+ column.getCard(0).getSuit());
    }

    public boolean isClicked(){
        return clickState;
    }



}
