package logic;

import java.util.HashMap;

public class Hero {
    private String name;
    private String race;
    private int currentHealt;
    private HashMap<InventoryObject, Integer> inventory=new HashMap<InventoryObject, Integer>(5);
    private boolean overloaded;
    private int count;

    public Hero(String name, String race) {
        this.name = name;
        this.race = race;
        this.currentHealt = 100;
        this.overloaded = false;
    }

    public void addInventory(InventoryObject object, int amount){
        if(count<5){
            count++;
            inventory.put(object, amount);
        }
    }

    public void removeInventory(InventoryObject object){
        if(inventory.get(object)-1>0){
            inventory.put(object,inventory.get(object)-1);
            } else {
            inventory.remove(object);
        }
    }

    public HashMap<InventoryObject, Integer> getInventory(){
        return inventory;
    }
}
