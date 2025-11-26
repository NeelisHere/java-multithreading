package ThreadPoolExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) {
        try {
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
//            Future<Integer> test = executor.schedule(() -> 12, 5, TimeUnit.SECONDS);
//            Thread.sleep(1000);
            Runnable r = () -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("task");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
//            executor.scheduleAtFixedRate(r, 1, 3, TimeUnit.SECONDS);
            executor.scheduleWithFixedDelay(r, 1, 1, TimeUnit.SECONDS);
//            System.out.println(test.get());
//            executor.shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
