package SharedResource;

public class Producer implements Runnable {
    private ResourceOne sharedResource;

    public Producer(ResourceOne sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " producer started");
//            Thread.sleep(5000);
            sharedResource.produceItem();
            System.out.println(Thread.currentThread().getName() + " producing items completed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
