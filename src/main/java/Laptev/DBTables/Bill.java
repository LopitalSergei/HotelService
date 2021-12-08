package Laptev.DBTables;

public class Bill {
    private int cost;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Cost = " + cost;
    }
}
