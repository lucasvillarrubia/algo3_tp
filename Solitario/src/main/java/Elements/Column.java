package Elements;

import Base.Card;
import Base.Deck;

public class Column extends Deck implements Visitable {

        public Card getCard(int pos){
                return deck.get(pos);
        }

        public Column getSequence(int upToIndex) {
                if (upToIndex == 0 || !getCard(upToIndex).isFaceUp()) return null;
                Column subColumn = new Column();
                if (!subColumn.addCards(deck.subList(0, upToIndex+1))) return null;
                return subColumn;
        }

        @Override
        public Card drawCard() {
                Card drawn = super.drawCard();
                if(!isEmpty() && !getLast().isFaceUp()) getLast().flip();
                return drawn;
        }

        public boolean removeSequence(Column sequence) {
                for (int i = 0; i < sequence.cardCount(); i++) {
                        if(!deck.get(0).isTheSameAs(sequence.getCard(i))) {
                                return false;
                        }
                        drawCard();
                }
                if(!isEmpty() && !getLast().isFaceUp()) getLast().flip();
                return true;
        }

        @Override public void accept(DeckVisitor visitor) {
                visitor.visit(this);
        }


}
