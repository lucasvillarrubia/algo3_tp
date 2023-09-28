package Base;

import java.util.*;

public class Deck {
    protected Stack<Card> deck;

    public Deck(){
        this.deck = new Stack<>();
    }

//    public void initDeck() {
//        for (Value value : Value.values()) {
//            for (Suit suit : Suit.values()) {
//                Card card = new Card(suit, value);
//                deck.push(card);
//            }
//        }
//    }

    public Card getLast(){
        if(deck.empty()) {
            return null;
        }
        deck.peek().flip();
        return deck.peek();
    }

    public void addCard(Card card){
        deck.push(card);
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

}
