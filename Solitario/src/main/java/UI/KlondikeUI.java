package UI;


import Base.Card;
import Base.Suit;
import Elements.Column;
import Elements.Foundation;
import Solitaire.Game;
import GameType.KlondikeRules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    ClickState clickState;
    private Column clickedColumn;
    private Foundation clickedFoundation;


    public void initialize(){
        KlondikeRules klondikeRules = new KlondikeRules();
        Random random = new Random();
        game = new Game(klondikeRules, random.nextInt());
        this.clickState = ClickState.NO_CLICK;
    }

    public void setUpGame(Stage stage) throws IOException {
        initialize();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KlondikeBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        Button stockButton = stockView.showStock();
        stock.getChildren().add(stockButton);
        updateFoundations();

        updateTableauView();
        setEventHandlers();

        Scene klondikeScene = new Scene(root,H, W);
        stage.setScene(klondikeScene);
        stage.setTitle("Klondike Solitaire");
        stage.show();
    }



    private void setEventHandlers() {
        tableau.setOnMouseClicked(this::handleColumnClick);
        foundations.setOnMouseClicked(this::handleFoundationsClick);
        stock.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }

    private void handleFoundationsClick(MouseEvent event) {
        if (event.getSource() instanceof Pane source) {
            // Check if there's a clickedColumn and it's not empty
            if (clickState == ClickState.FIRST_CLICK && !clickedColumn.isEmpty()) {
                // Iterate through the foundation panes
                for (Node child : source.getChildren()) {
                    if (child instanceof StackPane) {
                        FoundationView foundationView = (FoundationView) ((StackPane) child).getChildren().get(0);
                        clickedFoundation = foundationView.getFoundation();
                        // Perform the move to the foundation
                        if (game.moveCards(clickedColumn, clickedFoundation)) {
                            updateTableauView();
                            updateFoundations();
                        }

                        // Reset click state
                        clickState = ClickState.NO_CLICK;
                    }
                }
            }
        }

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

    private void updateFoundations(){
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


/*
* Waste -> corregir!!
*/

}