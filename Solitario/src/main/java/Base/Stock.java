package Base;

import java.util.Collections;
import java.util.Random;

public class Stock extends Deck {
    private Deck waste;

    public Stock () {
        super();
        this.waste = new Deck();
    }

    public void initStock () {
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(suit, value);
                deck.push(card);
            }
        }
    }

    public boolean showCard() {
        if (deck.empty()) {
            return false;
        }
        waste.addCard(deck.pop());
        return true;
    }

    public void shuffle(int seed) {
        Random rn = new Random(seed);
        Collections.shuffle(deck, rn);
    }

    public void reset() {
        while(!waste.isEmpty()) {
            deck.add(waste.drawCard());
        }
    }

    public boolean noCardOnDisplay() {
        return waste.isEmpty();
    }

    @Override
    public Card drawCard(){
        return waste.drawCard();
    }

    @Override
    public Card getLast() {
        return waste.getLast();
    }
}
