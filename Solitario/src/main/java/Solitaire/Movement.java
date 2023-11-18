package Solitaire;

import Base.Deck;
import Elements.Column;
import Elements.DeckVisitor;
import Elements.Foundation;
import Elements.Stock;

public class Movement implements DeckVisitor {

    private Deck from;
    private Deck to;
    private boolean validMove;

    public Movement() {
        this.from = null;
        this.to = null;
        this.validMove = true;
    }

    @Override
    public void visit(Stock s) {
        if (from == null) {
            this.from = s;
            this.validMove = moveRules.givesCard(s);
        }
        else {
            this.to = s;
            this.validMove = moveRules.acceptsCard(s, from.getLast());
        }
    }

    @Override
    public void visit(Foundation f) {
        if (from == null) {
            this.from = f;
            this.validMove = moveRules.givesCard(f);
        }
        else {
            this.to = f;
            this.validMove = moveRules.acceptsCard(f, from.getLast());
        }
    }

    @Override
    public void visit(Column c) {
        if (from == null) {
            this.from = c;
            this.validMove = moveRules.givesCard(c);
        }
        else {
            this.to = c;
            this.validMove = moveRules.acceptsCard(c, from.getLast());
        }
    }

    public boolean moveCards(Deck from, Deck to) {
        if (from.getLast() == null) { return false; }
        return to.addCards(from.drawCard());
    }

    public boolean moveCards(Column from, Deck to, int index) {
        Column moved = from.getSequence(index);
        if (moved == null) { return false; }
        return to.addCards(moved) && from.removeSequence(moved);
    }
}
