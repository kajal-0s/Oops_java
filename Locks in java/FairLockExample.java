//UnFairLockExample---->
/* 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnFairLockExample {

    private final Lock unfairLock = new ReentrantLock();

    public void accessResource() {
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the local");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            unfairLock.unlock();
            System.out.println(Thread.currentThread().getName() + "released the lock.");
        }
    }

    public static void main(String[] args) {
        UnFairLockExample example = new UnFairLockExample();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");
        Thread thread3 = new Thread(task, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}

*/

// FairLockExample--------->>>>
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample {

    private final Lock unfairLock = new ReentrantLock(true);

    public void accessResource() {
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the local");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            unfairLock.unlock();
            System.out.println(Thread.currentThread().getName() + "released the lock.");
        }
    }

    public static void main(String[] args) {
        FairLockExample example = new FairLockExample();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");
        Thread thread3 = new Thread(task, "Thread 3");
        try {
            thread1.start();
            Thread.sleep(50);
            thread2.start();
            Thread.sleep(50);
            thread3.start();

        } catch (Exception e) {

        }
    }
}