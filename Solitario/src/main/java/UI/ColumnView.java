package UI;

import Base.Card;
import Elements.Column;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class ColumnView extends StackPane implements Clickable {

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
            CardView cardView = new CardView(card, i);
            cardView.setFitHeight(79);
            cardView.setFitWidth(61);
            cardView.setTranslateY(OFFSET*offset);
            offset++;
            getChildren().add(cardView);
        }
        setOnMouseClicked(this::handleColumnClick);
    }

    public CardView getCardView(int i){
         return (CardView) this.getChildren().get(i);
    }

    public void setNumber(int id){
        this.number = id;
    }

    public int getNumber(){
        return this.number;
    }

    public void toggleColumnClick() {
        clickState = !clickState;
    }

    public void handleColumnClick(MouseEvent event) {
        toggleColumnClick();
    }

    public boolean isClicked(){
        return clickState;
    }

    @Override
    public void handleClick(MouseEvent event) {
        toggleColumnClick();
    }

    @Override
    public boolean estaClickeado() {
        return isClicked();
    }


    @Override
    public void setIndex(int id) {
        this.number = id;
    }

    @Override
    public int getIndex() {
        return number;
    }

}
