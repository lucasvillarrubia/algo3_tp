package Base;

import java.util.Stack;

public class Foundation {

    //es en donde se van a ir apilando las cartas de la A a la K  (de menor a mayor)
    private Suit suit;
    private Stack<Card> cards;

    public boolean isFull(){
        if(cards.lastElement().equals(Value.KING)){
            return true;
        } else return false;
    }

    public boolean addCard(Card card){
        return cards.add(card);
    }

    public Card removeCard(){
        return cards.pop();
    }


}
