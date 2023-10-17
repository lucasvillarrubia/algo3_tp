package Klondike;

import Base.Card;
import Base.Value;

import java.util.ArrayList;
import java.util.List;

public class Tableau {

    private List<List<Card>> tableau;

    public Tableau(int columns) {
        this.tableau = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            tableau.add(new ArrayList<>());
        }
    }

    public List<Card> getDeck(int pos) {
        return this.tableau.get(pos);
    }

    public boolean isEmpty(){
        for (List<Card> cards: tableau){
            if (!cards.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPosition(int pos) {
        return pos >= 0 && pos < tableau.size();
    }

    public Card getLast(int pos){
        return tableau.get(pos).get((tableau.get(pos).size() - 1));
    }

    public boolean canReceive(Card card, int deckPosition){
        if(isValidPosition(deckPosition)){
            if ((tableau.get(deckPosition).isEmpty()) && (card.getValue() == Value.KING)){
                return true;
            } else if (!tableau.get(deckPosition).isEmpty()){
                int lastValue = getLast(deckPosition).getValue().getNumber();
                int receivedValue = card.getValue().getNumber();
                String lastColor = getLast(deckPosition).getSuit().getColor();
                String receivedColor = card.getSuit().getColor();
                boolean precedingValue = (lastValue - receivedValue) == 1;
                boolean sameColor = lastColor.equals(receivedColor);
                return (!sameColor && precedingValue);
            }
        }
        return false;
    }

    public void addCard(Card card, int deckPos) {
        if ((card != null) && (deckPos <= tableau.size())) {
            if(canReceive(card, deckPos)) {
                tableau.get(deckPos).add(card);
            }
        }
    }

    public Card drawCard(int from){
        if(tableau.get(from).isEmpty()) {
            return null;
        }
        return tableau.get(from).remove(tableau.get(from).size() - 1);
    }

    public void addCardSequence(List<Card> cards, int toDeck) {
        if (isValidPosition(toDeck) && !cards.isEmpty()) {
            for (Card card : cards) {
                addCard(card, toDeck);
            }
        }
    }


}