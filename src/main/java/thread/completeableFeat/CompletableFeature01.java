package thread.completeableFeat;

import java.util.concurrent.*;

public class CompletableFeature01 {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            50,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        hasReturnExample();
    }

    public void noReturnExample() {
        /**
         * 没有返回结果
         */
        System.out.println("主线程开始");

        CompletableFuture.runAsync(() -> {
            System.out.println("没有返回结果子线程执行..." + Thread.currentThread().getName());
        }, executor);

        System.out.println("主线程结束");
    }

    public static void hasReturnExample() throws ExecutionException, InterruptedException {
        /**
         * 有返回结果
         */
        System.out.println("主线程开始");

        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回结果子线程执行..." + Thread.currentThread().getName());
            return 10;
        }, executor);

        /**
         * 这里阻塞主线程，直到获取结果后，主线程继续
         */
        System.out.println("返回结果" + supplyAsync.get());
        System.out.println("主线程结束");
    }
}
