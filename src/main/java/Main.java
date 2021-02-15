import controlers.gameControler;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.Store;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main extends Application {
    boolean timer=true;
    long lastTime=0;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Paths.get("src", "main", "resources", "fxml", "mainScreen.fxml").toUri().toURL());
        Scene scene=new Scene(loader.load());
        gameControler controler=loader.<gameControler>getController();
        stage.setTitle("Warrior");
        stage.getIcons().add(new Image(Paths.get("src", "main", "resources", "textures/icon.jpeg").toUri().toString()));
        stage.setMinHeight(680);
        stage.setMinWidth(680);
        stage.setScene(scene);
        stage.widthProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            stage.setHeight(t1.doubleValue());
        });
        stage.heightProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            stage.setWidth(t1.doubleValue());
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {

                if(System.currentTimeMillis()-lastTime>300) {
                    if (e.getCode().toString().equals("W")) {
                        try {
                            controler.getLogic().moveUp();
                            controler.move();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if (e.getCode().toString().equals("S")) {
                        try {
                            controler.getLogic().moveDown();
                            controler.move();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if (e.getCode().toString().equals("A")) {
                        try {
                            controler.getLogic().moveLeft();
                            controler.move();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if (e.getCode().toString().equals("D")) {
                        try {
                            controler.getLogic().moveRight();
                            controler.move();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if (e.getCode().toString().equals("E")) {
                        controler.getLogic().attack();
                        try {
                            controler.move();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    lastTime=System.currentTimeMillis();
                }

            }
        });
        stage.show();
    }
}
