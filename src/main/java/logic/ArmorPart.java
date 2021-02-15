package logic;

public class ArmorPart extends InventoryObject{
    private int damageResistance;
    private armorPartEnum bodyPart;

    public ArmorPart(String name, double weigth, int count, int damageResistance, armorPartEnum bodypart) {
        super(name, weigth, count);
        this.damageResistance=damageResistance;
        this.bodyPart=bodyPart;
    }

    public armorPartEnum getBodyPart(){
        return bodyPart;
    }
}
