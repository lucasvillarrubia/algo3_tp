package UI;

import Base.Deck;
import Elements.Column;
import Elements.Foundation;
import Elements.Visitable;
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
    Text moves;

    protected Game game;
    protected Stage LocalStage;
    protected static final String FILE_PATH = "savedGame.ser";
    protected Clickable sourceDeck;
    protected Clickable goalDeck;
    protected int clickedCardIndex;

    private static final int ERROR = -1;


    protected void handleClick (MouseEvent event) throws IOException {
        Clickable deckView = (Clickable) ((StackPane)event.getSource()).getChildren().get(0);
        if (sourceDeck == null && deckView != null) {
            int index = deckView.getClickedCardIndex();
            if (index != ERROR) {
                sourceDeck = deckView;
                clickedCardIndex = deckView.getClickedCardIndex();
            }
        }
        else if (deckView != null) {
            if (deckView != sourceDeck) {
                goalDeck = deckView;
                if (!((Deck)sourceDeck.getDeck()).isEmpty()) sourceDeck.turnOffSelectedCard();
                if (!((Deck)goalDeck.getDeck()).isEmpty()) goalDeck.turnOffSelectedCard();
                if (game.makeAMove(new Movement(sourceDeck.getDeck(), goalDeck.getDeck(), clickedCardIndex))) {
                    updateDeckView(sourceDeck);
                    updateDeckView(goalDeck);
                    checkWinningCondition();
                }
                sourceDeck = null;
                goalDeck = null;
            }
            else {
                if (!((Deck)sourceDeck.getDeck()).isEmpty()) sourceDeck.turnOffSelectedCard();
                if (!((Deck)deckView.getDeck()).isEmpty()) deckView.turnOffSelectedCard();
                sourceDeck = null;
            }
        }
    }


    protected void updateDeckView (Clickable deckView) {
        int deckIndex = deckView.getIndex();
        Visitable deck = deckView.getDeck();
        if (deckView instanceof ColumnView) {
            ColumnView updatedView = new ColumnView((Column)deck, deckIndex);
            updatePane(tableau, updatedView);
        }
        else if (deckView instanceof FoundationView) {
            FoundationView updatedView = new FoundationView((Foundation)deck, deckIndex);
            updatePane(foundations, updatedView);
        }
    }

    protected void updatePane (Pane editedPane, Clickable updatedView) {
        int deckIndex = updatedView.getIndex();
        StackPane stackPane = (StackPane) editedPane.getChildren().get(deckIndex);
        stackPane.getChildren().clear();
        stackPane.getChildren().add((StackPane)updatedView);
    }

    protected void checkWinningCondition() throws IOException {
        if (game.gameStatus()) {
            showWinScene(LocalStage);
            File file = new File(FILE_PATH);
            if (file.exists()) {
                file.deleteOnExit();
            }
        }
    }

    protected void setEventHandlers(int columns, int foundation){
        for(int i = 0; i < columns; i++){
            tableau.getChildren().get(i).setOnMouseClicked(event -> {
                try {
                    handleClick(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        for(int j = 0; j < foundation; j++){
            foundations.getChildren().get(j).setOnMouseClicked(event -> {
                try {
                    handleClick(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        stockPile.getChildren().get(0).setOnMouseClicked(this::handleStockClick);
    }

    protected void updateTableauView(int amountColumns){
        for(int i = 0 ;i< amountColumns; i++){
            Column column =game.getColumn(i);
            ColumnView columnView = new ColumnView(column, i);
            StackPane stackPane =(StackPane) tableau.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(columnView);
        }
    }

    protected void updateFoundations(int amountFoundations){
        for (int i = 0; i < amountFoundations; i++) {
            Foundation foundation = game.getFoundation(i);
            FoundationView foundationView = new FoundationView(foundation, i);
            StackPane stackPane = (StackPane) foundations.getChildren().get(i);
            stackPane.getChildren().clear();
            stackPane.getChildren().add(foundationView);
        }
    }

    protected void showWinScene(Stage stage) throws IOException {
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

    protected abstract void handleStockClick(MouseEvent event);

    protected abstract void updateStockButton();

}
