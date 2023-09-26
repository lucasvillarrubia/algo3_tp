package Base;

import java.util.ArrayList;

public class Tableau {

    //columna de cartas apiladas con la primera de frente y el resto hacia abajo

    private ArrayList<Deck> tableau;


    public Tableau() {
        this.tableau = new ArrayList<>() ;
    }

    public void addPile(Deck pile){
        this.tableau.add(pile);
    }

    public boolean sendCard(Deck origin, Deck destination){
       //origin.getLast().getValue().compareTo(destination.getLast().getValue());
        Card movedCard = origin.sendCard();
        int origen = origin.getLast().getValue().ordinal();
        int destino = destination.getLast().getValue().ordinal();
        boolean sameSuit = origin.getLast().getSuit().getColor().equals(destination.getLast().getSuit().getColor());
        if (sameSuit || (destino - origen) != 1){
            origin.addCard(movedCard);
            return false;
        } else {
            destination.addCard(movedCard);
        }
        return true;
    }



}
