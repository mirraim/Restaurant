package ru.mirraim.restaurant;

public class Order {
    private long time;
    private Table table;

    public Order(Table table) {
        this.time = (long)(Math.random() * 200);
        this.table = table;
    }

    public long getTime() {
        return time;
    }

    public Table getTable() {
        return table;
    }
}
