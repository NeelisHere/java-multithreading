package ThreadPoolExecutor;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTest {
    private static final Integer CORE_POOL_SIZE = 3;
    private static final Integer MAX_POOL_SIZE = 5;
    private static final Integer KEEP_ALIVE_TIME = 1;
    private static final Integer QUEUE_SIZE = 5;
    private static final Integer THREAD_EXEC_TIME = 15000;
    private static final Integer N = 15;

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(QUEUE_SIZE);


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                queue,
                (r) -> {
                    Thread t = new Thread(r);
                    t.setName("Thread-" + UUID.randomUUID());
                    System.out.println(t.getName() + " created!");
                    return t;
                },
                (r, ex) -> {
                    System.out.println("Thread rejected: " + r);
                }
        );

        IntStream.range(1, N + 1).forEach((val) -> {
            executor.submit(() -> {
                try {
                    System.out.printf("%s executing...\n", Thread.currentThread().getName());
                    Thread.sleep(THREAD_EXEC_TIME);
                    System.out.printf("%s execution completed\n", Thread.currentThread().getName());
                    System.out.println("task queue size: " + queue.size());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        executor.shutdown();
    }
}
