package logic;

public class Weapon extends InventoryObject{
    private int damagePoints;

    public Weapon(String name, double weigth, int count, int damagePoints) {
        super(name, weigth, count);
        this.damagePoints=damagePoints;
    }

    public int getDamagePoints(){
        return damagePoints;
    }
}
