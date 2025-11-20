package MonitorLock;

public class SharedResource {
    public synchronized void task1() {
        try {
            System.out.println(Thread.currentThread().getName() + " inside task1");
            System.out.println(Thread.currentThread().getName() + " executing task1...");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " completing executing task1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void task2() {
        System.out.println(Thread.currentThread().getName() + " inside task2");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " executing sync block inside task2");
        }
    }

    public void task3() {
        System.out.println(Thread.currentThread().getName() + " inside task3");
    }
}
