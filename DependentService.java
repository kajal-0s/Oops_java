
/*import java.util.concurrent.Callable;

public class DependentService implements Callable<String> {
    @Override
    public String call() throws Exception {
        
        System.out.println("Running " + Thread.currentThread().getName());
        Thread.sleep(1000); 
        return "Done";
    }
}*/
// countdownLatch implementation class-->
/* 
import java.util.concurrent.CountDownLatch;

public class DependentService implements Runnable {

    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is working...");
            Thread.sleep(6000); // Simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Properly handle interrupt
        } finally {
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished. Latch count: " + latch.getCount());
        }
    }
}
*/
//for CyclicBarrier--->
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DependentService implements Runnable {

    private final CyclicBarrier barrier;

    public DependentService(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " service started...");
            Thread.sleep(1000); // Simulate some startup work
            System.out.println(Thread.currentThread().getName() + " is waiting at the barrier...");

            Thread.sleep(6000); // Simulate more work before waiting
            barrier.await(); // Wait at the barrier

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Proper handling
            System.err.println(Thread.currentThread().getName() + " was interrupted.");
        } catch (BrokenBarrierException e) {
            System.err.println(Thread.currentThread().getName() + " barrier broken.");
        }
    }
}
