package Base;

import java.util.*;
import Base.Card;

public class Deck {
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

}