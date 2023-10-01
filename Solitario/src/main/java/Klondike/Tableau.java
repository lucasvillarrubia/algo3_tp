package Klondike;


import Base.Card;
import Base.Deck;
import Base.Value;

import java.util.ArrayList;
import java.util.List;

public class Tableau {

    //private ArrayList<Deck> tableau;
    // para poder mover una parte del arrayList y concatenar
    private List<List<Card>> tableau;

    public Tableau(int columns) {
        this.tableau = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            tableau.add(new ArrayList<>());
        }
    }


    public List<Card> getDeck(int pos) {
        return this.tableau.get(pos);
    }


    public boolean isEmpty(){
        for (List<Card> cards: tableau){
            if (!cards.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPosition(int pos) {
        return pos >= 0 && pos < tableau.size();
    }

    public Card getLast(int pos){
        return tableau.get(pos).get((tableau.get(pos).size() - 1));
    }


    public boolean canReceive(Card card, int deckPosition){
        if(isValidPosition(deckPosition)){
            if ((tableau.get(deckPosition).isEmpty()) && (card.getValue() == Value.KING)){
                return true;
            } else if (!tableau.get(deckPosition).isEmpty()){
                int lastValue = getLast(deckPosition).getValue().getNumber();
                int receivedValue = card.getValue().getNumber();
                String lastColor = getLast(deckPosition).getSuit().getColor();
                String receivedColor = card.getSuit().getColor();
                boolean precedingValue = (lastValue - receivedValue) == 1;
                boolean sameColor = lastColor.equals(receivedColor);
                return (!sameColor && precedingValue);
            }
        }
        return false;
    }


    public void addCard(Card card, int deckPos) {
        if ((card != null) && (deckPos <= tableau.size())) {
            if(canReceive(card, deckPos)) {
                tableau.get(deckPos).add(card);
            }
        }
    }

    public Card drawCard(int from){
        if(tableau.get(from).isEmpty()) {
            return null;
        }
        return tableau.get(from).remove(tableau.get(from).size() - 1);
    }

    public void addCardSequence (List<Card> cards, int toDeck) {
        if (!cards.isEmpty()) {
            for (Card card : cards) {
                addCard(card, toDeck);
            }
        }
    }

    // sinoooo

    //if (canReceive(stock.getLast(), 1)) {addCard(stock.drawCard(), 1)}
    // y addCard ya no sería booleana y no tendría la verificación de canReceive




//    public boolean swapCard(Deck origin, Deck destination){
//        if(origin.isEmpty()) return false;
//        int originValue = origin.getLast().getValue().getNumber();
//        String originColor = origin.getLast().getSuit().getColor();
//        Card movedCard = origin.drawCard();
//        if (destination.isEmpty()) {
//            System.out.println(destination.isEmpty());
//            if (movedCard.getValue() == Value.KING) {
//                destination.addCard(movedCard);
//                return true;
//            }
//            else {
//                origin.addCard(movedCard);
//                return false;
//            }
//        }
//        int destinationValue = destination.getLast().getValue().getNumber();
//        String destinationColor = destination.getLast().getSuit().getColor();
//        if ((originColor.compareTo(destinationColor) != 0)  && ((destinationValue - originValue) == 1)){
//            destination.addCard(movedCard);
//            return true;
//        } else {
//            origin.addCard(movedCard);
//        }
//        return false;
//    }


    //LOGICA VIEJA
//    public Tableau(int columns) {
//        this.tableau = new ArrayList<>();
//        for (int i = 0; i < columns; i++) {
//            tableau.add(new Deck());
//        }
//    }
//
//
//    public Deck getDeck(int pos) {
//        return this.tableau.get(pos);
//    }
//
//    public boolean isEmpty(){
//        for (Deck deck: tableau){
//            if (!deck.isEmpty()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    public boolean canReceive(Card card, int deckPosition){
//        if ((tableau.get(deckPosition).isEmpty()) && (card.getValue() == Value.KING)) return true;
//        int lastValue = tableau.get(deckPosition).getLast().getValue().getNumber();
//        int receivedValue = card.getValue().getNumber();
//        String lastColor = tableau.get(deckPosition).getLast().getSuit().getColor();
//        String receivedColor = card.getSuit().getColor();
//        boolean precedingValue = (lastValue - receivedValue) == 1;
//        boolean sameColor = lastColor.equals(receivedColor);
//        if (!sameColor && precedingValue) return true;
//        return false;
//    }
//
//
//    public void addCard(Card card, int deckPos) {
//        if ((card != null) && (deckPos <= tableau.size())) {
//            if(canReceive(card, deckPos)) {
//                tableau.get(deckPos).addCard(card);
//            }
//        }
//    }


}
