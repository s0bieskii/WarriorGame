package logic;

import java.util.ArrayList;
import java.util.Objects;

public class Monster {
    String name;
    int health, currentHealt;
    int damage;
    Coordinates coordinates;
    ArrayList<InventoryObject> drop=new ArrayList<InventoryObject>();

    public Monster(String name, int health, int damage, int x, int y) {
        this.name = name;
        this.health = health;
        this.currentHealt = health;
        this.damage = damage;
        coordinates=new Coordinates(x, y);
    }



    public int getX(){
        return coordinates.getX();
    }

    public int getY(){
        return coordinates.getY();
    }

    public void takeDamage(int damage){
        currentHealt-=damage;
    }

    public int getHP(){
        return currentHealt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return health == monster.health &&
                currentHealt == monster.currentHealt &&
                damage == monster.damage &&
                Objects.equals(name, monster.name) &&
                Objects.equals(coordinates, monster.coordinates) &&
                Objects.equals(drop, monster.drop);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", currentHealt=" + currentHealt +
                ", coordinates=" + coordinates +
                '}';
    }

    public int getDamage(){
        return damage;
    }
}
