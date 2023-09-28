package Base;

import java.util.ArrayList;

public class Tableau {

    private ArrayList<Deck> tableau;


    public Tableau() {
        this.tableau = new ArrayList<>() ;
    }

    public void addPile(Deck deck){
        this.tableau.add(deck);
    }



    public Deck getDeck(int pos) { return this.tableau.get(pos); }

    public boolean isEmpty(){
        boolean allEmpty = true;
        for (Deck deck: tableau){
            if (!deck.isEmpty()) {
                allEmpty = false;
            }
        }
        return allEmpty;
    }


    public boolean swapCard(Deck origin, Deck destination){
        if(origin.isEmpty()) return false;
        int originValue = origin.getLast().getValue().getNumber();
        String originColor = origin.getLast().getSuit().getColor();
        Card movedCard = origin.drawCard();
        System.out.println("esta vacia el origen " + origin.isEmpty());
        if (destination.isEmpty()) {
            System.out.println(destination.isEmpty());
            if (movedCard.getValue() == Value.KING) {
                destination.addCard(movedCard);
                System.out.println("Entro en la 1");
                return true;
            }
            else {
                origin.addCard(movedCard);
                System.out.println("Entro en la 2");
                return false;
            }
        }
        int destinationValue = destination.getLast().getValue().getNumber();
        String destinationColor = destination.getLast().getSuit().getColor();
        if ((originColor.compareTo(destinationColor) != 0)  && ((destinationValue - originValue) == 1)){
            destination.addCard(movedCard);
            System.out.println("Entro en la 3");
            return true;
        } else {
            origin.addCard(movedCard);
        }
        System.out.println("Entro en la 4");
        return false;
    }


}
