import ProducerConsumerProblem.SharedBuffer;
import locks.StampedOptimisticLocks.SharedResource;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
        try {
            SharedResource resource = new SharedResource();
            Thread t1 = new Thread(resource::readData, "t1");
            Thread t4 = new Thread(resource::writeData, "t4");
            t1.start();
            Thread.sleep(5000);
            t4.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}