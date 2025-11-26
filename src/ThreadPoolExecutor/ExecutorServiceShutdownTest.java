package ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceShutdownTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pool.shutdown();
        try {
            System.out.println("termination status: " + pool.awaitTermination(2, TimeUnit.SECONDS));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pool.submit(() -> {
            System.out.println("new task");
        });
    }
}
