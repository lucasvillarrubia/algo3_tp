package UI;


import Base.Card;
import Base.Suit;
import Elements.Column;
import Elements.Foundation;
import Elements.Game;
import GameType.KlondikeRules;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class KlondikeUI{

    private static final int H =650;
    private static final int W =560;
    static KlondikeRules klondikeRules = new KlondikeRules();
    static Game game;
    private static final int AMOUNT_COLUMNS = 7;
    @FXML
    HBox stock = new HBox();
    @FXML
    Pane foundations;
    @FXML
    Pane tableau;
    CardView cardView;


    public void setUpGame(Stage stage) throws IOException {
        Random random = new Random();
        game = new Game(klondikeRules, 10);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KlondikeBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        stock.getChildren().add(stockView.showStock());




        for (Suit suit: Suit.values()) {
            Foundation foundation = game.getFoundationBySuit(suit);
            Rectangle r = new Rectangle();
            r.setStyle("-fx-background-color: black; -fx-pref-heigth: 79.0; -fx-pref-width: 61");
            r.setId(suit.toString());
            foundations.getChildren().add(r);
        }

        for(int i = 0 ;i<AMOUNT_COLUMNS; i++ ){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            tableau.getChildren().add(columnView);
        }


        Scene klondikeScene = new Scene(root,H, W);
        stage.setScene(klondikeScene);
        stage.setTitle("Klondike Solitaire");
        stage.show();
    }




//Elementos del klondike
/*
* Stock
* Waste
* Column
* Foundation
*
* */

}
//        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Card card = game.getStock().getLast();
//                Button waste = new Button();
//                waste.setGraphic(cardView.getImage(card.getValue().getNumber()+card.getSuit().toString()));
//                stock.getChildren().add(waste);
//                game.drawCardFromStock();
//            }
//        });