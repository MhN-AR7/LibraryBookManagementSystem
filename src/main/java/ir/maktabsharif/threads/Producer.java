package ir.maktabsharif.threads;

import ir.maktabsharif.model.Book;

public class Producer implements Runnable{
    private final Warehouse warehouse;

    public Producer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            Book book = new Book();
            book.setTitle("Book " + i+1);
            warehouse.store(book);
        }
    }
}
