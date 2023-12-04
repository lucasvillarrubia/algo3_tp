package UI;


import Base.Deck;
import Solitaire.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class KlondikeUI extends GameUI{

    private static final int W =789;
    private static final int H =718;
    private static final int AMOUNT_COLUMNS = 7;
    private static final int AMOUNT_FOUNDATIONS = 4;
    @FXML
    StackPane waste;
    private int stockIndex = 0;
    private boolean showingEmptyStock;

    @Override
    public void setUpGame(Stage stage, Game game) throws IOException {
        this.LocalStage = stage;
        this.game = game;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KlondikeBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        Button stockButton = stockView.showStock();
        this.showingEmptyStock = false;
        stockPile.getChildren().add(stockButton);
        waste.getChildren().add(new WasteView(this.game.getStock()));
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
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void updateDeckView(Clickable deckView) {
        super.updateDeckView(deckView);
        if (deckView instanceof WasteView) {
            if (stockIndex != 0) stockIndex--;
            game.drawCardFromStock();
            updateWaste();
            if (stockIndex == 0 && !showingEmptyStock) {
                ((WasteView)waste.getChildren().get(0)).getChildren().clear();
            }
        }
    }

    @Override
    public void setEventHandlers(int columns, int foundation) {
        super.setEventHandlers(AMOUNT_COLUMNS, AMOUNT_FOUNDATIONS);
        waste.setOnMouseClicked(event -> {
            try {
                handleClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void updateStockButton(){
        StockView stockView = new StockView();
        if(game.getStock().isEmpty()){
            Button stockButton = stockView.showEmptyStock();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
            showingEmptyStock = true;
        }
        if (stockIndex == game.getStock().cardCount()) {
            Button stockButton = stockView.showEmptyStock();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
            stockPile.getChildren().get(0).setOnMouseClicked(this::handleEmptyStockClick);
            stockIndex = 0;
            showingEmptyStock = true;
        }
    }

    private void handleEmptyStockClick(MouseEvent event) {
        if (!game.getStock().isEmpty()) {
            StockView stockView = new StockView();
            Button stockButton = stockView.showStock();
            ((WasteView)waste.getChildren().get(0)).getChildren().clear();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
            stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
            showingEmptyStock = false;
        }
    }

    @Override
    public void handleStockClick(MouseEvent event){
        if (sourceDeck != null) {
            if (!((Deck)sourceDeck.getDeck()).isEmpty()) sourceDeck.turnOffSelectedCard();
            sourceDeck = null;
        }
        stockIndex++;
        game.drawCardFromStock();
        updateStockButton();
        updateWaste();
    }

    private void updateWaste() {
        ((WasteView) waste.getChildren().get(0)).update();
    }

}