package producer_consumer_problem;

public class ResourceOne {
    private int items = 0;

    public void produceItem() {
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " producing started...");
                items++;
                notifyAll();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void consumeItem() {
        try {
            synchronized (this) {
                while (items == 0) {
                    System.out.println(Thread.currentThread().getName() + " waiting before consumption...");
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + " consumption started...");
                items--;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
