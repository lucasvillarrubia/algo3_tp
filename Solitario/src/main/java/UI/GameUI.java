package UI;

import Elements.Column;
import Solitaire.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class GameUI {

    @FXML
    Pane tableau;
    @FXML
    Pane foundations;
    @FXML
    HBox stockPile;
    public Game game;
    public void setUpGame(Stage stage, Game game) throws IOException{}

    public void handleColumnClick(MouseEvent event){}
    public void handleFoundationClick(MouseEvent event) throws IOException {}
    public void handleStockClick(MouseEvent event){}

    public void setEventHandlers() {
        tableau.setOnMouseClicked(this::handleColumnClick);
        foundations.setOnMouseClicked(event -> {
            try {
                handleFoundationClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }
    public CardView getClickedCard (ColumnView cv) {
        for(int i = cv.getColumn().cardCount()-1; 0<=i ; i--){
            CardView card = cv.getCardView(i);
            if (card.estaClickeado()) {
                card.toggleCardClick();
                return card;
            }
        }
        return null;
    }

    public void updateTableauView(){}
    public void updateFoundations(){}
    public void updateColumnView(ColumnView columnView){
        int columnIndex = columnView.getIndex();
        Column column = game.getColumn(columnIndex);
        boolean isClicked = columnView.estaClickeado();
        ColumnView updatedColumnView = new ColumnView(column);
        updatedColumnView.setIndex(columnIndex);
        if (isClicked) {
            updatedColumnView.toggleColumnClick();
        }
        StackPane stackPane = (StackPane) tableau.getChildren().get(columnIndex);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(updatedColumnView);
    }
    public void updateStockButton(){}

    public void showWinScene(Stage stage) throws IOException {
        FXMLLoader winLoader = new FXMLLoader(getClass().getResource("/WinScene.fxml"));
        Parent winRoot = winLoader.load();
        Scene winScene = new Scene(winRoot);
        stage.setScene(winScene);
        stage.setTitle("Congratulations!");
        stage.show();
    }
}
