package thread.b;

import java.util.concurrent.locks.LockSupport;

public class TwoPhaseInterrupt {

    public static void main(String[] args) throws InterruptedException {
//        Terminator terminator = new Terminator();
//        terminator.start();
//
//        Thread.sleep(3500);
//        terminator.stop();

        test();
    }

    private static void test() throws InterruptedException {
        Thread unpark = new Thread(() -> {
            System.out.println("park,,,");
            long l = System.currentTimeMillis();
            LockSupport.park();
            long l3 = System.currentTimeMillis();

            System.out.println(l3-l);
            // 这里的输出就是主线程睡觉的时间，主线程执行 interrupt，将程序 park 打断，开始继续执行

            System.out.println("unpark");
            System.out.println("打断状态" + Thread.currentThread().isInterrupted());

            // interrupted status of the thread is cleared by this method
            System.out.println("park interrupt reset,,," + Thread.interrupted());

            System.out.println("park again,,,");
            long l4 = System.currentTimeMillis();
            LockSupport.park();
            long l5 = System.currentTimeMillis();
            System.out.println(l5-l4);

        });

        unpark.start();

        Thread.sleep(2000);

        unpark.interrupt();
    }

    private static class Terminator {
        private Thread monitor;

        public void start() {
            monitor = new Thread(() -> {
                while (true) {
                    Thread thread = Thread.currentThread();
                    boolean interrupted = thread.isInterrupted();
                    if (interrupted) {
                        System.out.println("料理后事");
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                        System.out.println("继续监控");
                    } catch (InterruptedException e) {
                        thread.interrupt();
                        e.printStackTrace();
                    }

                }
            });

            monitor.start();
        }

        public void stop() {
            monitor.interrupt();
        }
    }

}
