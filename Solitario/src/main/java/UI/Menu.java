package UI;

import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Menu {

    public static ComboBox<String> dropDownMenu(){
        ComboBox<String> gameTypeCB = new ComboBox<>();
        gameTypeCB.getItems().addAll("Klondike", "Spider");
        gameTypeCB.setValue("Klondike");
        return gameTypeCB;
    }


    private void openGame(String selectedGame){
        Stage gameStage = new Stage();
        gameStage.setTitle(selectedGame);
        if(selectedGame.equals("Klondike")){
            KlondikeUI.setUpGame();
        } else if(selectedGame.equals("Spider")){
            SpiderUI.setUpGame();
        }

        gameStage.show();
    }




}
