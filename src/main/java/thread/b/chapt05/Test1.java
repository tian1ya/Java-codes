package thread.b.chapt05;

public class Test1 {
//    volatile static boolean run = true;
    static boolean run = true;

    static Object obj = new Object();
    public static void main(String[] args) {
//        new Thread(() -> {
//            while (true) {
//                synchronized (obj) {
//                    if (!run) break;
//                }
//            }
//        }).start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("设置 false");
//        synchronized (obj) {
//            run = false;
//        }

        new Thread(() -> {
            while (run) {
                System.out.println(run);
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("设置 false");
        run = false;

    }
}
