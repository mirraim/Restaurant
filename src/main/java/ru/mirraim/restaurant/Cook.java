package ru.mirraim.restaurant;

public class Cook implements Runnable{
    public boolean continueWorking = true;

    public void run() {
        boolean needToWait;
        while (continueWorking || needToCookOrders()) {
            try {
                synchronized (this) {
                    needToWait = !needToCookOrders();
                    if (!needToWait) {
                        cook();
                    }
                }
                if (continueWorking && needToWait) {
                    System.out.println("Повар отдыхает");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean needToCookOrders() {
        return !Manager.getInstance().getOrderQueue().isEmpty();
    }

    private void cook() throws InterruptedException {
        Manager manager = Manager.getInstance();
        Order order = manager.getOrderQueue().poll();      // повар берет заказ из очереди
        System.out.printf("Заказ будет готовиться %d мс для стола №%d%n", order.getTime(), order.getTable().getTableNumber());
        Thread.sleep(order.getTime());     // готовим блюдо
        Dishes dishes = new Dishes(order.getTable());       //  это готовое блюдо
        System.out.printf("Заказ для стола №%d готов%n", dishes.getTable().getTableNumber());
        manager.getDishesQueue().add(dishes);
    }
}
