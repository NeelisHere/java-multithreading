package locks.StampedOptimisticLocks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    private final StampedLock lock = new StampedLock();
    private static final int READ_TIME = 1000;
    private static final int WRITE_TIME = 5000;

    public void readData() {
        long stamp = lock.tryOptimisticRead();
        try {
            System.out.printf("thread-%s reading data...\n", Thread.currentThread().getName());
            Thread.sleep(READ_TIME);

            if (!lock.validate(stamp)) {
                System.out.printf("thread-%s optimistic read failed, retrying with safe read...\n", Thread.currentThread().getName());
                long safe_read_lock_timestamp = lock.readLock();
                try {
                    Thread.sleep(READ_TIME);
                    System.out.printf("thread-%s safe read completed\n", Thread.currentThread().getName());
                } finally {
                    lock.unlockRead(safe_read_lock_timestamp);
                }
            } else {
                System.out.printf("thread-%s optimistic reading completed\n", Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeData() {
        long stamp = lock.writeLock();
        try {
            System.out.printf("thread-%s writing data...\n", Thread.currentThread().getName());
            Thread.sleep(WRITE_TIME);
            System.out.printf("thread-%s writing completed\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockWrite(stamp);
            System.out.printf("Lock released by thread-%s\n", Thread.currentThread().getName());
        }
    }
}
