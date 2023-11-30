package UI;

import Elements.Foundation;
import Solitaire.Game;
import Solitaire.Movement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class SpiderUI extends GameUI{

    private static final int W = 880;
    private static final int H = 718;
    private static final  int AMOUNT_COLUMNS = 10;
    private static final  int AMOUNT_FOUNDATIONS = 8;

    @Override
    public void setUpGame(Stage stage, Game game) throws IOException {
        this.clickState = ClickState.NO_CLICK;
        this.LocalStage = stage;
        this.game = game;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpiderBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        stockPile.getChildren().add(stockView.showStock());
        updateFoundations(AMOUNT_FOUNDATIONS);
        updateTableauView(AMOUNT_COLUMNS);
        setEventHandlers(AMOUNT_COLUMNS, AMOUNT_FOUNDATIONS);

        Scene klondikeScene = new Scene(root,W, H);
        stage.setScene(klondikeScene);
        stage.setTitle("Spider Solitaire");
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
    public void acceptMoveToFoundation(FoundationView foundationView) throws IOException{
        if (clickedColumnView != null) {
            Foundation targetFoundation = foundationView.getFoundation();
            if (clickedCard == null) clickedCard = getClickedCard(clickedColumnView);
            if(clickedCard != null) {
                clickedCard.toggleCardClick();
                if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetFoundation, 12))) {
                    updateColumnView(clickedColumnView);
                    updateFoundations(AMOUNT_FOUNDATIONS);
                }
                clickedCard = null;
            }
            clickedColumnView = null;
        }
        clickedFoundationView = null;
        clickState = ClickState.NO_CLICK;
        updateFoundations(AMOUNT_FOUNDATIONS);
        checkWinningCondition();
    }

    @Override
    public void handleStockClick(MouseEvent event) {
        if (clickState == ClickState.CLICKED) clickState = ClickState.NO_CLICK;
        if(game.drawCardFromStock()){
            updateTableauView(AMOUNT_COLUMNS);
        }
        updateStockButton();
    }

    @Override
    public void updateStockButton(){
        StockView stockView = new StockView();
        if(game.getStock().isEmpty()){
            Button stockButton = stockView.showEmptyStock();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
        }
    }

}