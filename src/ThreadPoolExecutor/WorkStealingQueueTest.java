package ThreadPoolExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WorkStealingQueueTest {
    public static void main(String[] args) {
        try {
            ForkJoinPool pool = ForkJoinPool.commonPool();;
            Future<Integer> res = pool.submit(new ComputeSumTask(1, 10000));
            System.out.println(res.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
