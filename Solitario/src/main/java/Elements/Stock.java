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


        // llenaría el mazo de acuerdo a las reglas del solitario
        // por única vez, y cambia el estado de filled a true
        public void fill() {}

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

        public boolean wasFilled() {
                return this.filled;
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
