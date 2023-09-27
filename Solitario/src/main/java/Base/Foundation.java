package Base;

import java.util.Stack;

public class Foundation {

    //es en donde se van a ir apilando las cartas de la A a la K  (de menor a mayor)
    private Suit suit;
    private Stack<Card> cards;

    public Foundation(Suit suit) {
        this.suit = suit;
        this.cards = new Stack<>();
    }


    public boolean isFull(){
        if(cards.lastElement().getValue().equals(Value.KING)){
            return true;
        } else return false;
    }

    public Card addCard(Card card){
        Card topCard = getTopCard();
        if(topCard == null || card.getValue().getNumber() > topCard.getValue().getNumber() ){
            return cards.push(card);
        }
        return null;
    }

    public Card getTopCard(){
        if(cards.isEmpty()) {
            return null;
        }else{
            return cards.peek();}
    }


    public Card removeCard(){
        return cards.pop();
    }

    public Suit getSuit(){
        return this.suit;
    }


}
