package UI;

import Base.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardView extends ImageView {


    private static final Map<Card, Image> cards = new HashMap<>();
    private static final String IMAGE_LOCATION = "images/cards/";
    private static final String IMAGE_SUFFIX = ".png";

    private static final String BACK_IMAGE_NAME = "back-card";


    public ImageView getImage(Card code){
        Image image = cards.get(code);
        if(image == null){
            image = new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + code.getValue().getNumber() + code.getSuit().toString() + IMAGE_SUFFIX)));
            cards.put(code, image);
        }
        ImageView i = new ImageView(image);
        i.setFitHeight(79);
        i.setFitWidth(61);
        return i;
    }

    public ImageView getBack(){
        Image image = new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + BACK_IMAGE_NAME + IMAGE_SUFFIX)));
        ImageView back = new ImageView(image);
        back.setFitHeight(79);
        back.setFitWidth(61);
        return back;
    }



}
