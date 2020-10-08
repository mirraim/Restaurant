package ru.mirraim.restaurant;

import java.util.concurrent.atomic.AtomicInteger;

public class Table {
    private static AtomicInteger tableCount = new AtomicInteger(1);
    private int tableNumber;

    public Table() {
        this.tableNumber = tableCount.getAndIncrement();
    }

    public static AtomicInteger getTableCount() {
        return tableCount;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Order getOrder(){
        return new Order(this);
    }
}
