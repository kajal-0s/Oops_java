// INTRINSIC--------->>>>> these are built into every object in java.
/* 
public class BankAccount {
    private int balance = 100;


    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " processing with withdrawl");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
              //  throw new RuntimeException(e);

            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + "completed withdrawal. remaining balance: "+balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");

        }
    }

}
*/
/* 
TRY LOCK
Lock() : Acquires the lock, blocking the thread until it's available,
TryLock():Attempts to acquire the lock without waiting, returning true if successful and false if the lock is already held.
TryLock(time):Waits up to the specified time to acquire the lock, returning false if it's still unavailable after the timeout.
Unlock():Releases the lock so other threads can acquire it — must be called in a finally block to avoid deadlocks.

  */

  //EXPLICIT----------->>> we can control these locks by own using different locks method.
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "processing with withdrawal");
                        Thread.sleep(3000);
                        balance -= amount;

                        System.out.println(Thread.currentThread().getName()
                                + "complete withdrawal. Remaining balance : " + balance);

                    } catch (Exception e) {
                        Thread.currentThread().interrupt();

                    } finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance  ");
                }

            } else {
                System.out
                        .println(Thread.currentThread().getName() + "Could not acquire the lock will try again later.");

            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();

        }
        if (Thread.currentThread().isInterrupted()) {
            System.out.println(" ");
        }

    }
}