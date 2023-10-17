package Visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Base.Card;

public class Tableau {
        
        private List<Column> tableau;

        public Tableau(int columns) {
                this.tableau = new ArrayList<>();
                for (int i = 0; i < columns; i++) {
                        tableau.add(new Column());
                }
        }

        public Column getDeck(int pos) {
                return this.tableau.get(pos);
        }

        public boolean isEmpty() {
                return tableau.stream().allMatch(Column::isEmpty);
        }

        private boolean isValidPosition(int pos) {
                return pos >= 0 && pos < tableau.size();
        }

        public Card getLast(int from) {
                Column column = getDeck(from);
                return column.isEmpty() ? null : column.getLast();
        }

        public boolean addCards(Card card, int deckPos) {
                if (card == null || !isValidPosition(deckPos)) { return false; }
                return getDeck(deckPos).addCards(card);
        }
        
        // la collección es una sublist de arrayList
        private boolean addCards(Collection<Card> cards, int deckPos) {
                if (cards.isEmpty() || !isValidPosition(deckPos)) { return false; }
                return getDeck(deckPos).addCards(cards);
        }

        public Card drawCard(int from) {
                Column column = getDeck(from);
                if (column.isEmpty()) return null;
                return column.drawCard();
        }

        public boolean moveCards (int fromDeckPos, int toDeckPos) { 
                Card moved = getDeck(fromDeckPos).getLast();
                if (moved == null) { return false; }
                else if (getDeck(toDeckPos).addCards(moved) && getDeck(fromDeckPos).removeCard(moved)) {
                        return true;
                }
                return false;
        }

        public boolean moveCards (int fromDeckPos, int toDeckPos, int cardIndex) {
                Collection<Card> cardsToMove = getDeck(fromDeckPos).getCards(cardIndex);
                if (cardsToMove == null) { return false; }
                else if (addCards(cardsToMove, toDeckPos)) {
                        for (Card card : cardsToMove) {
                                if (!getDeck(fromDeckPos).removeCard(card)) { return false; }
                        }
                        return true;
                }
                return false;
        }

}
