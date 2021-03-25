package thread.b;

public class Test133 {
    static final Object room = new Object();
    private static boolean hasCig = false;
    private static boolean ahsTakeout = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (room) {
                System.out.println("【小南】" + "烟送到了么 " + hasCig);

                while (!hasCig) {
                    try {
                        System.out.println("【小南】没有烟，先歇会");
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("【小南】" + "烟送到了么 " + hasCig);
                if (hasCig) {
                    System.out.println("【小南】" + "可以开始干活了");
                } else {
                    System.out.println("【小南】" + "干不了活了");
                }
            }
        },"小南").start();


        new

    Thread(() ->

    {
        synchronized (room) {
            System.out.println("【小女】" + "外卖送到了么 " + ahsTakeout);

            if (!hasCig) {
                try {
                    System.out.println("【小女】外卖送到了么，先歇会");
                    room.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("【小女】" + "外卖送到了么 " + ahsTakeout);
                if (ahsTakeout) {
                    System.out.println("【小女】" + "外卖送到了,开始干活");
                } else {
                    System.out.println("【小女】" + "外卖没送到，干不了活了");
                }
            }
        }
    },"小女").

    start();

        try

    {
        Thread.sleep(1000);
    } catch(
    InterruptedException e)

    {
        e.printStackTrace();
    }

        /*
            notify：虚假唤醒，这里可能会将 小南唤醒，但是唤醒之后小南线程的执行逻辑还是不正确的
            notifyAll：唤醒所有，但是唤醒的逻辑可能会满足不了，唤醒也没有如这里的 小南的烟还没有的
            这个时候不能使用 线程解决，应该是从代码逻辑上解决，如将小南线程if 条件边为 while 条件
         */
        new

    Thread(() ->

    {
        // 这里不能加 synchronized
        synchronized (room) {
            System.out.println("【送外卖的】送外卖");
            ahsTakeout = true;
            room.notifyAll();
        }
    }).

    start();
}
}
