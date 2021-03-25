package thread.b;

public class CounterTest {

    static Object obj = new Object();
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    counter++;
                }
            }
        });


        Thread thread = new Thread(() -> {
            synchronized (obj) {
                for (int i = 0; i < 10; i++) {
                    counter--;
                }
            }
        });

        thread.start();
        thread1.start();


        thread.join();
        thread1.join();

        System.out.println(counter);
    }
}
