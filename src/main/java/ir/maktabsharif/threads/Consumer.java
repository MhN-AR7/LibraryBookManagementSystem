package ir.maktabsharif.threads;

public class Consumer implements Runnable{
    private final Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            warehouse.ship();
        }
    }
}
