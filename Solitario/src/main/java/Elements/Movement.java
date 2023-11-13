package Elements;

public class Movement {

    private DeckType from;
    private DeckType to;

    public Movement(DeckType from, DeckType to) {
        this.from = from;
        this.to = to;
    }

    public Movement getMove() {
        return this;
    }

}
