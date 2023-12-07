package UI;

import Base.Card;
import Elements.Column;
import Elements.Visitable;
import javafx.scene.layout.StackPane;


public class ColumnView extends StackPane implements Clickable {

    private final static int OFFSET = 20;
    private final Column column;
    private final int index;
    private int clickedCardViewIndex;
    private static final int ERROR = -1;

    public ColumnView(Column column, int index) {
        this.column = column;
        this.index = index;
        buildColumn(column);
    }

    private void buildColumn(Column column) {
        int offset = 0;
        for (int i = column.cardCount()-1; 0<=i ; i--) {
            Card card = column.getCard(i);
            CardView cardView = new CardView(card, i);
            cardView.setTranslateY(OFFSET*offset);
            offset++;
            getChildren().add(cardView);
        }
    }

    public CardView getCardView(int i){
         return (CardView) this.getChildren().get(i);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getClickedCardIndex() {
        for(int i = column.cardCount()-1; 0<=i ; i--){
            CardView card = getCardView(i);
            if (card.isClicked()) {
                clickedCardViewIndex = i;
                return card.getIndex();
            }
        }
        return ERROR;
    }

    @Override
    public Visitable getDeck() { return column; }

    @Override
    public void turnOffSelectedCard() {
        if (!getChildren().isEmpty()) {
            getClickedCardIndex();
            ((CardView) getChildren().get(clickedCardViewIndex)).unselectCard();
        }
    }

}