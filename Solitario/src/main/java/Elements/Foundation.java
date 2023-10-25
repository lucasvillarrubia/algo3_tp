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
