package locks.Semaphores;

import java.util.concurrent.Semaphore;

public class SharedResource {
    private Semaphore lock;

    public SharedResource(int x) {
        this.lock = new Semaphore(x);
    }

    public void task() {
        try {
            lock.acquire();
            System.out.printf("Lock acquired by thread-%s\n", Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.release();
            System.out.printf("Lock released by thread-%s\n", Thread.currentThread().getName());
        }
    }
}
