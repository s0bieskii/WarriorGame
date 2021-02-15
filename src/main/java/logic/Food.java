package logic;

public class Food extends InventoryObject{
    private int healtPointsRegeneration;

    public Food(String name, double weigth, int count, int healtPointsRegeneration){
        super(name, weigth, count);
        this.healtPointsRegeneration=healtPointsRegeneration;
    }

    public int getHP(){
        return healtPointsRegeneration;
    }
}
