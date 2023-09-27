package Base;

public enum Suit {
    HEART("Red"),
    DIAMOND("Red"),
    SPADES("Black"),
    CLUBS("Black");
    private String color;
    Suit (String color) {
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
}
