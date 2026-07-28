package ir.maktabsharif.threads;

import ir.maktabsharif.exception.ThreadException;
import ir.maktabsharif.model.Book;

public class Warehouse {
    private Book book;

    public synchronized void store(Book book) {
        try {
            while (this.book != null) {
                System.out.println("Warehouse is Full! Producer is Waiting...");
                wait();
            }

            this.book = book;

            System.out.println("Producer Stored!\n" + book.getTitle());

            notifyAll();
        }
        catch (InterruptedException e) {
            throw new ThreadException(e.getMessage());
        }
    }

    public synchronized Book ship() {
        try {
            while (book == null) {
                System.out.println("Warehouse is Empty! Consumer is Waiting...");
                wait();
            }

            Book shippedBook = book;
            book = null;

            System.out.println("Consumer Shipped!\n" + shippedBook.getTitle());

            notifyAll();

            return shippedBook;
        }
        catch (InterruptedException e) {
            throw new ThreadException(e.getMessage());
        }
    }
}
