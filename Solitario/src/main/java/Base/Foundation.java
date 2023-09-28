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
        if (cards.isEmpty()) return false;
        if(cards.peek().getValue().equals(Value.KING)){
            return true;
        } else return false;
    }

    public boolean canMove(Card card){
        if(cards.empty()){
            return (card.getValue() == Value.ACE && card.getSuit() == suit);
        } else {
            Card topCard = cards.get(cards.size() - 1);
            return (card.getSuit()==suit && card.getValue().getNumber() == topCard.getValue().getNumber() + 1);
        }
    }

    public boolean addCard(Card card){
        Card topCard = getTopCard();
        if(canMove(card)){
           cards.push(card);
           return true;
        }
        return false;
    }

    public Card getTopCard(){
        if(cards.isEmpty()) {
            return null;
        }else return cards.peek();
    }


    public Card removeCard(){
        return cards.pop();
    }

    public Suit getSuit(){
        return this.suit;
    }



}

