package Base;

import java.util.*;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(){
        initDeck();
        shuffle();
    }

    private void initDeck() {
        deck = new ArrayList<>();
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(suit, value);
                deck.add(card);
            }
        }
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

}
