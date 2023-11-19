package Elements;

import Base.Card;
import Base.Deck;

import java.util.ArrayList;

public abstract class VisitableDeck extends Deck {

    public abstract void accept(DeckVisitor visitor);

    public boolean addCards(Column cards) {
        if (cards == null) return false;
        ArrayList<Card> cardsCollection = new ArrayList<>();
        for (int i = cards.cardCount() - 1; i >= 0;  i--) {
            cardsCollection.add(0, cards.getCard(i));
        }
        return addCards(cardsCollection);
    }

}
