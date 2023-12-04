package UI;


import Base.Card;
import Base.Suit;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class CardView extends ImageView {

    private static final Map<Card, Image> cards = new HashMap<>();
    private static final String IMAGE_LOCATION = "images/cards/";
    private static final String IMAGE_SUFFIX = ".png";
    private static final String BACK_IMAGE_NAME = "back-card";
    private boolean clickState;
    private int cardIndex;
    private static final int H = 96;
    private static final int W = 74;
    private static final DropShadow SELECTED_EFFECT = new DropShadow();
    static {
        SELECTED_EFFECT.setColor(Color.YELLOW);
        SELECTED_EFFECT.setWidth(W);
        SELECTED_EFFECT.setHeight(H);
    }

    public CardView() {
        this.clickState = false;
        setImage(new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + BACK_IMAGE_NAME + IMAGE_SUFFIX))));
        setFitHeight(H);
        setFitWidth(W);
        setStyle("-fx-border-radius: 2; -fx-border-color: black");
        setSmooth(true);
    }

    public CardView(Suit suit) {
        this();
        setImage(new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + suit.toString().toLowerCase() + IMAGE_SUFFIX))));
    }

    public CardView(Card card, int cardIndex) {
        this();
        this.cardIndex = cardIndex;
        setImage(getPhoto(card));
        if(card.isFaceUp()) setOnMouseClicked(event -> handleCardClick());
    }

    public Image getPhoto(Card card){
        if (card.isFaceUp()) {
            Image image = cards.get(card);
            if(image == null){
                image = new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + card.getValue().getNumber() + card.getSuit().toString().toLowerCase() + IMAGE_SUFFIX)));
                cards.put(card, image);
            }
            return image;
        }
        else return (new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + BACK_IMAGE_NAME + IMAGE_SUFFIX))));
    }

    public void handleCardClick() {
        setEffect(SELECTED_EFFECT);
        clickState = true;
    }

    public void unselectCard() {
        setEffect(null);
        clickState = false;
    }

    public boolean isClicked() { return clickState; }

    public int getIndex() { return cardIndex; }

}