package Auxiliar;

import Base.Card;
import Base.Color;
import Base.Value;

import java.util.ArrayList;
import java.util.List;

public class Tableau {

    private List<Column> tableau;

    public Tableau(int columns) {
        this.tableau = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            tableau.add(new Column());
        }
    }



    public Column getDeck(int pos) {
        return this.tableau.get(pos);
    }

    public boolean isEmpty() {
        return tableau.stream().allMatch(Column::isEmpty);
    }

    private boolean isValidPosition(int pos) {
        return pos >= 0 && pos < tableau.size();
    }

    public Card getLast(int pos) {
        Column column = tableau.get(pos);
        return column.isEmpty() ? null : column.getLast();
    }

    public boolean canReceive(Card card, int deckPosition) {
        if (isValidPosition(deckPosition)) {
            Column column = tableau.get(deckPosition);

            if (column.isEmpty() && card.getValue() == Value.KING) {
                return true;
            } else if (!column.isEmpty()) {
                int lastValue = getLast(deckPosition).getValue().getNumber();
                int receivedValue = card.getValue().getNumber();
                Color lastColor = getLast(deckPosition).getSuit().getColor();
                Color receivedColor = card.getSuit().getColor();
                boolean precedingValue = (lastValue - receivedValue) == 1;
                boolean sameColor = lastColor == receivedColor;
                return !sameColor && precedingValue;
            }
        }
        return false;
    }

    public void addCard(Card card, int deckPos) {
        if (card != null && deckPos < tableau.size() && canReceive(card, deckPos)) {
            tableau.get(deckPos).addCard(card);
        }
    }

    public Card drawCard(int from) {
        Column column = tableau.get(from);
        if (column.isEmpty()) {
            return null;
        }
        return column.drawCard();
    }

    public void addCardSequence(List<Card> cards, int toDeck) {
        if (isValidPosition(toDeck) && !cards.isEmpty()) {
            for (Card card : cards) {
                addCard(card, toDeck);
            }
        }
    }


}