package logic;

import java.util.Objects;

public abstract class InventoryObject{
    String name;
    double weigth;
    int count;

    public InventoryObject(String name, double weigth, int count) {
        this.name = name;
        this.weigth = weigth;
        this.count = 1;
    }

    public void increasCount(){
        count++;
    }

    public double getWeigth(){
        return weigth;
    }

    public void decreaseCount(){
        count=count--;
    }

    public int getCount(){
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryObject that = (InventoryObject) o;
        return Double.compare(that.weigth, weigth) == 0 &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weigth);
    }
}
