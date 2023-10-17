package Klondike;

import Base.Card;
import Base.Deck;
import Base.Suit;
import Base.Value;

public class Foundation {

    private Suit suit;
    private Deck cards;

    public Foundation(Suit suit) {
        this.suit = suit;
        this.cards = new Deck();
    }


    public boolean isFull(){
        if (cards.isEmpty()) return false;
        return cards.getLast().getValue().equals(Value.KING);
    }


    public boolean canReceive(Card card){
        if(cards.isEmpty()){
            return (card.getValue() == Value.ACE && card.getSuit() == suit);
        } else {
            Card topCard = cards.getLast();
            return (card.getSuit()==suit && card.getValue().getNumber() == topCard.getValue().getNumber() + 1);
        }
    }

    public void addCard(Card card){
        if(card!=null) {
            if (canReceive(card)) {
                cards.addCard(card);
            }
        }
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

