package Elements;

import Solitaire.Rules;
import Base.Deck;
import Base.Card;

import java.io.Serializable;
import java.util.Collections;
import java.util.Random;

public class Stock extends Deck implements Serializable {

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
//        public void showCard() {}

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

        @Override
        protected boolean containsCard(Card card) {
                return super.containsCard(card);
        }

        @Override
        public boolean acceptCard(Rules gameRules, Card card) {
                if (gameRules.acceptsCard(this, card) && !containsCard(card)) return addCards(card);
                else return false;
        }

        @Override
        public boolean acceptSequence(Rules gameRules, Column cards) {
                return false;
        }

}
