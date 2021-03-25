package thread.b;

public class ManyThreadState {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("running");
        }); // NEW


        Thread t2 = new Thread(() -> {
            while (true) {

            }
        });

        t2.start(); // RUNNABLE


        Thread t3 = new Thread(() -> {
            System.out.println("running"); // TERMINATED
        });
        t3.start();


        Thread t4 = new Thread() {
            @Override
            public void run() {
                synchronized (ManyThreadState.class) {
                    try {
                        Thread.sleep(100000); // TIMED_WAITING
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join(); // WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t5.start();

        Thread t6 = new Thread(){
            @Override
            public void run() {
                synchronized (ManyThreadState.class) {
                    try {
                        Thread.sleep(100000); // BLOCK, 锁被 t4 拿走了
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        System.out.println("t1：" + t1.getState());
        System.out.println("t2：" + t2.getState());
        System.out.println("t3：" + t3.getState());
        System.out.println("t4：" + t4.getState());
        System.out.println("t5：" + t5.getState());
        System.out.println("t6：" + t6.getState());

    }

}
