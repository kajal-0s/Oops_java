/*
 Problems in prior to Executor frameworks:::::Manual Thread Managemen,Resource Management,Scalability,Thread Reuse,Error Handling.
 There are three Executors Framework: Executor,ExecutorService,ScheduledExecutorService.

We use executors to handle multithreading programming and makes it more scalable and maintainable.
 */
/* 
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(3);
      for (int i=1;i< 10;i++){
        int finalI =i;
        executor.submit(() ->{
            long result = factorial(finalI);
            System.out.println(result);
        });
      }
      executor.shutdown();
      try {
       while(! executor.awaitTermination(100, TimeUnit.MILLISECONDS));
       System.out.println("Wating...");
      }catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

        System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

*/
//Future in Executors:
/* 
import java.util.concurrent.*;
public class Main {

  public static void main(String[]args) throws InterruptedException, ExecutionException{
    ExecutorService executorService = Executors.newSingleThreadExecutor();
   Future<?>future= executorService.submit(()->System.out.println("Hello"));
   if(future.isDone()){
    System.out.println("Task is done !");
   }
   System.out.println(future.get());
   executorService.shutdown();
  }}

*/

//collable in executors--------> Collable is for return purpose and runnable is not for return purpose.

/* 
import java.util.concurrent.*;

public class Main {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Callable<String> callable = () -> "Hello";
    Future<String> future = executorService.submit(callable);
    if (future.isDone()) {
      System.out.println("Task is done !");
    }
    System.out.println(future.get());
    executorService.shutdown();

    executorService.submit(() -> System.out.println("Hello"), "abc");
  }
}
*/
//isShutdown()---->
/* 
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> task = () -> {
            int a = 10;
            int b = 20;
            return a + b;
        };

        Future<Integer> future = executorService.submit(task);

        Integer i = future.get();
        System.out.println("Sum is " + i);

        executorService.shutdown();
        System.out.println("Is shutdown? " + executorService.isShutdown());
    }
}

*/

//IsTerminated()

/*import java.util.concurrent.*;

public class Main {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    Future<Integer> future = executorService.submit(() -> {
      int a = 10;
      int b = 20;
      return a + b;
    });

    Integer result = future.get();
    System.out.println("Sum is " + result);

    executorService.shutdown();
    System.out.println("Is shutdown? " + executorService.isShutdown());
  }

}

/*
 * yesb hi toh padha hai humne::::
 * submit(runnable)
 * submit(callable)
 * shutdown(callable)
 * submit(runnable,return something joki future mei chala jyga or future.get()
 * ko call kroge tb wo method ayega,)
 * shutdownnow()
 * awaitTermination()
 * isShutdown()
 * 
 * 
 */

// Example of ExecutorService:
/* 
import java.util.concurrent.*;

public class Main {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    Future<String> future1 = executorService.submit(new DependentService());
    Future<String> future2 = executorService.submit(new DependentService());
    Future<String> future3 = executorService.submit(new DependentService());

    future1.get();
    future2.get();                                       
    future3.get();

    System.out.println("All dependent services finished. Starting main service...");
    executorService.shutdown();
  }
}
*/
//qkii yahnk kuch problems ane lggye bohot sare threads ke time pe isliye now we use CountDownLatch
//CountdownLatch are those tha helps in waiting of one or more threads until a set of operation is being performed in other threads complete.
/* 
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);

        for (int i = 0; i < numberOfServices; i++) {
            executorService.submit(new DependentService(latch));
        }

        latch.await(5,TimeUnit.SECONDS); // Wait for all services to finish
        System.out.println("All dependent services are up. Proceeding with main thread.");

        executorService.shutdownNow();
    }
}
*/
//CyclicBarrier::for reusing purpose of countdown,we use cyclicBarrier----
/* 
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);

        CyclicBarrier barrier = new CyclicBarrier(numberOfServices, () -> {
            System.out.println("All dependent services are up. Proceeding with main thread.");
        });

        for (int i = 0; i < numberOfServices; i++) {
            executorService.submit(new DependentService(barrier));
        }

        executorService.shutdown();
    }
}
*/

//Use of cyclic barrier

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        int numberOfSubsystems = 4;

        CyclicBarrier barrier = new CyclicBarrier(numberOfSubsystems, new Runnable() {
            @Override
            public void run() {
                System.out.println("All subsystems are up and running. System startup complete.");
            }
        });

        Thread webServerThread = new Thread(new Subsystem("Web Server", 2000, barrier));
        Thread databaseThread = new Thread(new Subsystem("Database", 4000, barrier));
        Thread cacheThread = new Thread(new Subsystem("Cache", 3000, barrier));
        Thread messagingServiceThread = new Thread(new Subsystem("Messaging Service", 3500, barrier));

        webServerThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();
    }
}

class Subsystem implements Runnable {
    private final String name;
    private final int initializationTime;
    private final CyclicBarrier barrier;

    public Subsystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " initialization started.");
            Thread.sleep(initializationTime); // simulate the time taken to initialize
            System.out.println(name + " initialization complete.");
            barrier.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(name + " was interrupted.");
        } catch (BrokenBarrierException e) {
            System.err.println(name + " barrier was broken.");
        }
    }
}
