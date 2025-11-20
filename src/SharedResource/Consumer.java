package SharedResource;

public class Consumer implements Runnable {
    private ResourceOne sharedResource;

    public Consumer(ResourceOne sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " consumer started");
//            Thread.sleep(5000);
            sharedResource.consumeItem();
            System.out.println(Thread.currentThread().getName() + " consuming items completed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
