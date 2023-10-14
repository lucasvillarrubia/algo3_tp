package Klondike;

import Base.Card;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private List<Card> cards;

    public Column() {
        this.cards = new ArrayList<>();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card getLast() {
        if (!isEmpty()) {
            return cards.get(cards.size() - 1);
        }
        return null;
    }

    public void addCard(Card card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public Card drawCard() {
        if (!isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }

    public int size(){
        if(!isEmpty()){
            return cards.size();
        }
        return 0;
    }

    public Card getCard(int pos){
        return cards.get(pos);
    }

    public List<Card> getAllCards() {
        return new ArrayList<>(cards);
    }

}
