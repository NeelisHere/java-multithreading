package ThreadPoolExecutor;

import java.util.concurrent.*;

public class TestCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        CompletableFuture<Void> usernameInUppercase = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
                return "John";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executor).thenApplyAsync((String name) -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
                return name.toUpperCase();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executor).thenAccept((name) -> {
                    System.out.println(name);
                    System.out.println("All operations completed");
                });
        usernameInUppercase.get();

        CompletableFuture<Integer> fetchUserId = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String> fetchUsername = fetchUserId.thenComposeAsync((id) -> CompletableFuture.supplyAsync(() -> "john" + id));

        CompletableFuture<String> res = fetchUserId.thenCombineAsync(fetchUsername, (userId, username) -> username + "-" + userId);

        System.out.println(res.get());


        System.out.println("main thread");

        executor.shutdown();
    }
}
