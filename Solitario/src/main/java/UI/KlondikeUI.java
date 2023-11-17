package UI;


import Base.Card;
import Base.Suit;
import Elements.Column;
import Elements.Foundation;
import Solitaire.Game;
import GameType.KlondikeRules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Random;

public class KlondikeUI{

    private static final int H =650;
    private static final int W =560;
    private Game game;
    private final CardView cardView = new CardView();
    private static final int AMOUNT_COLUMNS = 7;
    @FXML
    HBox stock ;
    @FXML
    StackPane waste;
    @FXML
    Pane foundations;
    @FXML
    Pane tableau;


    public void initialize(){
        KlondikeRules klondikeRules = new KlondikeRules();
        Random random = new Random();
        game = new Game(klondikeRules, random.nextInt());
    }

    public void setUpGame(Stage stage) throws IOException {
        initialize();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KlondikeBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        Button stockButton = stockView.showStock();
        stock.getChildren().add(stockButton);
        initializeFoundations();

        updateTableauView();
        setEventHandlers();

        Scene klondikeScene = new Scene(root,H, W);
        stage.setScene(klondikeScene);
        stage.setTitle("Klondike Solitaire");
        stage.show();
    }



    private void setEventHandlers() {
        tableau.setOnMouseClicked(this::handleTableauClick);
        foundations.setOnMouseClicked(this::handleFoundationsClick);
        stock.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }

    private void handleFoundationsClick(MouseEvent event) {
        // Handle the click on the foundations area
        // You can implement logic to move cards or perform other actions
    }
    private void handleTableauClick(MouseEvent event) {

    }

    private void handleStockClick(MouseEvent event) {
        if(game.drawCardFromStock()){
            Card card = game.getStock().getLast();
            ImageView wasteView = cardView.getImage(card);
            //evaluar si == a la primer carta
            StackPane box = new StackPane(wasteView);
            waste.getChildren().add(box);
        }
        updateStockButton();
    }

    private void initializeFoundations(){
        int i = 0;
        for (Suit suit: Suit.values()) {
            Foundation foundation = game.getFoundationBySuit(suit);
            FoundationView foundationView = new FoundationView(foundation);
            StackPane stackPane = (StackPane) foundations.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(foundationView);
            i++;
        }
    }
    private void updateTableauView(){
        for(int i = 0 ;i<AMOUNT_COLUMNS; i++){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            columnView.setId(i);
            StackPane stackPane =(StackPane) tableau.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(columnView);
        }
    }

    private void updateStockButton(){
        StockView stockView = new StockView();
        if(game.getStock().isEmpty()){
            Button stockButton = stockView.showEmptyStock();
            stock.getChildren().clear();
            stock.getChildren().add(stockButton);
        }
    }


/*
* Waste -> corregir!!
*/

}