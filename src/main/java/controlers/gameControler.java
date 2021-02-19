package controlers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import logic.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class gameControler{
    private static StackPane[][] stackPaneGrid =new StackPane[20][20];
    private static ImageView[][] mapCharacters=new ImageView[20][20];
    private static ImageView[][] mapDetails=new ImageView[20][20];
    private static String[][] data=new String[20][20];
    private gameLogic logic=new gameLogic();
    private GUIReload reloader=new GUIReload(this);
    private Thread reloadThread=new Thread(reloader);
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
    @FXML
    private ImageView inventoryButton;
    @FXML
    private ImageView armorButton;
    @FXML
    private ImageView informationButton;
    @FXML
    private ImageView dropButton;
    @FXML
    private ImageView exitButton;



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
        reloadThread.setDaemon(true);
        reloadThread.start();
        Tooltip.install(inventoryButton, new Tooltip("Inventory"));
        Tooltip.install(armorButton, new Tooltip("Armor"));
        Tooltip.install(informationButton, new Tooltip("Information"));
        Tooltip.install(dropButton, new Tooltip("actualDrop"));
        Tooltip.install(exitButton, new Tooltip("Exit"));
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

    public void inventoryButtonAdd(){
        inventoryButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/inventoryButtonPressed.png").toUri().toString()));
    }
    public void inventoryButtonDelete(){
        inventoryButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/inventoryButton.png").toUri().toString()));
    }
    public void inventoryOpen(){
        JOptionPane.showMessageDialog(null, "kutassówka");
    }

    public void armorButtonAdd(){
        armorButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/armorButtonPressed.png").toUri().toString()));
    }
    public void armorButtonDelete(){
        armorButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/armorButton.png").toUri().toString()));
    }
    public void armorOpen(){
        JOptionPane.showMessageDialog(null, "inventory");
    }

    public void informationButtonAdd(){
        informationButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/InfoButtonPressed.png").toUri().toString()));
    }
    public void informationButtonDelete(){
        informationButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/InfoButton.png").toUri().toString()));
    }
    public void informationOpen(){
        JOptionPane.showMessageDialog(null, "information");
    }

    public void dropButtonAdd(){
        dropButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/dropButtonPressed.png").toUri().toString()));
    }
    public void dropButtonDelete(){
        dropButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/dropButton.png").toUri().toString()));
    }
    public void dropOpen(){
        JOptionPane.showMessageDialog(null, "Drop is here");
    }

    public void exitButtonAdd(){
        exitButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/exitButtonPressed.png").toUri().toString()));
    }
    public void exitButtonDelete(){
        exitButton.setImage(new Image(Paths.get("src", "main", "resources", "textures/exitButton.png").toUri().toString()));
    }
    public void exit(){

        ;
        if(JOptionPane.showConfirmDialog(null,"Are you sure to Exit Game?")>0){

        } else {
            System.exit(0);
        }
    }

    public Label getHealtLabel(){
        return healtLabel;
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

