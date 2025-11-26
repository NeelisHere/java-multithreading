package ThreadPoolExecutor;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> data = new ThreadLocal<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> {
            data.set(Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            data.remove();
        });

        for (int i = 1; i <= 10; ++i) {
            executor.submit(() ->{
                System.out.println(data.get());
            });
        }
        executor.shutdown();
    }
}
