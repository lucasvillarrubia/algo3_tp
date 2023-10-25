package Elements;

import Solitaire.Rules;
import Base.Deck;
import Base.Suit;
import Base.Value;
import Base.Card;

public class Foundation extends Deck {

        private Suit suit;

        public Foundation(Suit suit) {
                super();
                this.suit = suit;
        }

        public boolean isFull(){
                if (deck.isEmpty()) return false;
                return this.getLast().getValue() == Value.KING;
        }

        public Suit getSuit(){
                return this.suit;
        }

        @Override
        protected boolean addCards(Card card) {
                return super.addCards(card);
        }

        public boolean acceptCard(Rules gameRules, Card card) {
                if (gameRules.acceptsCard(this, card)) return addCards(card);
                else return false;
        }
}
