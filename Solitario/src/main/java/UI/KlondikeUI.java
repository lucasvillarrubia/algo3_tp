package UI;


import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Stock;
import GameType.KlondikeRules;
import Solitaire.Game;
import Solitaire.Movement;
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
import java.util.ArrayList;

public class KlondikeUI extends GameUI{

    private static final int W =650;
    private static final int H =600;
    private final CardView cardView = new CardView();
    private static final int AMOUNT_COLUMNS = 7;
    @FXML
    StackPane waste;
    ClickState clickState;
    private int stockIndex = 0;
    private boolean wasteIsClicked = true;
    private ColumnView clickedColumnView;
    private Foundation clickedFoundation;
    private Clickable sourceDeck;
    private Clickable destinationDeck;
    private CardView clickedCard;

    private Stage stage;

    public Game easyGame () {
        ArrayList<Foundation> fs = new ArrayList<>();
        ArrayList<Column> t = new ArrayList<>();
        Stock s = new Stock();
        for (Suit suit : Suit.values()) {
            Foundation f = new Foundation(suit);
            for (Value value : Value.values()) {
                Card c = new Card(suit, value);
                c.flip();
                f.addCards(c);
            }
            f.drawCard();
            fs.add(f);
        }

        for (int i = 0; i < 7; i++) {
            t.add(new Column());
        }
        Card k1 = new Card(Suit.SPADES, Value.KING);
        k1.flip();
        Card k2 = new Card(Suit.HEART, Value.KING);
        k2.flip();
        Card k3 = new Card(Suit.DIAMOND, Value.KING);
        k3.flip();
        Card k4 = new Card(Suit.CLUBS, Value.KING);
        k4.flip();

        t.get(0).addCards(k1);
        t.get(1).addCards(k2);
        t.get(2).addCards(k3);
        t.get(3).addCards(k4);
        return new Game(new KlondikeRules(),fs, t, s);
    }

    @Override
    public void setUpGame(Stage stage, Game newGame) throws IOException {
        this.clickState = ClickState.NO_CLICK;
        this.stage = stage;
        this.game = newGame;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KlondikeBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        Button stockButton = stockView.showStock(game.getStock());
        stockPile.getChildren().add(stockButton);
        updateFoundations();
        updateTableauView();
        setEventHandlers();
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
    public void handleColumnClick(MouseEvent event){
        if (event.getSource() instanceof Pane source) {
            for (int i = 0; i < source.getChildren().size(); i++) {
                if (source.getChildren().get(i) instanceof StackPane) {
                    ColumnView columnView = (ColumnView) ((StackPane) source.getChildren().get(i)).getChildren().get(0);
                    if (columnView.estaClickeado()) {
                        if (clickState == ClickState.NO_CLICK) {
                            columnView.toggleColumnClick();
                            clickedColumnView = columnView;
                            clickState = ClickState.CLICKED;
                            clickedCard = getClickedCard(clickedColumnView);
                        } else if (clickState == ClickState.CLICKED) {
                            Column targetColumn = columnView.getColumn();
                            columnView.toggleColumnClick();
                            if (wasteIsClicked) {
                                clickedColumnView = columnView;
                                if (game.makeAMove(new Movement(game.getStock(), clickedColumnView.getColumn()))) {
                                    updateColumnView(clickedColumnView);
                                    stockIndex--;
                                    game.drawCardFromStock();
                                    updateWaste();
                                    if (stockIndex == 0 ) {
                                        System.out.println("ESTO");
                                        waste.getChildren().clear();
                                    }
                                }
                                clickedColumnView = null;
                                clickedCard = null;
                                wasteIsClicked = false;
                            }
                            else if(clickedCard != null){
                                if (clickedCard.getIndex() == 0) {
                                    if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn))) {
                                        updateColumnView(clickedColumnView);
                                        updateColumnView(columnView);
                                    }
                                } else {
                                    if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn, clickedCard.getIndex()))){
                                        updateColumnView(clickedColumnView);
                                        updateColumnView(columnView);
                                    }
                                }
                                clickedColumnView = null;
                                clickedCard = null;
                            } else if (clickedFoundation != null) {
                                clickedColumnView = columnView;
                                if (game.makeAMove(new Movement(clickedFoundation, clickedColumnView.getColumn()))) {
                                    updateColumnView(clickedColumnView);
                                    updateFoundations();
                                }
                                clickedColumnView = null;
                                clickedCard = null;
                            }
                            clickState = ClickState.NO_CLICK;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void handleFoundationClick(MouseEvent event) throws IOException {
        if (event.getSource() instanceof Pane source) {
//            if (clickState == ClickState.CLICKED) {
            for (Node child : source.getChildren()) {
                if (child instanceof StackPane) {
                    FoundationView foundationView = (FoundationView) ((StackPane) child).getChildren().get(0);
                    if (foundationView.estaClickeado()){
                        foundationView.toggleFoundationClick();
                        clickedFoundation = foundationView.getFoundation();
                        clickState = ClickState.CLICKED;
                        if (clickedColumnView != null) {
                            Column column = clickedColumnView.getColumn();
                            if (game.makeAMove(new Movement(column, clickedFoundation))) {
                                updateColumnView(clickedColumnView);
                                updateFoundations();
                            }
                            clickedColumnView = null;
                            clickedFoundation = null;
                            clickState = ClickState.NO_CLICK;
                        } else if (wasteIsClicked) {
                            if (game.makeAMove(new Movement(game.getStock(), clickedFoundation))) {
                                updateFoundations();
                                stockIndex--;
                                game.drawCardFromStock();
                                updateWaste();
                                if (stockIndex == 0 ) {
                                    System.out.println("ESTO");
                                    waste.getChildren().clear();
                                }
                            }
                            clickedFoundation = null;
                            wasteIsClicked = false;
                            clickState = ClickState.NO_CLICK;
                        }

                        if (game.gameStatus()) {
                            showWinScene(stage);
                            return;
                        }
                    }
                }
            }
        }
    }

//    @Override
//    public void handleStockClick(MouseEvent event){
//        stockIndex++;
//        if (stockIndex != game.getStock().cardCount()) {
//            game.drawCardFromStock();
//        }
//        updateStockButton();
//        updateWaste();
//    }
//
//    private void handleEmptyStockClick(MouseEvent event) {
//        StockView stockView = new StockView();
//        Button stockButton = stockView.showStock(game.getStock());
//        waste.getChildren().clear();
//        stockPile.getChildren().clear();
//        stockPile.getChildren().add(stockButton);
//        stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
//    }

//    @Override
//    public void handleStockClick(MouseEvent event) {
//            StockView stockView = new StockView();
//            Button stockButton;
//            if (!game.drawCardFromStock()) {
//                stockButton = stockView.showEmptyStock();
//                stockPile.getChildren().clear();
//                stockPile.getChildren().add(stockButton);
//                stockPile.getChildren().get(0).setOnMouseClicked(this::handleEmptyStockClick);
//            }
//            else {
////                stockButton = stockView.showStock(game.getStock());
//                updateWaste();
//            }
//    }

    @Override
    public void updateTableauView(){
        for(int i = 0 ;i<AMOUNT_COLUMNS; i++){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            columnView.setIndex(i);
            StackPane stackPane =(StackPane) tableau.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(columnView);
        }
    }

    @Override
    public void updateFoundations(){
        int i = 0;
        for (Suit suit: Suit.values()) {
            Foundation foundation = game.getFoundation(suit);
            FoundationView foundationView = new FoundationView(foundation);
            foundationView.setIndex(i);
            StackPane stackPane = (StackPane) foundations.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(foundationView);
            i++;
        }
    }

//    @Override
//    public void updateStockButton(){
//        StockView stockView = new StockView();
//        if(game.getStock().isEmpty()){
//            System.out.println("Entro pq el stock esta vacio");
//            Button stockButton = stockView.showEmptyStock();
//            stockPile.getChildren().clear();
//            stockPile.getChildren().add(stockButton);
//        }
//        if (stockIndex == game.getStock().cardCount()-1) {
//            System.out.println("entro pq llego al card count - 1");
//            Button stockButton = stockView.showEmptyStock();
//            stockPile.getChildren().clear();
//            stockPile.getChildren().add(stockButton);
//            stockPile.getChildren().get(0).setOnMouseClicked(this::handleEmptyStockClick);
//        } else if (stockIndex == game.getStock().cardCount()) {
//            System.out.println("entro pq el cardCount +1");
//            Button stockButton = stockView.showStock(game.getStock());
//            stockPile.getChildren().clear();
//            stockPile.getChildren().add(stockButton);
//            stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
//            waste.getChildren().clear();
//            stockIndex = 0;
//        }
//    }




    @Override
    public void updateStockButton(){
        StockView stockView = new StockView();
        if(game.getStock().isEmpty()){
            Button stockButton = stockView.showEmptyStock();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
        }
        if (stockIndex == game.getStock().cardCount()) {
            System.out.println("llego al card count, se muestra boton de reinicio");
            Button stockButton = stockView.showEmptyStock();
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
            stockPile.getChildren().get(0).setOnMouseClicked(this::handleEmptyStockClick);
            stockIndex = 0;
        }
    }
    private void handleEmptyStockClick(MouseEvent event) {
        StockView stockView = new StockView();
        Button stockButton = stockView.showStock(game.getStock());
        waste.getChildren().clear();
        stockPile.getChildren().clear();
        stockPile.getChildren().add(stockButton);
        stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }
    //
    //
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

//    private void updateWaste() {
//        if (stockIndex == game.getStock().cardCount()) {
//            stockIndex++;
//            waste.getChildren().clear();
//        } else {
//            Card card = game.getStock().getLast();
//            ImageView wasteView = cardView.getImage(card);
//            StackPane cardStackPane = new StackPane(wasteView);
//            waste.getChildren().add(cardStackPane);
//            waste.setOnMouseClicked(this::handleWasteCardClick);
//        }
//    }

    private void updateWaste() {
//        if (stockIndex == 0 ) {
//            System.out.println("ESTO");
//            waste.getChildren().clear();
//        }
//        else {
            Card card = game.getStock().getLast();
            ImageView wasteView = cardView.getImage(card);
            StackPane cardStackPane = new StackPane(wasteView);
            waste.getChildren().add(cardStackPane);
            waste.setOnMouseClicked(this::handleWasteCardClick);
//        }
    }

//
//    private void searchForClicked(Pane pane) {
//        //StackPane sp = (StackPane) pane.getChildren().get(0);
//        for (Node child : pane.getChildren()) {
//            Clickable clickedDeck = (Clickable) ((StackPane)child).getChildren().get(0);
//            if (clickedDeck.estaClickeado()) {
//                if (sourceDeck == null) this.sourceDeck = clickedDeck;
//                else this.destinationDeck = clickedDeck;
//            }
//        }
//    }
//
//    private void searchForClicked(HBox stockCard) {
//        Button bS= (Button) stockCard.getChildren().get(0);
//        StockView sv = (StockView) bS.getGraphic();
//        if (sourceDeck == null && wasteIsClicked) this.sourceDeck = sv;
//        else this.destinationDeck = sv;
//    }
//
//    private void handleClick(MouseEvent event) {
//        Pane clickedPane = (Pane) event.getSource();
//        if (clickedPane.getChildren().get(0) instanceof HBox stockkkk) {
//            searchForClicked(stockkkk);
//        }
//        else {
//            searchForClicked(clickedPane);
//        }
////        if (event.getSource() instanceof Pane pane)
//        if (sourceDeck != null && destinationDeck != null) {
////            if (game.makeAMove(new Movement(sourceDeck.getDeck(), destinationDeck.getDeck()))) {
//////                updateDeck(sourceDeck);
//////                updateDeck(destinationDeck);
////                updateTableauView();
////                updateFoundations();
////            }
//        }
//    }

}