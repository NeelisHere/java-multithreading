package ThreadPoolExecutor;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ComputeSumTask extends RecursiveTask<Integer> {
    private final int start;
    private final int end;

    ComputeSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println("summation: " + start + " - " + end);
        if (start - end <= 5) {
            return IntStream.range(start, end + 1).sum();
        } else {
            int mid = (start + end) / 2;
            ComputeSumTask leftTask = new ComputeSumTask(start, mid);
            ComputeSumTask rightTask = new ComputeSumTask(mid + 1, end);

            leftTask.fork();
            rightTask.fork();

            int l = leftTask.join();
            int r = rightTask.join();
            return l + r;
        }
    }
}
