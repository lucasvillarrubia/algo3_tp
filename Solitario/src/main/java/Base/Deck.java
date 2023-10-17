package Base;

import java.util.*;

public class Deck {
    protected Stack<Card> deck;

    public Deck(){
        this.deck = new Stack<>();
    }

    public void addCard(Card card) {
        deck.push(card);
    }

    public Card getLast(){
        if(deck.empty()) {
            return null;
        }
        deck.peek().flip();
        return deck.peek();
    }

    public Card drawCard(){
        if(deck.empty()) {
            return null;
        }
        deck.peek().flip();
        return deck.pop();
    }

    public boolean isEmpty () {
        return deck.empty();
    }

    public int cardCount () {
        return deck.size();
    }

    public void shuffle(int seed) {
        Random rn = new Random(seed);
        Collections.shuffle(deck, rn);
    }
}