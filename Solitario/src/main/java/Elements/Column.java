package Elements;

import Base.Deck;
import Solitaire.Rules;
import Base.Card;

import java.util.Collection;

public class Column extends Deck {
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
        
        public boolean acceptsCard(Rules gameRules, Card card) {
                return this.addCards(card) && gameRules.acceptsCard(this);
        }

        public boolean canDrawCard(Rules gameRules) {
                return gameRules.givesCard(this);
        }

        public boolean admitsSequence(Rules gameRules) {
                return gameRules.admitsSequence(this);
        }

}
