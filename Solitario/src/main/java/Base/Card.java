package Base;

import java.io.Serializable;

public class Card implements Serializable {
    private final Suit suit;
    private final Value value;
    private boolean faceUp;

    public Card(Suit palo, Value valor) {
        this.suit = palo;
        this.value = valor;
        this.faceUp = false;
    }

    public void flip(){
        faceUp = !faceUp;
    }

    public boolean isFaceUp() {
        return faceUp;
    }
    
    public boolean isTheSameAs(Card card) {
        return (this.suit.equals(card.getSuit()) && this.value.equals(card.getValue()));
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Color getColor() {
        return suit.getColor();
    }

    public int getNumber() {
        return value.getNumber();
    }

}
