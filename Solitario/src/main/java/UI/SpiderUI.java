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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class SpiderUI extends GameUI{

    private static final int W =780;
    private static final int H =620;

    ClickState clickState;
    private ColumnView clickedColumnView;
    private CardView clickedCard;
    private Foundation clickedFoundation;

    private Stage LocalStage;

    public Game gameStartsWithOneFullColumn () {
        ArrayList<Foundation> fs = new ArrayList<>();
        ArrayList<Column> t = new ArrayList<>();
        Stock s = new Stock();
        //Column c = new Column();
//        for (int j = Value.values().length - 1; j >= 0; j--) {
//            Card ca = new Card(Suit.SPADES, Value.values()[j]);
//            ca.flip();
//            c.addCards(ca);
//        }
        for (int i = 0; i < 8; i++) {
            fs.add(new Foundation(Suit.SPADES));
        }
        for (int i = 0; i < 8; i++) {
            Column c = new Column();
            for (int j = Value.values().length - 1; j >= 0; j--) {
                Card ca = new Card(Suit.SPADES, Value.values()[j]);
                ca.flip();
                c.addCards(ca);
            }
            t.add(c);
        }
        t.add(new Column());
        t.add(new Column());
        return new Game(new SpiderRules(),fs, t, s);
    }

    @Override
    public void setUpGame(Stage stage, Game game) throws IOException {
        this.clickState = ClickState.NO_CLICK;
        this.LocalStage = stage;
        this.game = game;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpiderBase.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        StockView stockView = new StockView();
        stockPile.getChildren().add(stockView.showStock(game.getStock()));
        updateFoundations();
        updateTableauView();
        setEventHandlers();

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

        stage.show();

    }


    @Override
    public void handleColumnClick(MouseEvent event) {
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
                            if(clickedCard != null){
//                                System.out.println("Index de carta clickeada: " + clickedCard.getIndex());
//                                if (clickedCard.getIndex() == -1){
//                                    System.out.println("ninguna carta figura clickeada"); //revisar esto no deberia haber prints
//                                } else
                                if (clickedCard.getIndex() == 0) {
                                    if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn))) {
                                        //updateTableauView();
                                        updateColumnView(clickedColumnView);
                                        updateColumnView(columnView);
                                        clickedColumnView = null;
                                        clickedCard = null;
                                    }
//                                    else System.out.println("no se movió la carta"); //REVISAR
                                } else {
                                    if (game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn, clickedCard.getIndex()))){
                                        updateColumnView(clickedColumnView);
                                        updateColumnView(columnView);
                                        clickedColumnView = null;
                                        clickedCard = null;
                                    } else {
                                        clickedCard.toggleCardClick();
//                                        System.out.println("no se movió la secuencia desde columnn"); //REVISAR
                                    }
                                }
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
            for (Node child : source.getChildren()) {
                if (child instanceof StackPane) {
                    FoundationView foundationView = (FoundationView) ((StackPane) child).getChildren().get(0);
                    if (foundationView.estaClickeado()){
                        foundationView.toggleFoundationClick();
                        clickedFoundation = foundationView.getFoundation();
                        if (clickedColumnView != null) {
                            Column column = clickedColumnView.getColumn();
                            if (clickedCard == null) clickedCard = getClickedCard(clickedColumnView);
                            if(clickedCard != null) {
//                                System.out.println("Index de carta clickeada: " + clickedCard.getIndex());
                                if (clickedCard.getIndex() == 12) {
                                    if (game.makeAMove(new Movement(column, clickedFoundation, 12))) {
                                        updateColumnView(clickedColumnView);
                                        updateFoundations();
                                        clickedColumnView = null;
                                        clickedFoundation = null;
                                        clickedCard = null;
                                    } else{
                                        clickedCard.toggleCardClick();
//                                        System.out.println("no se movió la secuencia"); //REVISAR
                                    }
                                }
                            }
                        }
                        clickState = ClickState.NO_CLICK;
//                        System.out.println("ganado: " + game.isGameWon()+ "terminado:" + game.isGameOver() );
                        if (game.gameStatus()) {
                            showWinScene(LocalStage);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void handleStockClick(MouseEvent event) {
        if(game.drawCardFromStock()){
            updateTableauView();
        }
        updateStockButton();
    }


    @Override
    public void updateTableauView(){
        for(int i = 0 ;i<10; i++ ){
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
        for (int i = 0; i < 8; i++) {
            Foundation foundation = game.getFoundation(i);
            FoundationView foundationView = new FoundationView(foundation);
            foundationView.setIndex(i);
            StackPane stackPane = (StackPane) foundations.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(foundationView);
        }
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