package UI;

import Base.Card;
import Base.Suit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardView extends ImageView implements Clickable{

    private static final Map<Card, Image> cards = new HashMap<>();
    private static final String IMAGE_LOCATION = "images/cards/";
    private static final String IMAGE_SUFFIX = ".png";
    private static final String BACK_IMAGE_NAME = "back-card";
    private boolean clickState;
    private int cardIndex;
//    private Image cardImage;
//    private ImageView backView;


    public CardView() {
    }

    public CardView(Card card, int cardIndex) {
        this.cardIndex = cardIndex;
        this.clickState = false;

//        this.cardImage = getPhoto(card);
        if (card.isFaceUp()) {
            setImage(getPhoto(card));
            setOnMouseClicked(event -> handleCardClick(card));
        }
        else setImage(new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + BACK_IMAGE_NAME + IMAGE_SUFFIX))));
    }


    public ImageView getImage(Card card){
        if(card.isFaceUp()){
            Image image = cards.get(card);
            if(image == null){
                image = new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + card.getValue().getNumber() + card.getSuit().toString() + IMAGE_SUFFIX)));
                //ImageView i = new ImageView(image);
                cards.put(card, image);
            }
            ImageView i = new ImageView(image);
            i.setFitHeight(79);
            i.setFitWidth(61);
            i.setStyle("-fx-border-radius: 2; -fx-border-color: black");
            i.setOnMouseClicked(event -> handleCardClick(card));
            return i;
        } else {
            return getBack();
        }
    }

    public Image getPhoto(Card card){
        Image image = cards.get(card);
        if(image == null){
            image = new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + card.getValue().getNumber() + card.getSuit().toString() + IMAGE_SUFFIX)));
            //ImageView i = new ImageView(image);
            cards.put(card, image);
        }
        return image;
    }

    public ImageView getBack(){
        Image image = new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + BACK_IMAGE_NAME + IMAGE_SUFFIX)));
        ImageView back = new ImageView(image);
        back.setFitHeight(79);
        back.setFitWidth(61);
        return back;
    }

    public ImageView getFoundationImage(Suit suit){
        Image i = new Image(Objects.requireNonNull(CardView.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + suit.toString() + IMAGE_SUFFIX)));
        ImageView imageView = new ImageView(i);
        imageView.setFitHeight(79);
        imageView.setFitWidth(61);
        return imageView;
    }


    public void handleCardClick(Card card) {
        toggleCardClick();
        System.out.println("Card!"+ card.getSuit()+ "__"+ card.getValue());
    }

    @Override
    public void handleClick(MouseEvent event) {
        toggleCardClick();
    }

    public void toggleCardClick() {
        this.clickState = !clickState;
//        if (clickState) { System.out.println("Card Selected"); }
//        else System.out.println("Card descliqueada");
    }

    @Override
    public boolean estaClickeado() {
        return clickState;
    }


    @Override
    public void setIndex(int id) {
        this.cardIndex = id;
    }

    @Override
    public int getIndex() {
        return cardIndex;
    }

}
