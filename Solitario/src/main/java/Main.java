
import UI.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private static final int H =640;
    private static final int W =480;
    private static final String TITLE = "Solitaire";


    @Override
    public void start(Stage stage) throws Exception {
        Menu menu = new Menu();
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #000177");

        ComboBox<String> dropdown = menu.dropDownMenu();
        root.getChildren().add(dropdown);

        dropdown.setOnAction(e -> {
            try {
                menu.openGame(stage, dropdown.getValue());
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


}


