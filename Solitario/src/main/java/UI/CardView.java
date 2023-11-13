package UI;

import Base.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardView extends ImageView {

    private static Map<Card, Image> cards = new HashMap<>();
    private static final String IMAGE_LOCATION = "resources/images/cards/";
    private static final String IMAGE_SUFFIX = ".png";

    private static final String BACK_IMAGE_NAME = "back-card";


    public CardView(Card card){
        super(getImage(card));
    }

    private static Image getImage(Card card) {
        Image image = cards.get(card);
        if(image==null){
            if(card.isFaceUp()){
                image = new Image(Objects.requireNonNull(CardView.class.getClassLoader()
                        .getResourceAsStream(IMAGE_LOCATION + card.getValue().getNumber() + card.getSuit().name().toLowerCase()+ IMAGE_SUFFIX)));
            } else{
                image = new Image(Objects.requireNonNull(CardView.class.getClassLoader()
                        .getResourceAsStream(IMAGE_LOCATION + BACK_IMAGE_NAME+ IMAGE_SUFFIX)));
            }
            cards.put(card,image);
        }
        return image;
    }

    public void setBackImage() {
        setImage(new Image(Objects.requireNonNull(CardView.class.getClassLoader()
                .getResourceAsStream(IMAGE_LOCATION + BACK_IMAGE_NAME))));
    }


}
