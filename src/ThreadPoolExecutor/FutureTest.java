package ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        Future<?> f1 = executor.submit(() -> {
            System.out.println("f1 executing...");
        });

        Task task =  new Task(1, "some task", false);
        Future<Task> f2 = executor.submit(() -> {
            try {
                Thread.sleep(2000);
                task.setCompleted(true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, task);

        Future<List<Task>> f3 = executor.submit(() -> {
            try {
                Thread.sleep(2000);
                List<Task> tasks = new ArrayList<Task>();
                tasks.add(new Task(1, UUID.randomUUID().toString(), false));
                tasks.add(new Task(2, UUID.randomUUID().toString(), false));
                tasks.add(new Task(3, UUID.randomUUID().toString(), false));
                return tasks;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            System.out.println(f1.get());

            Task t = f2.get(2, TimeUnit.SECONDS);
            System.out.println(t.toString());

            List<Task> tasks = f3.get();
            System.out.println(tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            executor.shutdown();
        }

    }
}
