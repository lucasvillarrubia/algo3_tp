package Base;

public enum Suit {
    HEART(Color.RED),
    DIAMOND(Color.RED),
    SPADES(Color.BLACK),
    CLUBS(Color.BLACK);

    private final Color color;

    Suit(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }


}
