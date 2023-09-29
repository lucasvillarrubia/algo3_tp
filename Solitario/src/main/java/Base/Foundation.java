package Base;

import java.util.Stack;

public class Foundation {

    private Suit suit;
    private Deck cards;

    public Foundation(Suit suit) {
        this.suit = suit;
        this.cards = new Deck();
    }


    public boolean isFull(){
        if (cards.isEmpty()) return false;
        if(cards.getLast().getValue().equals(Value.KING)){
            return true;
        } else return false;
    }

    public boolean canMove(Card card){
        if(cards.isEmpty()){
            return (card.getValue() == Value.ACE && card.getSuit() == suit);
        } else {
            Card topCard = cards.getLast();
            return (card.getSuit()==suit && card.getValue().getNumber() == topCard.getValue().getNumber() + 1);
        }
    }

    public boolean addCard(Card card){
        if(canMove(card)){
           cards.addCard(card);
           return true;
        }
        return false;
    }

    public Card getTopCard(){
        if(cards.isEmpty()) {
            return null;
        }else return cards.getLast();
    }


    public Card removeCard(){
        return cards.drawCard();
    }

    public Suit getSuit(){
        return this.suit;
    }



}

