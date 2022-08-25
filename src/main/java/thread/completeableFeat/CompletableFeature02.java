package thread.completeableFeat;

import java.util.concurrent.*;

public class CompletableFeature02 {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            50,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        noReturnExample();
    }

    public static void noReturnExample() {

        CompletableFuture.runAsync(() -> {
            System.out.println("runAsync..." + Thread.currentThread().getName());
            System.out.println();
        }).handleAsync((res, exception) -> {
            System.out.println("whenCompleteAsync" + Thread.currentThread().getName());
            System.out.println("result==" + res + " exception==" + exception);
            return null;
        });


        System.out.println("supplyAsync");
        System.out.println();
        System.out.println();
        CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("supplyAsync..." + Thread.currentThread().getName());
                    int i = 1 / 0;
                    return 10;
                },
                executor
        ).whenCompleteAsync((result, exception) -> {
            // 获取前面的结果，以及异常信息，
            System.out.println("whenCompleteAsync" + Thread.currentThread().getName());
            System.out.println("result==" + result + " exception==" + exception);
        }).exceptionally(e -> {
            System.out.println("e==" +e);
            return 10;
        });

        System.out.println("主线程结束");
    }
}