package Visitor;

import Base.Card;

import java.util.Collection;

public class Column extends Deck implements RuleAcceptance {
        // column debería tener atributo index? (posicion en tableau)
        // falta chequear que esten flippeadas, creo
        public Card getCard(int pos){
                return deck.get(pos);
        }

        public Column getCards(int upToIndex) {
                Column subColumn = new Column();
                if (!subColumn.addCards(deck.subList(0, upToIndex))) return null;
                return subColumn;
        }

        @Override
        protected boolean addCards(Card card) {
                return super.addCards(card);
        }
        
        private boolean addCards(Collection<Card> cards) {
                if (cards == null) return false;
                deck.addAll(0, cards);
                return true;
        }
        
        @Override
        public boolean acceptsCard(RuleChecker addingChecker, Card card) {
                return this.addCards(card) && addingChecker.checkRule(this);
        }

        @Override
        public boolean canDrawCard(RuleChecker removingChecker) {
                return removingChecker.checkRule(this);
        }

        @Override
        public boolean admitsSequence(RuleChecker sequenceChecker) {
                return sequenceChecker.checkRule(this);
        }

}
