package Base;

import java.util.*;

public class Deck {
    private Stack<Card> deck;

    public Deck(){
        this.deck = new Stack<>();
    }

    public void initDeck() {
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(suit, value);
                deck.push(card);
            }
        }
    }

    public void shuffle(int seed) {
        Random rn = new Random(seed);
        Collections.shuffle(deck, rn);
    }

    public Card getLast(){
        return deck.peek();
    }

    public void addCard(Card card){
        deck.push(card);
    }

    public Card sendCard(){
        if(deck.empty()){
            return null;
        }
        return deck.pop();
    }
    
    public boolean isEmpty () {
        return deck.empty();
    }
    
    public int cardCount () {
        return deck.size();
    }

    


    // Esto va en otro lado
    // public void resetDeck(){
    // }


}
