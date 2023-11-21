package Solitaire;

import Elements.*;

public class Movement implements DeckVisitor {

    private final VisitableDeck from;

    private final VisitableDeck to;

    private Column sequenceToMove;

    private Rules rules;

    private boolean validMove;

    private int cardIndex;

    private boolean sourceChecked;

    public Movement (VisitableDeck source, VisitableDeck goal) {
        this.from = source;
        this.to = goal;
        this.cardIndex = 0;
        this.sourceChecked = false;
    }

    public Movement (Column source, VisitableDeck goal, int index) {
        this(source, goal);
        this.cardIndex = index;
        this.sourceChecked = false;
    }

    protected boolean checkMoveByRules (Rules rules) {
        System.out.println("checkeo de move by rules");
        this.rules = rules;
        from.accept(this);
        this.sourceChecked = true;
        System.out.println("primero");
        if (!validMove) return false;
        to.accept(this);
        System.out.println("segundo");
        if (!validMove) return false;
        System.out.println("segundo");
        if (cardIndex == 0) {
            return moveCards(from, to);
        }else{
            System.out.println("tercero");
            return moveSequence(sequenceToMove, (Column)from, to);
        }
    }

    @Override
    public void visit(Stock s) {
        if (!sourceChecked) this.validMove = rules.givesCard(s);
        else if (cardIndex != 0 && sequenceToMove != null) this.validMove = rules.admitsSequence(s, sequenceToMove);
        else this.validMove = rules.acceptsCard(s, from.getLast());
    }

    @Override
    public void visit(Foundation f) {
        if (!sourceChecked) this.validMove = rules.givesCard(f);
        else if (cardIndex != 0 && sequenceToMove != null) this.validMove = rules.admitsSequence(f, sequenceToMove);
        else this.validMove = rules.acceptsCard(f, from.getLast());
    }

    @Override
    public void visit(Column c) {
        if (!sourceChecked) {
            this.validMove = rules.givesCard(c);
            if (cardIndex != 0){
                this.sequenceToMove = c.getSequence(cardIndex);
            }
        } else if (cardIndex != 0 && sequenceToMove != null){
            System.out.println("HERE 1");
            this.validMove = rules.admitsSequence(c, sequenceToMove);
        } else{
            System.out.println("HERE 2");
            this.validMove = rules.acceptsCard(c, from.getLast());
        }

    }

    private boolean moveCards(VisitableDeck from, VisitableDeck to) {
        if (from.getLast() == null) { return false; }
        return to.addCards(from.drawCard());
    }

    private boolean moveSequence(Column sequence, Column from, VisitableDeck to) {
        System.out.println("Entro aca en el movimiento de sec ");
        if (sequence == null) {
            System.out.println("Retorno FALSSO");
            return false;
        }
        System.out.println("Retorno VERDADERO");
        return to.addCards(sequence) && from.removeSequence(sequence);
    }

}