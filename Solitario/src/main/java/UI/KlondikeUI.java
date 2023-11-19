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
    private int stockIndex = 0;
    private ColumnView clickedColumnView;
    private Foundation clickedFoundation;
    private Clickable sourceDeck;
    private Clickable destinationDeck;


    public void initialize(){
        KlondikeRules klondikeRules = new KlondikeRules();
        Random random = new Random();
        game = new Game(klondikeRules, 10);
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
//            if (clickState == ClickState.CLICKED) {
                for (Node child : source.getChildren()) {
                    if (child instanceof StackPane) {
                        FoundationView foundationView = (FoundationView) ((StackPane) child).getChildren().get(0);
                        if (foundationView.isClicked()){
                            foundationView.toggleFoundationClick();
                            clickedFoundation = foundationView.getFoundation();
                            if (clickedColumnView != null) {
                                Column column = clickedColumnView.getColumn();
                                if (game.moveCards(column, clickedFoundation)) {
                                    updateColumnView(clickedColumnView);
                                    System.out.println("FOUNDATION DROM THID COLUMN " + clickedColumnView.getNumber());
                                    updateFoundations();
                                    clickedColumnView = null;
                                    clickedFoundation = null;
                                }
                            }
                            clickState = ClickState.NO_CLICK;
                        }
                    }
                }
//            }
        }
    }


    private void handleColumnClick(MouseEvent event) {
        if (event.getSource() instanceof Pane source) {
            for (int i = 0; i < source.getChildren().size(); i++) {
                if (source.getChildren().get(i) instanceof StackPane) {
                    ColumnView columnView = (ColumnView) ((StackPane) source.getChildren().get(i)).getChildren().get(0);
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
                                //updateTableauView();
                                updateColumnView(clickedColumnView);
                                updateColumnView(columnView);
                                clickedColumnView = null;
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
        System.out.println(stockIndex);
        if (stockIndex != game.getStock().cardCount() + 1) {
            game.drawCardFromStock();
        }
        updateStockButton();
        updateWaste();
    }

    private void handleWasteCardClick(MouseEvent event) {
        clickState = ClickState.NO_CLICK;
        if(clickedColumnView == null && clickedFoundation == null) return;
        if (clickedColumnView != null) {
            Column targetColumn = clickedColumnView.getColumn();
            if (game.moveCards(game.getStock(), targetColumn)) {
                updateColumnView(clickedColumnView);
                game.drawCardFromStock();
                updateWaste();
                clickedColumnView = null;
                clickState = ClickState.NO_CLICK;
            }
        } else if (clickedFoundation !=null) {
            Foundation targetFoundation = clickedFoundation;
            System.out.println("Foundationnnnn"+clickedFoundation.getSuit());
            if (game.moveCards(game.getStock(), targetFoundation)) {
                updateFoundations();
                game.drawCardFromStock();
                updateWaste();
                clickedFoundation = null;
                clickState = ClickState.NO_CLICK;
            } else System.out.println("no se hizo el move");
        }
    }

    private void handleCardClickToFoundation(MouseEvent event) {
        clickState = ClickState.CLICKED;
        if(clickState == ClickState.CLICKED) { clickState = ClickState.NO_CLICK; }
        if(clickedColumnView == null) return;
        Foundation targetFoundation = clickedFoundation;
        if (game.moveCards(game.getStock(), targetFoundation)) {
            updateFoundations();
            game.drawCardFromStock();
            updateWaste();
            clickedColumnView = null;
            clickState = ClickState.NO_CLICK;
        }
    }

    private void updateWaste() {
        if (stockIndex == game.getStock().cardCount()) {
            stockIndex++;
        }
        else if (stockIndex == game.getStock().cardCount() + 1) {
            waste.getChildren().clear();
        }
        else {
            Card card = game.getStock().getLast();
            ImageView wasteView = cardView.getImage(card);
            StackPane cardStackPane = new StackPane(wasteView);
            waste.getChildren().add(cardStackPane);
            //cardStackPane.setOnMouseClicked(wasteCardEvent -> handleWasteCardClick(card));
            waste.setOnMouseClicked(this::handleWasteCardClick);
//            waste.setOnMouseClicked(this::handleCardClickToFoundation);
            //cardStackPane.setOnMouseClicked(wasteCardEvent -> handleCardClickToFoundation(wasteCardEvent));
            stockIndex++;
        }
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
        System.out.println("Termino");
    }

    private void updateStockButton(){
        StockView stockView = new StockView();
        if(game.getStock().isEmpty()){
            Button stockButton = stockView.showEmptyStock();
            stock.getChildren().clear();
            stock.getChildren().add(stockButton);
        }
        if (stockIndex == game.getStock().cardCount()) {
            Button stockButton = stockView.showEmptyStock();
            stock.getChildren().clear();
            stock.getChildren().add(stockButton);
            stock.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
        }
        else if (stockIndex == game.getStock().cardCount() + 1) {
            Button stockButton = stockView.showStock();
            stock.getChildren().clear();
            stock.getChildren().add(stockButton);
            stock.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
            stockIndex = 0;
        }
    }


    private void updateColumnView(ColumnView columnView){
        int columnIndex = columnView.getNumber();
        Column column = game.getColumn(columnIndex);
        boolean isClicked = columnView.isClicked();
        ColumnView updatedColumnView = new ColumnView(column);
        updatedColumnView.setNumber(columnIndex);
        if (isClicked) {
            updatedColumnView.toggleColumnClick();
        }
        StackPane stackPane = (StackPane) tableau.getChildren().get(columnIndex);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(updatedColumnView);
        System.out.println("Column updated: " + columnIndex);
    }

    private void searchForClicked(Pane pane) {
        for (Node child : pane.getChildren()) {
            Clickable clickedDeck = (Clickable) ((StackPane) child).getChildren().get(0);
            if (clickedDeck.estaClickeado()) {
                if (sourceDeck == null) this.sourceDeck = clickedDeck;
                else this.destinationDeck = clickedDeck;
            }
        }
    }

    private void searchForClicked(HBox stockCard) {
        Clickable clickedDeck = (Clickable) stockCard;
        if (sourceDeck == null) this.sourceDeck = clickedDeck;
        else this.destinationDeck = clickedDeck;
    }

//    private void searchForClicked(StackPane column) {
//        for (int i = 0; i < column.getChildren().size(); i++) {
////        for (Node child : column.getChildren()) {
//            Clickable clickedCard = (Clickable) column.getChildren().get(i);
//            if (clickedCard.estaClickeado()) {
//                if (i == 0) game.moveCards(sourceDeck.getDeck(), destinationDeck.getDeck());
//                else game.moveCards(sourceDeck.getDeck(), destinationDeck.getDeck(), i);
//            }
//        }
//    }

//    private void updateDeck(Clickable deck) {
//    }

    // Stock deberia estar como un atributo privado representado por su Pane
    // el HBox stock de ahora debería renombrarse quizás como stockPile
    private void handleClick(MouseEvent event) {
        Pane clickedPane = (Pane) event.getSource();
        if (clickedPane.getChildren().get(0) instanceof HBox stockPile) {
            searchForClicked(stockPile);
        }
        else {
            searchForClicked(clickedPane);
        }
//        if (event.getSource() instanceof Pane pane)
        if (sourceDeck != null && destinationDeck != null) {
            if (game.moveCards(sourceDeck.getDeck(), destinationDeck.getDeck())) {
//                updateDeck(sourceDeck);
//                updateDeck(destinationDeck);
                updateTableauView();
                updateFoundations();
            }
        }
    }

/*
* Waste -> corregir click
*/

}