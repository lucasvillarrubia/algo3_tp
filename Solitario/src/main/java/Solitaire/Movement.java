package Solitaire;

import Base.Card;
import Base.Deck;
import Elements.*;

import java.util.ArrayList;

public class Movement implements DeckVisitor {

    private final Visitable from;

    private final Visitable to;

    private Column sequenceToMove;

    private Rules rules;

    private boolean validMove;

    private int cardIndex;

    private boolean sourceChecked;

    public Movement (Visitable source, Visitable goal) {
        this.from = source;
        this.to = goal;
        this.cardIndex = 0;
        this.sourceChecked = false;
    }

    public Movement (Visitable source, Visitable goal, int index) {
        this(source, goal);
        this.cardIndex = index;
        this.sourceChecked = false;
    }

    protected boolean checkMoveByRules (Rules rules) {
        this.rules = rules;
        from.accept(this);
        this.sourceChecked = true;
        if (!validMove) return false;
        to.accept(this);
        if (!validMove) return false;
        if (cardIndex == 0) {
            return moveCards((Deck)from, (Deck)to);
        }else{
            return moveSequence(sequenceToMove, (Column)from, (Deck)to);
        }
    }

    @Override
    public void visit(Stock s) {
        if (!sourceChecked) this.validMove = rules.givesCard(s) && !s.isEmpty();
        else if (cardIndex != 0 && sequenceToMove != null) this.validMove = rules.admitsSequence(s, sequenceToMove);
        else if (cardIndex == 0) this.validMove = rules.acceptsCard(s, ((Deck)from).getLast());
        else validMove = false;
    }

    @Override
    public void visit(Foundation f) {
        if (!sourceChecked) this.validMove = rules.givesCard(f) && !f.isEmpty();
        else if (cardIndex != 0 && sequenceToMove != null) this.validMove = rules.admitsSequence(f, sequenceToMove);
        else if (cardIndex == 0) this.validMove = rules.acceptsCard(f, ((Deck)from).getLast());
        else validMove = false;
    }

    @Override
    public void visit(Column c) {
        if (!sourceChecked) {
            this.validMove = rules.givesCard(c) && !c.isEmpty();
            if (cardIndex != 0){
                this.sequenceToMove = c.getSequence(cardIndex);
            }
        }
        else if (cardIndex != 0 && sequenceToMove != null) this.validMove = rules.admitsSequence(c, sequenceToMove);
        else if (cardIndex == 0) this.validMove = rules.acceptsCard(c, ((Deck)from).getLast());
        else validMove = false;
    }

    private boolean moveCards(Deck from, Deck to) {
        if (from.getLast() == null) { return false; }
        return to.addCards(from.drawCard());
    }

    private boolean moveSequence(Column sequence, Column from, Deck to) {
        if (sequence == null) { return false; }
        ArrayList<Card> cardsCollection = new ArrayList<>();
        for (int i = sequence.cardCount() - 1; i >= 0;  i--) {
            cardsCollection.add(0, sequence.getCard(i));
        }
        return to.addCards(cardsCollection) && from.removeSequence(sequence);
    }

}