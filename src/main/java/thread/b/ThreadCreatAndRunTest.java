package thread.b;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ThreadCreatAndRunTest {

    private static int r=0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        // Thread 代表一个线程
//        Thread thread = new Thread(() -> System.out.println("run sub thread"));
//        thread.setName("t1");
//        thread.start();
//        System.out.println("run main thread");
//
//        // Runnable 是可运行的任务， 线程要执行的代码
//        Runnable runnable = () -> System.out.println("runable sub thread");
//        runnable.run();
//
//
//        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
//            System.out.println("futureTask callable sub thread");
//            return 100;
//        });
//
//        // 需要借助 thread 执行， futureTask 中的任务抛到一个线程中去执行
//        Thread thread1 = new Thread(futureTask);
//        thread1.start();
//
//        // 等待 featureTask 执行结束并返回结果，阻塞
//        Integer integer = futureTask.get();
//        System.out.println(integer);
//
//
//
//        Thread t = new Thread(() -> System.out.println("running"));
//        System.out.println(t.getState()); // NEW
//        t.start();
//        System.out.println(t.getState()); // RUNNABLE CPU 调度执行
//
//        test1();
//        test2();
//
//        Thread sleep = new Thread(() -> {
//            System.out.println("sleep");
//            try {
//                sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        sleep.start();
//        System.out.println("interrupt");
//        Thread.sleep(1000);
//        sleep.interrupt(); // 子线程被打断，主线程继续往下运行
//        System.out.println("打断标记"+ sleep.isInterrupted());
//
//        Thread thread2 = new Thread(() -> {
//            while (true) {
//                boolean interrupted = Thread.currentThread().isInterrupted();
//                if (interrupted) {
//                    break;
//                }
//            }
//        });
//
//        thread2.start();
//        Thread.sleep(1000);
//        thread2.interrupt();

        test2();
    }



    private static void test1() throws InterruptedException {
        System.out.println("开始 main");
        Thread thread = new Thread(() -> {
            System.out.println("开始 subThread");
            System.out.println("结束 subThread");
            r = 10;
        });

        thread.start();
        thread.join();

        System.out.println("main 结果为： " + r);
        System.out.println("main 结束");
    }

    private static void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = 10;
        },"thread");


        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = 20;
        },"thread2");

        long start = System.currentTimeMillis();
        thread.start();
        thread2.start();

        System.out.println("join begin");
        thread.join();
        System.out.println("t1 joined");
        thread2.join();
        System.out.println("t2 joined");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
