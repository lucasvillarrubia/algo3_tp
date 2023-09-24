package Base;

public class Card {
    private Suit suit;
    private Value value;
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
}
