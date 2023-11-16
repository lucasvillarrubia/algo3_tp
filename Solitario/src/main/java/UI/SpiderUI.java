package UI;

import Elements.Column;
import Solitaire.Game;
import GameType.SpiderRules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class SpiderUI{

    private static final int H =780;
    private static final int W =520;

    static SpiderRules spiderRules = new SpiderRules();
    static Game game;
    @FXML
    HBox stock = new HBox();

    @FXML
    private Button drawCardButton;
    @FXML
    Pane tableau;


    public void setUpGame(Stage stage) throws IOException {
        Random random = new Random();
        game = new Game(spiderRules, random.nextInt());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpiderBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        stock.getChildren().add(stockView.showStock());
        drawCardButton = stockView.showStock();


        for(int i = 0 ;i<10; i++ ){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            StackPane stackPane =(StackPane) tableau.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(columnView);
        }


        Scene klondikeScene = new Scene(root,H, W);
        stage.setScene(klondikeScene);
        stage.setTitle("Spider Solitaire");
        stage.show();

    }



}



//        Random random = new Random();
//        game = new Game(spiderRules, random.nextInt());
//        Button drawCardButton = new Button("Draw Card");
//        drawCardButton.setOnAction(e -> game.drawCardFromStock());

//Button moveCardsButton = new Button("Move Cards");
//        moveCardsButton.setOnAction(e -> moveCards());