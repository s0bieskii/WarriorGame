package controlers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import logic.*;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class gameControler{
    private static StackPane[][] stackPaneGrid =new StackPane[20][20];
    private static ImageView[][] mapCharacters=new ImageView[20][20];
    private static ImageView[][] mapDetails=new ImageView[20][20];
    private static String[][] data=new String[20][20];
    private gameLogic logic=new gameLogic();
    private GUIReload reloader=new GUIReload(this);
    Thread reloadThread=new Thread(reloader);

    @FXML
    private AnchorPane main;
    @FXML
    private GridPane mapGrid;
    @FXML
    private AnchorPane skala;
    @FXML
    private ImageView characterImage;
    @FXML
    private ProgressBar healt;
    @FXML
    private ProgressBar mana;
    @FXML
    private ScrollPane inventoryPane;
    @FXML
    private AnchorPane InventoryBG;
    @FXML
    private Label healtLabel;


    public gameControler() throws FileNotFoundException {
    }

    @FXML
    private void initialize() throws FileNotFoundException {
        createStackPaneGrid();
        createWordTexture();
        createWordDetails();
        createCharacters();
        characterImage.setImage(new Image(Paths.get("src", "main", "resources", "textures/icon22.png").toUri().toString()));
        inventoryPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        inventoryPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        reloadThread.start();
    }

    public void move() throws FileNotFoundException {
        String[][] characters=logic.getCharacters();
        for(int y=0; y<20;y++) {
            for (int x = 0; x < 20; x++) {
                String icon=characters[x][y];
                String fileName="";
                if(icon.equals("@")){
                    fileName="wariorStay";
                } else if(icon.equals("!")){
                    fileName="fly";
                } else if(icon.equals("*")){
                    fileName="skeleton";
                }
                mapCharacters[logic.getLastX()][logic.getLastY()].setImage(null);
                mapCharacters[x][y].setImage(new Image(Paths.get("src", "main", "resources", "textures/"+fileName+".gif").toUri().toString()));
                data[x][y]="@";
            }
        }
        setHealtLabel("s");
    }

    public void setHealt(){
        double percent=(((double)logic.getHero().getCurrentHealt()*100)/(double)logic.getHero().getMaxHealt())/100;
            healt.setProgress(percent);
    }

    public void setHealtLabel(String string){
        double per=healt.getProgress()*100;
        String str = String.format("%.0f", per);
        healtLabel.setText(str+"%");
    }

    public void createCharacters() throws FileNotFoundException {
        String[][] characters=logic.getCharacters();
        for(int y=0; y<20;y++) {
            for (int x = 0; x < 20; x++) {
                String icon=characters[x][y];
                ImageView image=new ImageView();
                mapCharacters[x][y]=image;
                image.fitWidthProperty().bind(skala.widthProperty());
                image.fitHeightProperty().bind(skala.heightProperty());
                if(icon.equals("@")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/wariorStay.gif").toUri().toString()));
                    data[x][y]="@";
                } else if(icon.equals("!")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/fly.gif").toUri().toString()));
                    data[x][y]="!";
                } else if(icon.equals("*")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/skeleton.gif").toUri().toString()));
                    data[x][y]="*";
                }else if(icon.equals("0")){
                    image.setImage(null);
                    data[x][y]="0";
                }
                stackPaneGrid[x][y].getChildren().add(image);
            }
        }
    }
    public void createWordTexture() throws FileNotFoundException {
        String[][] textures=logic.getTextures();
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=textures[x][y];
                ImageView image=new ImageView();
                Image toAdd;
                image.fitWidthProperty().bind(skala.widthProperty());
                image.fitHeightProperty().bind(skala.heightProperty());
                if(icon.equals("G")){
                    toAdd=new Image(Paths.get("src", "main", "resources", "textures/grass.png").toUri().toString());
                    image.setImage(toAdd);
                } else if(icon.equals("L")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/lava.png").toUri().toString()));
                    data[x][y]="L";
                }else if(icon.equals("W")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/water.png").toUri().toString()));
                    data[x][y]="W";
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
        String[][] detailss=logic.getDetails();
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=detailss[x][y];
                ImageView image=new ImageView();
                image.fitWidthProperty().bind(skala.widthProperty());
                image.fitHeightProperty().bind(skala.heightProperty());
                if(icon.equals("T")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/tree1.png").toUri().toString()));
                    mapDetails[x][y]=image;
                    data[x][y]="T";
                }
                else if(icon.equals("C")){
                    image.setImage(new Image(Paths.get("src", "main", "resources", "textures/chest.png").toUri().toString()));
                    mapDetails[x][y]=image;
                    data[x][y]="C";
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

    public gameLogic getLogic() {
        return logic;
    }

     public void createStackPaneGrid(){
        for(int y=0; y<20;y++) {
            for (int x = 0; x < 20; x++) {
                StackPane stack = new StackPane();
                AnchorPane anchor=new AnchorPane(stack);
                mapGrid.getChildren().add(stack);
                mapGrid.setConstraints(stack,x,y);
                stackPaneGrid[x][y]=stack;
            }
        }
    }
}

