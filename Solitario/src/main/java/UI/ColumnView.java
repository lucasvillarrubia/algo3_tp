package UI;

import Base.Card;
import Base.Deck;
import Elements.Column;
import Elements.VisitableDeck;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class ColumnView extends StackPane implements Clickable {

//    CardView cardView = new CardView();
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
            //ImageView image = cardView.getImage(card);
//            image.setTranslateY(OFFSET*offset);
            cardView.setTranslateY(OFFSET*offset);
            offset++;
            getChildren().add(cardView);
//            getChildren().add(image);
        }
        setOnMouseClicked(this::handleColumnClick);
    }

    public CardView getCardView(int i){
         return (CardView) this.getChildren().get(i);
    }
//    public CardView getCardView(int i) {
//        int childIndex = i * 2; // Assuming each card is followed by an ImageView
//        if (childIndex >= 0 && childIndex < this.getChildren().size()) {
//            Node node = this.getChildren().get(childIndex);
//            if (node instanceof CardView) {
//                return (CardView) node;
//            } else {
//                // Handle the case where the child at the specified index is not a CardView
//                // You might want to throw an exception or return null, depending on your requirements.
//                return null;
//            }
//        } else {
//            // Handle the case where the specified index is out of bounds
//            // You might want to throw an exception or return null, depending on your requirements.
//            return null;
//        }
//    }

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
        System.out.println("Column clicked! Column ID: " + number);
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

//    @Override
    public VisitableDeck getDeck() {
        return column;
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
