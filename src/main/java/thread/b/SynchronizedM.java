package thread.b;

public class SynchronizedM {

    private static class Room {
        private int counter = 0;

        public void increment() {
            synchronized (this) {
                counter++;
            }
        }

        public void decrease() {
            synchronized (this) {
                counter--;
            }
        }

        public int getCounter(){
            synchronized (this) {
                return counter;
            }
        }
    }

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Room room = new Room();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                room.increment();
            }
        });


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                room.decrease();
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println(room.getCounter());
    }
}
