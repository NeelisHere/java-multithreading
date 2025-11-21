package locks.readWriteLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void readData() {
        try {
            lock.readLock().lock();
            System.out.printf("Lock acquired by thread-%s\n", Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
            System.out.printf("Lock released by thread-%s\n", Thread.currentThread().getName());
        }
    }

    public void writeData() {
        try {
            lock.writeLock().lock();
            System.out.printf("Lock acquired by thread-%s\n", Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
            System.out.printf("Lock released by thread-%s\n", Thread.currentThread().getName());
        }
    }
}
