package thread.b;

public class SynchronizedM2 {

    private static class Number {
        /*
            synchronized 锁住的是 Number 类对象
         */
        public static synchronized void a() {
            System.out.println("a");
        }

        /*
            synchronized 锁住的是 Number 类对象
         */
        public static synchronized void b() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }

    }

    public static void main(String[] args) {

        Number number = new Number();
        Number number1 = new Number();

        Thread thread = new Thread(() -> number.a());
        Thread thread1 = new Thread(() -> number1.b());

        thread.start();
        thread1.start();
    }
}
