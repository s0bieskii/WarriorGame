package controlers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class gameControler{
    private StackPane[][] stackPaneGrid =new StackPane[20][20];
    @FXML
    private ImageView[][] mapDetails=new ImageView[20][20];
    @FXML
    private ImageView[][] mapCharacters=new ImageView[20][20];
    @FXML
    private Pane mainPane;
    @FXML
    private GridPane mapGrid;


    @FXML
    private void initialize() throws FileNotFoundException {
        createStackPaneGrid();
        createWordTexture();
        createWordDetails();
        createCharacters();
    }

    public void createStackPaneGrid(){
        for(int y=0; y<20;y++) {
            for (int x = 0; x < 20; x++) {
                StackPane stack = new StackPane();
                mapGrid.getChildren().add(stack);
                mapGrid.setConstraints(stack,x,y);
                stackPaneGrid[x][y]=stack;
            }
        }
    }

    public void createCharacters() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapDetails.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++) {
            for (int x = 0; x < 20; x++) {
                String icon=reader.next();
                ImageView image=new ImageView();
                image.setFitHeight(25.0);
                image.setFitWidth(25);
                if(icon.equals("@")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/wariorStay.gif").toUri().toString()));
                } else if(icon.equals("!")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/fly.gif").toUri().toString()));
                }
                stackPaneGrid[x][y].getChildren().add(image);
            }
        }
    }

    public void createWordTexture() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapTexture.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                ImageView image=new ImageView();
                image.setFitHeight(25.0);
                image.setFitWidth(25);
                if(icon.equals("G")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/grass.png").toUri().toString()));

                } else if(icon.equals("L")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/lava.png").toUri().toString()));
                }else if(icon.equals("w")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/water.png").toUri().toString()));
                } else if(icon.equals("S")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/stone.png").toUri().toString()));
                } else if(icon.equals("D")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/wood.png").toUri().toString()));
                }
                stackPaneGrid[x][y].getChildren().add(image);
            }
        }

    }

    public void createWordDetails() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapDetails.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                ImageView image=new ImageView();
                image.setFitHeight(25.0);
                image.setFitWidth(25);
                if(icon.equals("T")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/tree1.png").toUri().toString()));
                    mapDetails[x][y]=image;
                }
               else if(icon.equals("C")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/chest.png").toUri().toString()));
                    mapDetails[x][y]=image;
               }
               //else if(icon.equals("w")){
//                    image.setImage(new Image(Paths.get("src", "main", "resources", "water.png").toUri().toString()));
//                } else if(icon.equals("S")){
//                    image.setImage(new Image(Paths.get("src", "main", "resources", "stone.png").toUri().toString()));
//                } else if(icon.equals("D")){
//                    image.setImage(new Image(Paths.get("src", "main", "resources", "wood.png").toUri().toString()));
//                }
                stackPaneGrid[x][y].getChildren().add(image);
            }
        }
    }
}
