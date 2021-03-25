package thread.book.executors;

import java.util.concurrent.*;

public class ExecutorsTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        CallableDemo callableDemo = new CallableDemo();

        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);

        singleThreadExecutor.submit(futureTask);

        singleThreadExecutor.shutdown();

        try {
            Thread.sleep(2000);
            System.out.println("主线程在做其他的事情");

            // 这里会阻塞等待 future get 结果
            if (futureTask.get() != null) {
                System.out.println("feature.get(): ==> " + futureTask.get());
            }else {
                System.out.println("feature.get(): ==> " + null);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("主线程事情做完了");
    }
}

//class MyThread implements Runnable{
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName() + " is running...");
//    }
//}

class MyThread implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}

class CallableDemo implements Callable<Integer> {

    private Integer sum;

    public CallableDemo() {
        this.sum = 0;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable 开始计算了");
        Thread.sleep(2000);
        for (int i = 0; i < 200; i++) {
            sum = sum + 1;
        }
        return sum;
    }
}