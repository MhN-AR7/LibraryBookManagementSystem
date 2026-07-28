package ir.maktabsharif.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private final static ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void submitTasks() {
        for (int i = 0; i < 10; i++) {
            executor.submit(new Task());
        }

        executor.shutdown();
    }
}
