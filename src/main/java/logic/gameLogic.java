package logic;

import controlers.gameControler;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class gameLogic {
    private String[][] textures=new String[20][20];
    private String[][] details=new String[20][20];
    private String[][] characters=new String[20][20];
    private Coordinates currentLocation=new Coordinates(0, 0);
    private ArrayList<Monster> monstersList=new ArrayList<>();
    private ArrayList<Monster> monstersListOposit=new ArrayList<>();
    private boolean monsterIsOposit=false;
    private int counter =0;
    private int lastX=0;
    private int lastY=0;
    private Warrior hero;
    private ArrayList<Coordinates> monstersToKillCordinate=new ArrayList<>();

    public gameLogic() throws FileNotFoundException {
        mapBuilder();
        hero=new Warrior("Killer", "Warrior", new Weapon("Old Sword", 1,1, 25), new Armor());
    }

    public void moveLeft() throws FileNotFoundException {
        if(canMoveTo(currentLocation.getX()-1, currentLocation.getY())){
            lastX=currentLocation.getX();
            lastY=currentLocation.getY();
            characters[currentLocation.getX()][currentLocation.getY()]="0";
            currentLocation.setX(currentLocation.getX()-1);
            currentLocation.setY(currentLocation.getY() );
            characters[currentLocation.getX()][currentLocation.getY()]="@";
        }
        opositeMonster();
    }
    public void moveRight() throws FileNotFoundException {
        if(canMoveTo(currentLocation.getX()+1, currentLocation.getY())){
            lastX=currentLocation.getX();
            lastY=currentLocation.getY();
            characters[currentLocation.getX()][currentLocation.getY()]="0";
            currentLocation.setX(currentLocation.getX()+1);
            currentLocation.setY(currentLocation.getY() );
            characters[currentLocation.getX()][currentLocation.getY()]="@";
        }
        opositeMonster();
    }
    public void moveUp() throws FileNotFoundException {
        if(canMoveTo(currentLocation.getX(), currentLocation.getY()-1)){
            lastX=currentLocation.getX();
            lastY=currentLocation.getY();
            characters[currentLocation.getX()][currentLocation.getY()]="0";
            currentLocation.setX(currentLocation.getX());
            currentLocation.setY(currentLocation.getY()-1 );
            characters[currentLocation.getX()][currentLocation.getY()]="@";
        }
        opositeMonster();
    }
    public void moveDown() throws FileNotFoundException {
        if (canMoveTo(currentLocation.getX(), currentLocation.getY() + 1)) {
            lastX=currentLocation.getX();
            lastY=currentLocation.getY();
            characters[currentLocation.getX()][currentLocation.getY()] = "0";
            currentLocation.setX(currentLocation.getX());
            currentLocation.setY(currentLocation.getY() + 1);
            characters[currentLocation.getX()][currentLocation.getY()] = "@";
        }
        opositeMonster();
    }
    private boolean canMoveTo(int x, int y){
        if(x<20 && x>=0 && y<20 && y>=00){
            if(details[x][y].equals("0") && textures[x][y].equals("G") || textures[x][y].equals("S")
                    || textures[x][y].equals("D")){
                if(characters[x][y].equals("0")){
                    return true;
                }
            }
        }
        return false;
    }

    private void mapBuilder(){
        try {
            texturesLoad();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            detailsLoad();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            charactersLoad();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void opositeMonster(){
        monstersListOposit.clear();
        if(currentLocation.getX()==0 && currentLocation.getY()==0){  //left top corner
            monsterIsOposit=!characters[currentLocation.getX()][currentLocation.getY()+1].equals("0")||
                    !characters[currentLocation.getX()+1][currentLocation.getY()].equals("0");
        } else if(currentLocation.getX()==19 && currentLocation.getY()==19) {  //right down corner
            monsterIsOposit = !characters[currentLocation.getX()][currentLocation.getY() - 1].equals("0") ||
                    !characters[currentLocation.getX() - 1][currentLocation.getY()].equals("0");
        } else if(currentLocation.getX()==19 && currentLocation.getY()==0){  //right top corner
            monsterIsOposit=!characters[currentLocation.getX()-1][currentLocation.getY()].equals("0") ||
                    !characters[currentLocation.getX()][currentLocation.getY()+1].equals("0");
        } else if(currentLocation.getX()==0 && currentLocation.getY()==19){  //left down corner
            monsterIsOposit=!characters[currentLocation.getX()][currentLocation.getY()-1].equals("0") ||
                    !characters[currentLocation.getX()+1][currentLocation.getY()].equals("0");
        } else{
            if(currentLocation.getY()==19){ //down border
                monsterIsOposit= !characters[currentLocation.getX()][currentLocation.getY()-1].equals("0") ||
                        !characters[currentLocation.getX()-1][currentLocation.getY()].equals("0")||
                        !characters[currentLocation.getX()+1][currentLocation.getY()].equals("0");

                if(!characters[currentLocation.getX()][currentLocation.getY()-1].equals("0")) monstersListOposit.add(monsterFromCoordinate(currentLocation.getX(), currentLocation.getY()-1));
                if(!characters[currentLocation.getX()][currentLocation.getY()-1].equals("0")) monstersListOposit.add(monsterFromCoordinate(currentLocation.getX()-1, currentLocation.getY()));
                if(!characters[currentLocation.getX()][currentLocation.getY()-1].equals("0")) monstersListOposit.add(monsterFromCoordinate(currentLocation.getX()+1, currentLocation.getY()));
            }
            else if(currentLocation.getX()==0){ //left border
                monsterIsOposit= !characters[currentLocation.getX()][currentLocation.getY()+1].equals("0")||
                        !characters[currentLocation.getX()][currentLocation.getY()-1].equals("0")||
                        !characters[currentLocation.getX()+1][currentLocation.getY()].equals("0");
                System.out.println(monsterIsOposit);
            } else if(currentLocation.getX()==19){
                monsterIsOposit= !characters[currentLocation.getX()][currentLocation.getY()-1].equals("0") ||
                        !characters[currentLocation.getX()][currentLocation.getY()+1].equals("0")||
                        !characters[currentLocation.getX()-1][currentLocation.getY()].equals("0");
                System.out.println(monsterIsOposit);
            }else if(currentLocation.getY()==0){
                monsterIsOposit=!characters[currentLocation.getX()-1][currentLocation.getY()].equals("0") ||
                        !characters[currentLocation.getX()][currentLocation.getY()+1].equals("0")||
                        !characters[currentLocation.getX()+1][currentLocation.getY()].equals("0");
                System.out.println(monsterIsOposit);
            } else {
                monsterIsOposit=!characters[currentLocation.getX()-1][currentLocation.getY()].equals("0") ||
                        !characters[currentLocation.getX()][currentLocation.getY()+1].equals("0")||
                        !characters[currentLocation.getX()+1][currentLocation.getY()].equals("0") ||
                        !characters[currentLocation.getX()][currentLocation.getY()-1].equals("0");
                if(!characters[currentLocation.getX()][currentLocation.getY()-1].equals("0")) monstersListOposit.add(monsterFromCoordinate(currentLocation.getX(), currentLocation.getY()-1));
                if(!characters[currentLocation.getX()][currentLocation.getY()+1].equals("0")) monstersListOposit.add(monsterFromCoordinate(currentLocation.getX(), currentLocation.getY()+1));
                if(!characters[currentLocation.getX()-1][currentLocation.getY()].equals("0")) monstersListOposit.add(monsterFromCoordinate(currentLocation.getX()-1, currentLocation.getY()));
                if(!characters[currentLocation.getX()+1][currentLocation.getY()].equals("0")) monstersListOposit.add(monsterFromCoordinate(currentLocation.getX()+1, currentLocation.getY()));
            }
        }
        System.out.println(monsterIsOposit);
    }
    public void attack(){
        if(!monstersListOposit.isEmpty()){
            monstersListOposit.get(0).takeDamage(hero.getWeapon().getDamagePoints());
            monsterKill();
        }
        System.out.println(monstersListOposit);
    }
    public void monsterKill(){
        ArrayList<Monster> monstersToKill=new ArrayList<>();
        Iterator iterator=monstersList.iterator();
        while (iterator.hasNext()){
            Monster temp=(Monster)iterator.next();
            if(temp.getHP()<=0){
                characters[temp.getX()][temp.getY()]="0";
                monstersToKillCordinate.add(new Coordinates(temp.getX(), temp.getY()));
                monstersToKill.add(temp);
            }
        }
        monstersListOposit.removeAll(monstersToKill);
        monstersList.removeAll(monstersToKill);

    }




    private void texturesLoad() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapTexture.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                switch (icon){
                    case "G": textures[x][y]="G";
                        break;
                    case "L": textures[x][y]="L";
                        break;
                    case "W": textures[x][y]="W";
                        break;
                    case "S": textures[x][y]="S";
                        break;
                    case "D": textures[x][y]="D";
                        break;
                }
            }
        }
    }
    private void detailsLoad() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapDetails.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                switch (icon){
                    case "T": details[x][y]="T";
                        break;
                    case "C": details[x][y]="C";
                        break;
                    default: details[x][y]="0";
                }
            }
        }
    }
    private void charactersLoad() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapCharacters.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                switch (icon){
                    case "@": {
                        characters[x][y]="@";
                        currentLocation=new Coordinates(x, y);
                    }
                    break;
                    case "!": {characters[x][y]="!";
                                monstersList.add(new Monster("Fly", 80, 15, x, y));
                    }
                        break;
                    case "*": {characters[x][y]="*";
                        monstersList.add(new Monster("Skeleton", 100, 25, x, y));
                    }
                        break;
                    default: characters[x][y]="0";
                }
            }
        }
    }

    public Coordinates getCurrentLocation() {
        return currentLocation;
    }
    public Monster monsterFromCoordinate(int x, int y){
        for(Monster monster: monstersList){
            if(monster.getX()==x&& monster.getY()==y){
                return monster;
            }
        }
        return null;
    }
    public String[][] getTextures() {
        return textures;
    }
    public String[][] getDetails() {
        return details;
    }
    public String[][] getCharacters() {
        return characters;
    }
    public int getLastX() {
        return lastX;
    }
    public int getLastY() {
        return lastY;
    }
    public Hero getHero(){
        return hero;
    }
    public ArrayList<Monster> getMonsters(){
        return monstersListOposit;
    }
}