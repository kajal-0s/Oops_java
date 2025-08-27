//CompletableFuture was introduce under java 8 to handle Asynchronous programming

import java.util.concurrent.CompletableFuture;

public class CF {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Worker"); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Good practice
                return "Interrupted";
            }
            return "OK";
        });

        // Handle the result when it's ready
        completableFuture.thenAccept(result -> {
            System.out.println("Result: " + result);
        });

        // Prevent the main thread from exiting early
        completableFuture.join();
    }
}
