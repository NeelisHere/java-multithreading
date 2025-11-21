package locks.reentrantLocks;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    private final ReentrantLock lock = new ReentrantLock();
    public void task() {
        try {
            lock.lock();
            System.out.printf("Lock acquired by thread-%s\n", Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
            System.out.printf("Lock released by thread-%s\n", Thread.currentThread().getName());
        }
    }
}
