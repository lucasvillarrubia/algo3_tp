package Elements;

import Solitaire.Rules;
import Base.Deck;
import Base.Card;

import java.util.Collections;
import java.util.Random;

public class Stock extends Deck {

        // solo puede ser llenado una sola vez!
        private boolean filled;

        public Stock () {
                super();
                this.filled = false;
        }

        public void shuffle(int seed) {
                Random rn = new Random(seed);
                Collections.shuffle(deck, rn);
        }

        // el método que está en game, creo que debería devolver una carta
        // pero a la vez pienso que el waste debería tenerlo el propio stock
        public void showCard() {}

        // también el método que está en game
        public void reset() {}

        public boolean contains(Card card) {
                return this.contains(card);
        }

        protected void setFilled() {
                this.filled = true;
        }

        public boolean wasFilled() {
                return this.filled;
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
