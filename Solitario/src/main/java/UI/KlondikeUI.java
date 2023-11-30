package UI;


import Base.Card;
import Elements.Foundation;
import Solitaire.Game;
import Solitaire.Movement;
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
    private boolean wasteIsClicked = false;

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
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void acceptMoveToFoundation(FoundationView foundationView) throws IOException {
        if (clickState == ClickState.CLICKED) {
            if (clickedColumnView != null) {
                Foundation targetFoundation = foundationView.getFoundation();
                if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetFoundation))) {
                    updateColumnView(clickedColumnView);
                    updateFoundations(AMOUNT_FOUNDATIONS);
                }
                if (clickedColumnView.getChildren().size() != 0) ((CardView)clickedColumnView.getChildren().get(clickedColumnView.getChildren().size()-1)).toggleCardClick();
                clickedColumnView = null;
            } else if (wasteIsClicked) {
                Foundation targetFoundation = foundationView.getFoundation();
                if (game.makeAMove(new Movement(game.getStock(), targetFoundation))) {
                    updateFoundations(AMOUNT_FOUNDATIONS);
                    stockIndex--;
                    game.drawCardFromStock();
                    updateWaste();
                    wasteIsClicked = false;
                    if (stockIndex == 0) waste.getChildren().clear();
                }
                if (wasteIsClicked && waste.getChildren().size() != 0) ((CardView)waste.getChildren().get(0)).toggleCardClick();
                wasteIsClicked = false;
            }
            checkWinningCondition();
        }
        if (clickedFoundationView != null) {
            if (clickedFoundationView.getChildren().size() != 0) {
                if(((CardView)clickedFoundationView.getChildren().get(0)).isClicked()){
                    ((CardView)clickedFoundationView.getChildren().get(0)).toggleCardClick();
                }
            }
        }
        clickedFoundationView = null;
        ((CardView)foundationView.getChildren().get(0)).toggleCardClick();
        clickState = ClickState.NO_CLICK;
    }

    @Override
    public void acceptMoveToColumn(ColumnView columnView){
        if(clickedCard != null && clickedColumnView != null){
            super.acceptMoveToColumn(columnView);
        } else if (wasteIsClicked) {
            clickedColumnView = columnView;
            if (game.makeAMove(new Movement(game.getStock(), clickedColumnView.getColumn()))) {
                stockIndex--;
                game.drawCardFromStock();
                updateWaste();
                wasteIsClicked = false;
                if (stockIndex == 0) waste.getChildren().clear();
            }
            if (wasteIsClicked && waste.getChildren().size() != 0) ((CardView)waste.getChildren().get(0)).toggleCardClick();
            wasteIsClicked = false;
            updateColumnView(clickedColumnView);
        } else if (clickedFoundationView != null) {
            clickedColumnView = columnView;
            if (game.makeAMove(new Movement(clickedFoundationView.getFoundation(), clickedColumnView.getColumn()))) {
                updateFoundations(AMOUNT_FOUNDATIONS);
            }
            if (clickedColumnView.getChildren().size() != 0) ((CardView)clickedColumnView.getChildren().get(clickedColumnView.getChildren().size()-1)).toggleCardClick();
            updateColumnView(clickedColumnView);
            if (clickedFoundationView.getChildren().size() != 0) ((CardView)clickedFoundationView.getChildren().get(0)).toggleCardClick();
            clickedFoundationView = null;
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
        if (clickState == ClickState.CLICKED) clickState = ClickState.NO_CLICK;
        stockIndex++;
        game.drawCardFromStock();
        updateStockButton();
        updateWaste();
    }

    private void handleWasteCardClick(MouseEvent event) {
        if (clickState == ClickState.NO_CLICK) {
            wasteIsClicked = true;
            clickState = ClickState.CLICKED;
        }
        else {
            if(clickedColumnView != null) {
                if (wasteIsClicked && waste.getChildren().size() != 0) ((CardView)waste.getChildren().get(0)).toggleCardClick();
                if (clickedColumnView.getChildren().size() != 0) {
                    if(((CardView)clickedColumnView.getChildren().get(clickedColumnView.getChildren().size()-1)).isClicked()){
                        ((CardView)clickedColumnView.getChildren().get(clickedColumnView.getChildren().size()-1)).toggleCardClick();
                    }
                }
                wasteIsClicked = false;
                clickedColumnView = null;
            }
            if(clickedFoundationView != null) {
                if (wasteIsClicked && waste.getChildren().size() != 0) ((CardView)waste.getChildren().get(0)).toggleCardClick();
                if (clickedFoundationView.getChildren().size() != 0) {
                    if(((CardView)clickedFoundationView.getChildren().get(0)).isClicked()){
                        ((CardView)clickedFoundationView.getChildren().get(0)).toggleCardClick();
                    }
                }
                wasteIsClicked = false;
                clickedFoundationView = null;
            }
            clickState = ClickState.NO_CLICK;
        }
    }

    private void updateWaste() {
        if (!game.getStock().isEmpty()) {
            Card card = game.getStock().getLast();
            CardView wasteView = new CardView(card, 0);
            waste.getChildren().clear();
            waste.getChildren().add(wasteView);
            waste.setOnMouseClicked(this::handleWasteCardClick);
        } else if(game.getStock().isEmpty()){
            waste.getChildren().clear();
        }
    }

}