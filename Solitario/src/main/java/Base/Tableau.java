package Base;

import java.util.ArrayList;

public class Tableau {

    private ArrayList<Deck> tableau;

    public Tableau(int columns) {
        this.tableau = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            tableau.add(new Deck());
        }
    }

//    public void addDeck(Deck deck){
//        this.tableau.add(deck);
//    }

    public Deck getDeck(int pos) {
        return this.tableau.get(pos);
    }

    public boolean isEmpty(){
        for (Deck deck: tableau){
            if (!deck.isEmpty()) {
                return false;
            }
        }
        return true;
    }

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


}
