package UI;

import Elements.Column;
import Elements.Foundation;
import Solitaire.Game;
import Solitaire.Movement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public abstract class GameUI {

    @FXML
    Pane tableau;
    @FXML
    Pane foundations;
    @FXML
    HBox stockPile;
    @FXML
    Text moves ;

    public Game game;
    public ClickState clickState;
    public ColumnView clickedColumnView;
    public Foundation clickedFoundation;
    public CardView clickedCard;
    public Stage LocalStage;

    public void handleColumnClick(MouseEvent event){
        ColumnView columnView = (ColumnView) ((StackPane)event.getSource()).getChildren().get(0);
        if (clickState == ClickState.NO_CLICK) {
            columnView.toggleColumnClick();
            clickedColumnView = columnView;
            clickState = ClickState.CLICKED;
            clickedCard = getClickedCard(clickedColumnView);
        } else acceptMoveToColumn(columnView);
    }

    public void handleFoundationClick(MouseEvent event) {
        FoundationView foundationView = (FoundationView) ((StackPane) event.getSource()).getChildren().get(0);
        clickedFoundation = foundationView.getFoundation();
        clickState = ClickState.CLICKED;
        try {
            acceptMoveToFoundation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptMoveToColumn(ColumnView columnView){
        if (clickState != ClickState.CLICKED) return;
        Column targetColumn = columnView.getColumn();
        clickedCard.toggleCardClick();
        clickState = ClickState.NO_CLICK;
        if (clickedCard.getIndex() == 0) game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn));
        else game.makeAMove(new Movement(clickedColumnView.getColumn(), targetColumn, clickedCard.getIndex()));
        updateColumnView(clickedColumnView);
        updateColumnView(columnView);
        clickedColumnView = null;
        clickedCard = null;
    }

    public void checkWinningCondition(String file_path) throws IOException {
        if (game.gameStatus()) {
            showWinScene(LocalStage);
            File file = new File(file_path);
            if (file.exists()) {
                file.deleteOnExit();
            }
        }
    }

    public void setEventHandlers(int columns, int foundation){
        for(int i = 0; i < columns; i++){
            tableau.getChildren().get(i).setOnMouseClicked(this::handleColumnClick);
        }
        for(int j = 0; j < foundation; j++){
            foundations.getChildren().get(j).setOnMouseClicked(this::handleFoundationClick);
        }
        stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }

    public CardView getClickedCard (ColumnView cv) {
        for(int i = cv.getColumn().cardCount()-1; 0<=i ; i--){
            CardView card = cv.getCardView(i);
            if (card.isClicked()) {
                card.toggleCardClick();
                return card;
            }
        }
        return null;
    }

    public void updateTableauView(int amount_columns){
        for(int i = 0 ;i< amount_columns; i++){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column);
            columnView.setIndex(i);
            StackPane stackPane =(StackPane) tableau.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(columnView);
        }
    }

    public void updateFoundations(int amount_foundations){
        for (int i = 0; i < amount_foundations; i++) {
            Foundation foundation = game.getFoundation(i);
            FoundationView foundationView = new FoundationView(foundation);
            foundationView.setIndex(i);
            StackPane stackPane = (StackPane) foundations.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(foundationView);
        }
    }

    public void updateColumnView(ColumnView columnView){
        int columnIndex = columnView.getIndex();
        Column column = game.getColumn(columnIndex);
        boolean isClicked = columnView.isClicked();
        ColumnView updatedColumnView = new ColumnView(column);
        updatedColumnView.setIndex(columnIndex);
        if (isClicked) {
            updatedColumnView.toggleColumnClick();
        }
        StackPane stackPane = (StackPane) tableau.getChildren().get(columnIndex);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(updatedColumnView);
    }

    public void showWinScene(Stage stage) throws IOException {
        FXMLLoader winLoader = new FXMLLoader(getClass().getResource("/WinScene.fxml"));
        winLoader.setController(this);
        Parent winRoot = winLoader.load();
        moves.setText("Cantidad de Movimientos: " + game.getCantMovements());
        Scene winScene = new Scene(winRoot);
        stage.setScene(winScene);
        stage.setTitle("Congratulations!");
        stage.show();
    }

    public abstract void setUpGame(Stage stage, Game game) throws IOException;

    public abstract void handleStockClick(MouseEvent event);

    public abstract void acceptMoveToFoundation() throws IOException;

    public abstract void updateStockButton();

}
