package UI;


import Base.Card;
import Solitaire.Game;
import Solitaire.Movement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class KlondikeUI extends GameUI{

    private static final int W =650;
    private static final int H =600;
    private final CardView cardView = new CardView();
    private static final int AMOUNT_COLUMNS = 7;
    private static final int AMOUNT_FOUNDATIONS = 4;
    @FXML
    StackPane waste;
    private int stockIndex = 0;
    private boolean wasteIsClicked = true;

    private static final String FILE_PATH = "savedGame.txt";

    @Override
    public void setUpGame(Stage stage, Game game) throws IOException {
        this.clickState = ClickState.NO_CLICK;
        this.LocalStage = stage;
        this.game = game;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KlondikeBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        Button stockButton = stockView.showStock();
        stockPile.getChildren().add(stockButton);
        updateFoundations(AMOUNT_FOUNDATIONS);
        updateTableauView(AMOUNT_COLUMNS);
        setEventHandlers(AMOUNT_COLUMNS, AMOUNT_FOUNDATIONS);
        Scene klondikeScene = new Scene(root,W, H);
        stage.setScene(klondikeScene);
        stage.setTitle("Klondike Solitaire");
        stage.setOnCloseRequest(event -> {
            try {
                game.serialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        stage.show();
    }

    @Override
    public void acceptMoveToFoundation() throws IOException {
        if (clickState != ClickState.CLICKED) return;
        if (clickedColumnView != null) {
            if (game.makeAMove(new Movement(clickedColumnView.getColumn(), clickedFoundation))) {
                updateColumnView(clickedColumnView);
                updateFoundations(AMOUNT_FOUNDATIONS);
            }
            clickedColumnView = null;
            clickedFoundation = null;
            clickState = ClickState.NO_CLICK;
        } else if (wasteIsClicked) {
            if (game.makeAMove(new Movement(game.getStock(), clickedFoundation))) {
                updateFoundations(AMOUNT_FOUNDATIONS);
                stockIndex--;
                game.drawCardFromStock();
                updateWaste();
                if (stockIndex == 0 ) waste.getChildren().clear();
            }
            wasteIsClicked = false;
            clickedFoundation = null;
            clickState = ClickState.NO_CLICK;
        }
        checkWinningCondition(FILE_PATH);
    }

    @Override
    public void acceptMoveToColumn(ColumnView columnView){
        if(clickedCard != null){
            super.acceptMoveToColumn(columnView);
        } else if (wasteIsClicked) {
            clickedColumnView = columnView;
            if (game.makeAMove(new Movement(game.getStock(), clickedColumnView.getColumn()))) {
                stockIndex--;
                game.drawCardFromStock();
                updateWaste();
                if (stockIndex == 0) waste.getChildren().clear();
            }
            wasteIsClicked = false;
            updateColumnView(clickedColumnView);
        } else if (clickedFoundation != null) {
            clickedColumnView = columnView;
            if (game.makeAMove(new Movement(clickedFoundation, clickedColumnView.getColumn()))) {
                updateFoundations(AMOUNT_FOUNDATIONS);
            }
            updateColumnView(clickedColumnView);
        }

        clickedColumnView = null;
        clickedCard = null;
        clickState = ClickState.NO_CLICK;
    }

    @Override
    public void updateStockButton(){
        StockView stockView = new StockView();
        if(game.getStock().isEmpty()){
            Button stockButton = stockView.showEmptyStock();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
        }
        if (stockIndex == game.getStock().cardCount()) {
            Button stockButton = stockView.showEmptyStock();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
            stockPile.getChildren().get(0).setOnMouseClicked(this::handleEmptyStockClick);
            stockIndex = 0;
        }
    }

    private void handleEmptyStockClick(MouseEvent event) {
        StockView stockView = new StockView();
        Button stockButton = stockView.showStock();
        waste.getChildren().clear();
        stockPile.getChildren().clear();
        stockPile.getChildren().add(stockButton);
        stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }

    @Override
    public void handleStockClick(MouseEvent event){
        stockIndex++;
        game.drawCardFromStock();
        updateStockButton();
        updateWaste();
    }

    private void handleWasteCardClick(MouseEvent event) {
        clickState = ClickState.CLICKED;
        wasteIsClicked = true;
    }

    private void updateWaste() {
        if (!game.getStock().isEmpty()) {
            Card card = game.getStock().getLast();
            ImageView wasteView = cardView.getImage(card);
            StackPane cardStackPane = new StackPane(wasteView);
            waste.getChildren().add(cardStackPane);
            waste.setOnMouseClicked(this::handleWasteCardClick);
        }
    }

}