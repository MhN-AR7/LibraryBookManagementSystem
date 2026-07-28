package ir.maktabsharif.threads;

import ir.maktabsharif.exception.ThreadException;

public class Task implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Started...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new ThreadException(e.getMessage());
        }

        System.out.println(Thread.currentThread().getName() + "Finished!");
    }
}
