import ProducerConsumerProblem.SharedBuffer;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
        try {
            SharedBuffer sharedBuffer = new SharedBuffer(5);
            
            Thread producer = new Thread(() -> {
                System.out.printf("Starting thread - %s\n", Thread.currentThread().getName());
                IntStream.range(1, 7).forEach((x) -> {
                    try {
                        sharedBuffer.produce();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                System.out.printf("Stopping thread - %s\n", Thread.currentThread().getName());
            }, "producer_thread");
            
            Thread consumer = new Thread(() -> {
                try {
                    System.out.printf("Starting thread - %s\n", Thread.currentThread().getName());
                    IntStream.range(1, 7).forEach((x) -> {
                        try {
                            sharedBuffer.consume();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                    System.out.printf("Stopping thread - %s\n", Thread.currentThread().getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, "consumer_thread");

            producer.start();
            consumer.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}