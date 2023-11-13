import UI.KlondikeUI;
import UI.Menu;
import UI.SpiderUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int H =640;
    private static final int W =480;
    private static final String TITLE = "Solitaire";


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #008500");
        root.getChildren().add(Menu.dropDownMenu());
        var scene = new Scene(root, H, W);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.show();

    }






}
