
import GameType.KlondikeRules;
import GameType.SpiderRules;
import Solitaire.Game;
import UI.KlondikeUI;
//import UI.Menu;
import UI.SpiderUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Main extends Application {

    private static final int H =640;
    private static final int W =480;
    private static final String TITLE = "Solitaire";
    private static final String SAVE_PATH = "savedGame.txt";

    private File file = new File(SAVE_PATH);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        if (savedGameExists()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Solitaire");
            alert.setHeaderText("Saved game found!");
            alert.setContentText("Do you want to start a new game or continue the saved one?");
            ButtonType newGameButton = new ButtonType("New Game");
            ButtonType continueButton = new ButtonType("Continue");
            alert.getButtonTypes().setAll(newGameButton, continueButton);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == newGameButton) {
                    showMenu(root, stage);
                } else if (buttonType == continueButton) {
                    try {
                        openSavedGame(stage);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
                showMenu(root, stage);
        }

    }


    public void showMenu(StackPane root, Stage stage){
        root.setStyle("-fx-background-color: #000177");
        ComboBox<String> dropdown = dropDownMenu();
        root.getChildren().add(dropdown);
        dropdown.setOnAction(e -> {
            try {
                openGame(stage, dropdown.getValue());

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Scene scene = new Scene(root, H, W);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image("/images/extras/app-logo.png"));
        stage.setResizable(false);
        stage.show();
    }

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
            Random random = new Random();
            Game game = new Game(new KlondikeRules(),10);
            klondikeUI.setUpGame(stage, game);
        } else if ("Spider".equals(selectedGame)) {
            SpiderUI spiderUI = new SpiderUI();
            Random random = new Random();
            Game game = new Game(new SpiderRules(),10);
            spiderUI.setUpGame(stage, game);
        }
    }

    private boolean savedGameExists() {
        return file.exists();
    }

    private void openSavedGame(Stage stage) throws IOException, ClassNotFoundException {
        Game savedGame = Game.deserialize(file);
        switch (savedGame.getGameRules()) {
            case "Klondike" -> {
                KlondikeUI klondikeUI = new KlondikeUI();
                klondikeUI.setUpGame(stage, savedGame);
            }
            case "Spider" -> {
                SpiderUI spiderUI = new SpiderUI();
                spiderUI.setUpGame (stage, savedGame);
            }
        }

    }

}


