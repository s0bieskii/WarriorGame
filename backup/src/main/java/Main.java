import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Paths.get("src", "main", "resources", "fxml", "mainScreen.fxml").toUri().toURL());
        Scene scene=new Scene(loader.load());
        stage.setTitle("Warrior");
        stage.getIcons().add(new Image(Paths.get("src", "main", "resources", "textures/icon.jpeg").toUri().toString()));
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}
