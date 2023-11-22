package UI;

import Base.Card;
import Base.Suit;
import Base.Value;
import Elements.Column;
import Elements.Foundation;
import Elements.Stock;
import Solitaire.Game;
import GameType.SpiderRules;
import Solitaire.Movement;
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
import java.util.ArrayList;
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
    Pane foundations;
    ClickState clickState;
    private ColumnView clickedColumnView;
    private CardView clickedCard;
    private Foundation clickedFoundation;


    public Game gameStartsWithOneFullColumn () {
        ArrayList<Foundation> fs = new ArrayList<>();
        ArrayList<Column> t = new ArrayList<>();
        Stock s = new Stock();
        Column c = new Column();
        for (int i = Value.values().length - 1; i >= 0; i--) {
            Card ca = new Card(Suit.SPADES, Value.values()[i]);
            ca.flip();
            c.addCards(ca);
        }
        for (int i = 0; i < 8; i++) {
            fs.add(new Foundation(Suit.SPADES));
        }
        for (int i = 0; i < 9; i++) {
            t.add(new Column());
        }
        t.add(c);
        return new Game(new SpiderRules(),fs, t, s);
    }

    public void initialize(){
        SpiderRules spiderRules = new SpiderRules();
        Random random = new Random();
        game = new Game(spiderRules, 10);
        //game = gameStartsWithOneFullColumn();
        clickState = ClickState.NO_CLICK;
    }

    public void setUpGame(Stage stage) throws IOException {
        initialize();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpiderBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        stock.getChildren().add(stockView.showStock(game.getStock()));

        updateFoundations();
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

    private void handleColumnClick2(MouseEvent event) {
        if (event.getSource() instanceof Pane source) {
            for (Node child : source.getChildren()) {
                if (child instanceof StackPane) {
                    ColumnView columnView = (ColumnView) ((StackPane) child).getChildren().get(0);
                    if (columnView.isClicked()) {
                        if (clickState == ClickState.NO_CLICK) {
                            columnView.toggleColumnClick();
                            clickedColumnView = columnView;
                            clickState = ClickState.CLICKED;
                        } else if (clickState == ClickState.CLICKED) {
                            Column targetColumn = columnView.getColumn();
                            columnView.toggleColumnClick();
                            if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn))){
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

    private void handleColumnClick(MouseEvent event) {
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
                            if(clickedCard != null){
                                System.out.println("Index de carta clickeada: " + clickedCard.getIndex());
                                if (clickedCard.getIndex() == -1){
                                    System.out.println("ninguna carta figura clickeada"); //revisar esto no deberia haber prints
                                } else if (clickedCard.getIndex() == 0) {
                                    if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn))) {
                                        //updateTableauView();
                                        updateColumnView(clickedColumnView);
                                        updateColumnView(columnView);
                                        clickedColumnView = null;
                                        clickedCard = null;
                                    } else System.out.println("no se movió la carta"); //REVISAR
                                } else {
                                    if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn, clickedCard.getIndex()))){
                                        updateColumnView(clickedColumnView);
                                        updateColumnView(columnView);
                                        clickedColumnView = null;
                                        clickedCard = null;
                                    } else {
                                        clickedCard.toggleCardClick();
                                        System.out.println("no se movió la secuencia desde columnn"); //REVISAR
                                    }
                                }
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
        if (event.getSource() instanceof Pane source) {
            for (Node child : source.getChildren()) {
                if (child instanceof StackPane) {
                    FoundationView foundationView = (FoundationView) ((StackPane) child).getChildren().get(0);
                    if (foundationView.isClicked()){
                        foundationView.toggleFoundationClick();
                        clickedFoundation = foundationView.getFoundation();
                        if (clickedColumnView != null) {
                            Column column = clickedColumnView.getColumn();
                            if (clickedCard == null) clickedCard = getClickedCard(clickedColumnView);
                            if(clickedCard != null) {
                                System.out.println("Index de carta clickeada: " + clickedCard.getIndex());
                                if (clickedCard.getIndex() == 12) {
                                    if (game.makeAMove(new Movement(column, clickedFoundation, 12))) {
                                        updateColumnView(clickedColumnView);
                                        updateFoundations();
                                        clickedColumnView = null;
                                        clickedFoundation = null;
                                        clickedCard = null;
                                    } else{
                                        clickedCard.toggleCardClick();
                                        System.out.println("no se movió la secuencia"); //REVISAR
                                    }
                                }
                            }
//                            System.out.println(" la clickedCard es nula ;/");
                        }
                        clickState = ClickState.NO_CLICK;
                    }
                }
            }
        }
    }

    private CardView getClickedCard (ColumnView cv) {
        for(int i = cv.getColumn().cardCount()-1; 0<=i ; i--){
            CardView card = cv.getCardView(i);
            if (card.estaClickeado()) {
                card.toggleCardClick();
                System.out.println("Salió del for, la carta clickeada fue guardada");
                return card;
            }
        }
        System.out.println("Termino el For ninguna carta fue clickeada");
        return null;
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

    private void updateFoundations(){
        for (int i = 0; i < 8; i++) {
            Foundation foundation = game.getFoundation(i);
            FoundationView foundationView = new FoundationView(foundation);
            foundationView.setIndex(i);
            StackPane stackPane = (StackPane) foundations.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(foundationView);
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
//        System.out.println("Column updated: " + columnIndex);
    }


}