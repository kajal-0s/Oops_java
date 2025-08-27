import java.util.concurrent.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;

public class Main {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    int numberOfServices = 3;
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
    CountDownLatch latch = new CountDownLatch(numberOfServices);
    for(int i=0;i< numberOfServices;i++){
      new Thread(new DependentService(latch)).start();
    }
    
    latch.await();

    System.out.println("Main");
    executorService.shutdown();

  }
}
