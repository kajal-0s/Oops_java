
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);

        }catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState()); // This represents the NEW thread.
        t1.start();
        System.out.println(t1.getState()); // This represents about the thread has started.

        System.out.println(Thread.currentThread().getState()); // This represents about the main thread state.
        Thread.sleep(100);
        System.out.println(t1.getState());
        t1.join();
        System.out.println(t1.getState());

    }

}


//THREAD VS RUNNABLE:

/*
 we don't use thread class if there will be the case of multiple inheritance because java do not support multiple inheritance.
 AAO HINDI MEI SMJHATE HAI:
 hum thread ka use tab nhi krrhe honge jahn koi ek class dushre class ko inherit krrha hoga qkii wahn multiple inheritance ka situation create hojyga oki java support nhi krta
 toh uss situation mei;
 WE'LL USE RUNNABLE (that uses implement in it).
 */