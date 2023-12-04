
import GameType.KlondikeRules;
import Solitaire.SolitaireType;
import GameType.SpiderRules;
import Solitaire.Game;
import UI.KlondikeUI;
import UI.SpiderUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Main extends Application {

    private static final int H =754;
    private static final int W =680;
    private static final String TITLE = "Solitaire";
    private static final String SAVE_PATH = "savedGame.ser";

    private File file = new File(SAVE_PATH);

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
        root.setAlignment(Pos.CENTER);
        Text title = new Text("SOLITAIRE");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        title.setFill(Color.WHITE);
        Text text = new Text("Choose your game type");
        text.setFont(Font.font("Arial", 28));
        text.setFill(Color.WHITE);
        ComboBox<SolitaireType> dropdown = dropDownMenu();
        VBox vBox = new VBox(title,text, dropdown);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        root.getChildren().add(vBox);
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

    public ComboBox<SolitaireType> dropDownMenu(){
        ComboBox<SolitaireType> gameTypeCB = new ComboBox<>();
        gameTypeCB.setPrefWidth(100);
        gameTypeCB.getItems().addAll(SolitaireType.KLONDIKE, SolitaireType.SPIDER);
        return gameTypeCB;
    }

    public void openGame(Stage stage, SolitaireType selectedGame) throws IOException {
        Random random = new Random();
        if (SolitaireType.KLONDIKE ==selectedGame) {
            KlondikeUI klondikeUI = new KlondikeUI();
            Game game = new Game(new KlondikeRules(), random.nextInt());
            klondikeUI.setUpGame(stage, game);
        } else if (SolitaireType.SPIDER == selectedGame) {
            SpiderUI spiderUI = new SpiderUI();
            Game game = new Game(new SpiderRules(),random.nextInt());
            spiderUI.setUpGame(stage, game);
        }
    }

    private boolean savedGameExists() {
        return file.exists();
    }

    private void openSavedGame(Stage stage) throws IOException, ClassNotFoundException {
        Game savedGame = Game.deserialize(file);
        switch (savedGame.getGameRules()) {
            case KLONDIKE-> {
                KlondikeUI klondikeUI = new KlondikeUI();
                klondikeUI.setUpGame(stage, savedGame);
            }
            case SPIDER-> {
                SpiderUI spiderUI = new SpiderUI();
                spiderUI.setUpGame (stage, savedGame);
            }
        }

    }

}


