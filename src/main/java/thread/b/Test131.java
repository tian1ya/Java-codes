package thread.b;

public class Test131 {
    static final Object room = new Object();
    private static boolean hasCig = false;
    private static boolean ahsTakeout = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (room) {
                System.out.println("【小南】" + "烟送到了么 " + hasCig);

                if (!hasCig) {
                    try {
                        System.out.println("【小南】没有烟，先歇会");
                        room.wait(); // 会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("【小南】" + "烟送到了么 " + hasCig);
                    if (hasCig) {
                        System.out.println("【小南】" + "可以开始干活了");
                    }
                }
            }
        }, "小南").start();


        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    System.out.println("【其他人】可以开始干活了");
                }
            }, "其他人").start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (room) {
                System.out.println("【送烟人】烟送到了");
                hasCig = true;
                room.notify();
            }
        }).start();
    }
}
