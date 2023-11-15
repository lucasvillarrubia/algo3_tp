package Base;

import Elements.Column;

import java.io.Serializable;
import java.util.*;

public abstract class Deck implements Serializable {

        protected ArrayList<Card> deck;

        public Deck(){
                this.deck = new ArrayList<>();
        }

        public boolean addCards(Card card) {
                if (card == null) return false;
                deck.add(0, card);
                return true;
        }

        protected boolean addCards(Collection<Card> cards) {
                if (cards == null) return false;
                deck.addAll(0, cards);
                return true;
        }

        public boolean addCards(Column cards) {
                if (cards == null) return false;
                ArrayList<Card> cardsCollection = new ArrayList<>();
                for (int i = cards.cardCount() - 1; i >= 0;  i--) {
                        cardsCollection.add(0, cards.getCard(i));
                }
                return addCards(cardsCollection);
        }

        public Card getLast(){
                if(deck.isEmpty()) return null;
                return deck.get(0);
        }

        public Card drawCard(){
                if(deck.isEmpty()) return null;
                return deck.remove(0);
        }

        public boolean isEmpty () {
                return deck.isEmpty();
        }

        public int cardCount () {
                return deck.size();
        }

}