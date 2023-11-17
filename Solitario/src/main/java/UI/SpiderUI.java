package UI;

import Elements.Column;
import Solitaire.Game;
import GameType.SpiderRules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SpiderUI{

    private static final int H =780;
    private static final int W =520;
    private Game game;
    @FXML
    HBox stock = new HBox();

    @FXML
    private Button drawCardButton;
    @FXML
    Pane tableau;
    @FXML
    Pane foundations ;
    ClickState clickState;
    private Column clickedColumn;

    public void initialize(){
        SpiderRules spiderRules = new SpiderRules();
        Random random = new Random();
        game = new Game(spiderRules, random.nextInt());
        clickState = ClickState.NO_CLICK;
    }

    public void setUpGame(Stage stage) throws IOException {
        initialize();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpiderBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        stock.getChildren().add(stockView.showStock());


        updateTableauView();
        setEventHandlers();

        Scene klondikeScene = new Scene(root,H, W);
        stage.setScene(klondikeScene);
        stage.setTitle("Spider Solitaire");
        stage.show();

    }

    private void setEventHandlers() {
        tableau.setOnMouseClicked(this::handleColumnClick);
        foundations.setOnMouseClicked(this::handleFoundationClick);
        stock.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }
    private void handleColumnClick(MouseEvent event) {
        if (event.getSource() instanceof Pane source) {
            for (Node child : source.getChildren()) {
                if (child instanceof StackPane) {
                    ColumnView columnView = (ColumnView) ((StackPane) child).getChildren().get(0);
                    if (columnView.isClicked()) {
                        if (clickState == ClickState.NO_CLICK) {
                            clickedColumn = columnView.getColumn();
                            clickState = ClickState.FIRST_CLICK;
                        } else if (clickState == ClickState.FIRST_CLICK) {
                            Column targetColumn = columnView.getColumn();
                            if (game.moveCards(clickedColumn, targetColumn)) {
                                updateTableauView();
                            }
                            clickState = ClickState.NO_CLICK;
                        }
                        //break;
                    }
                }
            }
        }
    }

    private void handleFoundationClick(MouseEvent event){

    }

    private void handleStockClick(MouseEvent event) {
        if(game.drawCardFromStock()){
            updateTableauView();
        }
        updateStockButton();
    }

    private void updateTableauView(){
        for(int i = 0 ;i<10; i++ ){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            columnView.setNumber(i);
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
}