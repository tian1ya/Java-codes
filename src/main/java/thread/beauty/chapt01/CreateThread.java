package thread.beauty.chapt01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThread {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Run Thread");
        }
    }

    public static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Runnable Task");
        }
    }

    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
//        MyThread thread2 = new MyThread();
//        thread.start();

        RunnableTask runnableTask = new RunnableTask();
//        runnableTask.run();

        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();

        String s;
        try {
            s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
