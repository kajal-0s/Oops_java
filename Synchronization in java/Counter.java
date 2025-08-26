/*SYNCHRONIZATION:Synchronization prevents race conditions by allowing only
 one thread at a time to access a critical section (shared code or data),
  ensuring that threads don’t overwrite each other's changes or read inconsistent data.
*/
public class Counter {

    private int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }

}
