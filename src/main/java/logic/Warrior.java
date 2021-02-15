package logic;

public class Warrior extends Hero {
    private Weapon weapon;
    private Armor armor;

    public Warrior(String name, String race, Weapon weapon, Armor armor) {
        super(name, race);
        this.weapon=weapon;
        this.armor=armor;
    }

    public void setItem(int index){
        if(inventory.get(index).equals(ArmorPart.class)){
            ArmorPart temp=armor.actualArmor((ArmorPart)inventory.get(index));
            armor.setArmor((ArmorPart)inventory.get(index));
            inventory.add(temp);
        } else if(inventory.get(index).equals(Weapon.class)){
            if(weapon!=null){
                InventoryObject temp=weapon;
                weapon=(Weapon)inventory.get(index);
                inventory.remove(inventory.get(index));
                inventory.add(temp);
            }else {
                weapon=(Weapon)inventory.get(index);
            }
        } else{
            System.out.println("Something wrong with your weapon/armor");
        }
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public void damageGet(int howMuch){
        currentHealt-=howMuch;
    }

    public void eat(int index){
        if(inventory.get(index).getClass()==Food.class){
            Food food =(Food)inventory.get(index);
            currentHealt+=food.getHP();
            removeInventory(inventory.get(index));
        }
    }
}
