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
import java.util.Random;


public class SpiderUI{

    private static final int H =780;
    private static final int W =520;
    private Game game;
    @FXML
    HBox stock = new HBox();
    @FXML
    Pane tableau;
    @FXML
    Pane foundations = new Pane();
    ClickState clickState;
    private ColumnView clickedColumnView;

    public void initialize(){
        SpiderRules spiderRules = new SpiderRules();
        Random random = new Random();
        game = new Game(spiderRules, 10);
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
                        System.out.println("Column Clicked! Column ID: " + columnView.getNumber());
                        if (clickState == ClickState.NO_CLICK) {
                            columnView.toggleColumnClick();
                            clickedColumnView = columnView;
                            clickState = ClickState.CLICKED;
                        } else if (clickState == ClickState.CLICKED) {
                            Column targetColumn = columnView.getColumn();
                            columnView.toggleColumnClick();
                            if (game.moveCards(clickedColumnView.getColumn(), targetColumn)) {
                                updateTableauView();
                                clickedColumnView=null;
                            }
                            clickState = ClickState.NO_CLICK;
                        }
                    }
                }
            }
        }
    }

    //cardView will need an index so each card has an identifier; and each card view will need a
    //handleClick so it can be identified.

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