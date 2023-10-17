package Visitor;

import Base.Card;

import java.util.Collection;

public class Column extends Deck implements Addeable {

        // falta chequear que esten flippeadas, creo
        public Card getCard(int pos){
                return deck.get(pos);
        }

        public Collection<Card> getCards(int upToIndex) {
                return deck.subList(0, upToIndex);
        }

        @Override
        public boolean addCards(Card card) {
                return super.addCards(card);
        }
        
        public boolean addCards(Collection<Card> cards) {
                if (cards == null) return false;
                deck.addAll(0, cards);
                return true;
        }
        
        @Override
        public boolean acceptCard(DeckVisitor cardAdder) {
                return cardAdder.checkCard(this, getLast());
        }

}
