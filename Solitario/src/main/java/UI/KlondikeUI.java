package UI;


import Base.Suit;
import Elements.Column;
import Elements.Foundation;
import Solitaire.Game;
import GameType.KlondikeRules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    private static KlondikeRules klondikeRules = new KlondikeRules();
    private static Game game;
    private static final int AMOUNT_COLUMNS = 7;
    @FXML
    HBox stock ;
    @FXML
    Pane foundations;
    @FXML
    Pane tableau;


    public void initialize(){
        Random random = new Random();
        game = new Game(klondikeRules, random.nextInt());
    }

    public void setUpGame(Stage stage) throws IOException {
        initialize();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KlondikeBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        stock.getChildren().add(stockView.showStock());

        int i = 0;
        for (Suit suit: Suit.values()) {
            Foundation foundation = game.getFoundationBySuit(suit);
            FoundationView foundationView = new FoundationView(foundation);
            StackPane stackPane = (StackPane) foundations.getChildren().get(i);
            stackPane.getChildren().clear();
            foundations.getChildren().add(foundationView);
            i ++;
        }

        for(i = 0 ;i<AMOUNT_COLUMNS; i++){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            StackPane stackPane =(StackPane) tableau.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(columnView);
        }

        Scene klondikeScene = new Scene(root,H, W);
        stage.setScene(klondikeScene);
        stage.setTitle("Klondike Solitaire");
        stage.show();
    }




//Elementos del klondike
/*
* Waste
* Foundation
* */

}
//        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Card card = game.getStock().getLast();
//                StackPane waste = new StackPane();
//                waste.setGraphic(cardView.getImage(card.getValue().getNumber()+card.getSuit().toString()));
//                stock.getChildren().add(waste);
//                game.drawCardFromStock();
//            }
//        });