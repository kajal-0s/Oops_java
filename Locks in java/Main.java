public class Main {
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();

        // Define the task for each thread
        Runnable task = new Runnable() {
            @Override
            public void run() {
                sbi.withdraw(50);
            }
        };

        // Pass the task AND name to each thread
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
