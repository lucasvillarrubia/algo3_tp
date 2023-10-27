package Base;

import Solitaire.Rules;
import Elements.Column;
import java.util.*;

public abstract class Deck {
        protected ArrayList<Card> deck;

        public Deck(){
                this.deck = new ArrayList<>();
        }

        protected boolean addCards(Card card) {
                if (card == null) return false;
                deck.add(0, card);
                return true;
        }

        public Card getLast(){
                if(deck.isEmpty()) return null;
                deck.get(0).flip();
                return deck.get(0);
        }

        public Card drawCard(){
                if(deck.isEmpty()) return null;
                deck.get(0).flip();
                return deck.remove(0);
        }

        public boolean removeCard(Card card) {
                return deck.remove(card);
        } 

        public boolean isEmpty () {
                return deck.isEmpty();
        }

        public int cardCount () {
                return deck.size();
        }

        protected boolean containsCard(Card card) {
                return deck.contains(card);
        }

        public abstract boolean acceptCard(Rules gameRules, Card card);
        public abstract boolean acceptSequence(Rules gameRules, Column cards);
        public abstract boolean givesCard(Rules gameRules);


}