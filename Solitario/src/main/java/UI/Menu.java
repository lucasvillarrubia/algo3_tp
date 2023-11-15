package UI;

import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {


    public ComboBox<String> dropDownMenu(){
        ComboBox<String> gameTypeCB = new ComboBox<>();
        gameTypeCB.setPrefWidth(100);
        gameTypeCB.getItems().addAll("Klondike", "Spider");
        gameTypeCB.setValue("Choose...");
        return gameTypeCB;
    }

    public void openGame(Stage stage, String selectedGame) throws IOException {
        if ("Klondike".equals(selectedGame)) {
            KlondikeUI klondikeUI = new KlondikeUI();
            klondikeUI.setUpGame(stage);
        } else if ("Spider".equals(selectedGame)) {
            SpiderUI spiderUI = new SpiderUI();
            spiderUI.setUpGame(stage);
        }
    }




}
