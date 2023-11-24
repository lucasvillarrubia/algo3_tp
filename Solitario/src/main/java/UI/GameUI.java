package UI;

import Solitaire.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class GameUI {


    public void setUpGame(Stage stage, Game game) throws IOException{}
    public void setEventHandlers(){}
    public void handleColumnClick(MouseEvent event) throws IOException {}
    public void handleFoundationClick(MouseEvent event) throws IOException {}
    public void handleStockClick(MouseEvent event) throws IOException {}

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
    public void updateColumnView(ColumnView columnView){}
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
