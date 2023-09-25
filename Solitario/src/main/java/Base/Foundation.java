package Base;

import java.util.Stack;

public class Foundation {

    //es en donde se van a ir apilando las cartas de la A a la K  (de menor a mayor)
    private Suit suit;
    private Stack<Card> cards;

    public Foundation() {
        initFoundation();
    }

    private void initFoundation(){
        for (Suit suit : Suit.values()) {
           this.suit = suit;
           this.cards = new Stack<>();
        }
    }

    public boolean isFull(){
        if(cards.lastElement().getValue().equals(Value.KING)){
            return true;
        } else return false;
    }

    public Card addCard(Card card){
        return cards.push(card);
    }

    public Card removeCard(){
        return cards.pop();
    }



}
