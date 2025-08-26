
/*
// START RUN SLEEP JOIN------>

start()--Creates a new thread and begins its execution by calling the run() method in tha background.
run()-Contains the code that defines the task the thread will perform.
sleep()-Pause the current thread for a specified amount of time(in millisecond).
join()-Makes the current thread wait until the specified thread has finished executing.

 * public class ThreadMethod extends Thread {
 * 
 * @Override
 * public void run() {
 * System.out.println("Thread is running...");
 * 
 * for (int i = 0; i <= 5; i++) {
 * try {
 * ThreadMethod.sleep(5000);
 * } catch (InterruptedException e) {
 * 
 * e.printStackTrace();
 * }
 * System.out.println(i);
 * 
 * }
 * 
 * }
 * 
 * public static void main(String[] args) throws InterruptedException {
 * ThreadMethod t1 = new ThreadMethod();
 * t1.start();
 * t1.join();
 * System.out.println("hello");
 * }
 * }
 * 
 */
// SETPRIORITY------>sets the priority of a thread from(1 to 10) to suggest how urgently it should be scheduledby the CPU,through actual behavior depends on the JVM and OS. 
//Priority Range:
//    Constant	          Value    Meaning
// Thread.MIN_PRIORITY	   1      Lowest priority
// Thread.NORM_PRIORITY	   5	  Default (normal) priority
// Thread.MAX_PRIORITY  	10	  Highest priority

/*
 * public class ThreadMethod extends Thread {
 * public ThreadMethod(String name) {
 * super(name);
 * }
 * 
 * @Override
 * public void run() {
 * for (int i = 0; i < 5; i++) {
 * String a = "";
 * for (int j = 0; j < 10000; j++) {
 * a += "a";
 * 
 * }
 * System.out.println(Thread.currentThread().getName() + " - Priority: " +
 * Thread.currentThread().getPriority()
 * + "-count: " + i);
 * try {
 * ThreadMethod.sleep(100);
 * 
 * } catch (Exception e) {
 * 
 * }
 * }
 * }
 * 
 * public static void main(String[] args) throws InterruptedException {
 * ThreadMethod t1 = new ThreadMethod("rahul");
 * ThreadMethod t2 = new ThreadMethod("rohit");
 * ThreadMethod t3 = new ThreadMethod("ram");
 * 
 * t1.setPriority(Thread.MIN_PRIORITY);
 * t2.setPriority(Thread.NORM_PRIORITY);
 * t3.setPriority(Thread.MAX_PRIORITY);
 * t1.start();
 * t2.start();
 * t3.start();
 * 
 * }
 * }
 */
// INTERRUPT METHOD----->interrupt() is used to request a thread to stop or wake up, especially if it's sleeping or waiting.
/*
 * class ThreadMethod extends Thread {
 * 
 * @Override
 * public void run() {
 * try {
 * Thread.sleep(1000);
 * System.out.println("Thread is running...");
 * } catch (InterruptedException e) {
 * System.out.println("Thread interrupted: "+e);
 * }
 * }
 * public static void main(String[]args) throws InterruptedException {
 * ThreadMethod t1 = new ThreadMethod();
 * t1.start();
 * t1.interrupt();
 * 
 * 
 * }
 * }
 */
// YIELD METHOD------->yield() temporarily pauses the current thread to give other threads of the same or higher priority a chance to execute.
/*
 * class ThreadMethod extends Thread {
 * public ThreadMethod(String name) {
 * super(name);
 * }
 * 
 * @Override
 * public void run() {
 * for (int i = 0; i < 5; i++) {
 * System.out.println(Thread.currentThread().getName() + " is running");
 * Thread.yield();
 * 
 * }
 * 
 * }
 * 
 * public static void main(String[] args) throws InterruptedException {
 * ThreadMethod t1 = new ThreadMethod("t1");
 * ThreadMethod t2 = new ThreadMethod("t2");
 * 
 * t1.start();
 * t2.start();
 * }
 * }
 * 
 * /*
 * //WITHOUT YIELD OUTPUT WILL BE:
 * t1 is running
 * t1 is running
 * t2 is running
 * t2 is running
 * t1 is running
 * t1 is running
 * t2 is running
 * t2 is running
 * t2 is running
 * t1 is running
 * 
 * WITH YIELD OUTPUT WILL BE:
 * t1 is running
 * t2 is running
 * t1 is running
 * t2 is running
 * t1 is running
 * t2 is running
 * t1 is running
 * t1 is running
 * t2 is running
 * t2 is running

 */

// setDaemon:--------these are background threads that runs inside java program and does not prevent the JVM from exiting when all users (non-daemon) threads are done.

class ThreadMethod extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Hello world..!");
        }
    }

    public static void main(String[] args) {
        ThreadMethod t1 = new ThreadMethod();
       // ThreadMethod t2 = new ThreadMethod();

        t1.setDaemon(true);
        t1.start();
      //  t2.start();

        System.out.println("Main done");
        t1.start();
    }
}
