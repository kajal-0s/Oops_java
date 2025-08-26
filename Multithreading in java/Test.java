public class Test {
    public static void main(String[] args) {
        World world = new World();
        world.start();

        for (;;) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
