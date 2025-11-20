import monitor_lock.SharedResource;
import producer_consumer_problem.Consumer;
import producer_consumer_problem.Producer;
import producer_consumer_problem.ResourceOne;

public class Main {
    public static void main(String[] args){
        try {
            ResourceOne sharedResource = new ResourceOne();
            Thread producer = new Thread(new Producer(sharedResource));
            Thread consumer = new Thread(new Consumer(sharedResource));
            consumer.start();
            Thread.sleep(2000);
            producer.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}