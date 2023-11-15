package Elements;

import Base.Deck;
import Base.Card;
import java.util.Collections;
import java.util.Random;

public class Stock extends Deck {

        public void showPreviousCard() {
                Card card = deck.get(cardCount()-1);
                deck.remove(card);
                addCards(card);
        }

        public void showNextCard() {
                Card card = getLast();
                deck.remove(card);
                deck.add(card);
        }

        public void shuffle(int seed) {
                Random rn = new Random(seed);
                Collections.shuffle(deck, rn);
        }

        public boolean containsCard(Card card) {
                return deck.contains(card);
        }

}
