package UI;


import Base.Card;
import Base.Suit;
import Elements.Column;
import Elements.Foundation;
import Solitaire.Game;
import GameType.KlondikeRules;
import Solitaire.Movement;
import Solitaire.Rules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class KlondikeUI extends GameUI{

    private static final int W =650;
    private static final int H =600;
    private Game game;
    private final CardView cardView = new CardView();
    private static final int AMOUNT_COLUMNS = 7;

    @FXML
    HBox stockPile;
    @FXML
    StackPane waste;
    @FXML
    Pane foundations;
    @FXML
    Pane tableau;
    ClickState clickState;
    private int stockIndex = 0;
    private boolean wasteIsClicked = true;
    private ColumnView clickedColumnView;
    private Foundation clickedFoundation;
    private Clickable sourceDeck;
    private Clickable destinationDeck;
    private CardView clickedCard;

    private Stage stage;


    @Override
    public void setUpGame(Stage stage, Game game) throws IOException {
        this.clickState = ClickState.NO_CLICK;
        this.stage = stage;
        this.game = game;
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
    public void setEventHandlers() {
        tableau.setOnMouseClicked(event -> {
            try {
                handleColumnClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        foundations.setOnMouseClicked(event -> {
            try {
                handleFoundationClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        stockPile.getChildren().get(0).setOnMouseClicked(event -> {
            try {
                handleStockClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        tableau.setOnMouseClicked(this::handleClick);
//        foundations.setOnMouseClicked(this::handleClick);
//        stock.setOnMouseClicked(this::handleClick);
    }

    @Override
    public void handleColumnClick(MouseEvent event) throws IOException {
        if (event.getSource() instanceof Pane source) {
            for (int i = 0; i < source.getChildren().size(); i++) {
                if (source.getChildren().get(i) instanceof StackPane) {
                    ColumnView columnView = (ColumnView) ((StackPane) source.getChildren().get(i)).getChildren().get(0);
                    if (columnView.isClicked()) {
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
                                    game.drawCardFromStock();
                                    updateWaste();
                                    stockIndex--;
                                    stockIndex--;
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
//                                    else System.out.println("no se movió la carta"); //REVISAR
                                } else {
                                    if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn, clickedCard.getIndex()))){
                                        updateColumnView(clickedColumnView);
                                        updateColumnView(columnView);
                                    }
//                                    else System.out.println("no se movió la secuencia"); //REVISAR
                                }
                                clickedColumnView = null;
                                clickedCard = null;
                            } else if (clickedFoundation != null) {
                                clickedColumnView = columnView;
//                                System.out.println("ENtro para movimiento de fopundation a column");
                                if (game.makeAMove(new Movement(clickedFoundation, clickedColumnView.getColumn()))) {
                                    updateColumnView(clickedColumnView);
                                    updateFoundations();
                                }
//                                else System.out.println("no se movió la carta de la foundation");
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
                    if (foundationView.isClicked()){
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
                                game.drawCardFromStock();
                                updateWaste();
                                stockIndex--;
                                stockIndex--;
                            }
//                            } else System.out.println("no se hizo el move de stock a foundation");
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

    @Override
    public void handleStockClick(MouseEvent event) throws IOException {
        if (stockIndex != game.getStock().cardCount()+1) {
            game.drawCardFromStock();
        }
        updateStockButton();
        updateWaste();
    }


    @Override
    public void updateTableauView(){
        for(int i = 0 ;i<AMOUNT_COLUMNS; i++){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            columnView.setNumber(i);
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

    @Override
    public void updateColumnView(ColumnView columnView){
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
            stockPile.getChildren().get(0).setOnMouseClicked(event -> {
                try {
                    handleStockClick(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else if (stockIndex == game.getStock().cardCount() + 1) {
            Button stockButton = stockView.showStock(game.getStock());
            stockPile.getChildren().clear();
            stockPile.getChildren().add(stockButton);
            stockPile.getChildren().get(0).setOnMouseClicked(event -> {
                try {
                    handleStockClick(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            stockIndex = 0;
        }
    }




    //se me ocutte crear un contador que vaya contando cada vez que se
    // realiza un movimiento desde el stock y ahi vas restandole al card count que
    //no parece ir como queremos


    private void handleWasteCardClick(MouseEvent event) {
        clickState = ClickState.CLICKED;
        wasteIsClicked = true;
    }

    private void updateWaste() {
        if (stockIndex == game.getStock().cardCount()) {
            stockIndex++;
        } else if (stockIndex == game.getStock().cardCount()) {
            waste.getChildren().clear();
        } else {
            Card card = game.getStock().getLast();
            ImageView wasteView = cardView.getImage(card);
            StackPane cardStackPane = new StackPane(wasteView);
            waste.getChildren().add(cardStackPane);
            waste.setOnMouseClicked(this::handleWasteCardClick);
            stockIndex++;
        }
    }



    private void searchForClicked(Pane pane) {
        //StackPane sp = (StackPane) pane.getChildren().get(0);
        for (Node child : pane.getChildren()) {
            Clickable clickedDeck = (Clickable) ((StackPane)child).getChildren().get(0);
            if (clickedDeck.estaClickeado()) {
                if (sourceDeck == null) this.sourceDeck = clickedDeck;
                else this.destinationDeck = clickedDeck;
            }
        }
    }

    private void searchForClicked(HBox stockCard) {
        Button bS= (Button) stockCard.getChildren().get(0);
        StockView sv = (StockView) bS.getGraphic();
        if (sourceDeck == null && wasteIsClicked) this.sourceDeck = sv;
        else this.destinationDeck = sv;
    }

    private void handleClick(MouseEvent event) {
        Pane clickedPane = (Pane) event.getSource();
        if (clickedPane.getChildren().get(0) instanceof HBox stockkkk) {
            searchForClicked(stockkkk);
        }
        else {
            searchForClicked(clickedPane);
        }
//        if (event.getSource() instanceof Pane pane)
        if (sourceDeck != null && destinationDeck != null) {
//            if (game.makeAMove(new Movement(sourceDeck.getDeck(), destinationDeck.getDeck()))) {
////                updateDeck(sourceDeck);
////                updateDeck(destinationDeck);
//                updateTableauView();
//                updateFoundations();
//            }
        }
    }

}