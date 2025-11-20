package ProducerConsumerProblem;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class SharedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Integer buffer_limit;

    public SharedBuffer(Integer bufferLimit) {
        this.buffer_limit = bufferLimit;
    }

    public synchronized void consume() throws Exception {
        while (buffer.isEmpty()) {
            System.out.printf("buffer empty, thread - %s waiting...\n", Thread.currentThread().getName());
            wait();
        }
        Integer val = buffer.poll();
        System.out.printf("thread - %s consumed item=%d\n", Thread.currentThread().getName(), val);
        notify();
    }

    public synchronized void produce() throws Exception {
        while (buffer.size() == buffer_limit) {
            System.out.printf("buffer is full, thread - %s waiting...\n", Thread.currentThread().getName());
            wait();
        }
        Random random = new Random();
        int min = 10;
        int max = 50;
        int val = min + random.nextInt(max - min + 1);
        buffer.add(val);
        System.out.printf("Thread - %s added value=%d to the buffer\n", Thread.currentThread().getName(), val);
        notify();
    }
}
