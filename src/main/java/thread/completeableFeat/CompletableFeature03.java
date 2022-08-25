package thread.completeableFeat;

import java.util.concurrent.*;
import java.util.function.Function;

public class CompletableFeature03 {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            50,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        noReturnExample6();
    }

    public static void noReturnExample() {

        // 2个线程有先后执行顺序，111先跑，222后跑，且222线程获取不到111线程的结果
        CompletableFuture<Void> feature = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync..111..." + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }, executor).thenRunAsync(() -> {
            System.out.println("runAsync..222..." + Thread.currentThread().getName());
        }, executor);

        System.out.println("主线程结束");
    }

    public static void noReturnExample2() {

        // 2个线程有先后执行顺序，111先跑，222后跑，且222线程可以获取111线程的结果
        CompletableFuture<Void> feature = CompletableFuture.supplyAsync(() -> {
            System.out.println("runAsync..111..." + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            return 100;
        }, executor).thenAcceptAsync((t) -> {
            System.out.println("runAsync..222..." + Thread.currentThread().getName() + "res from 111 " + t);
        }, executor);

        System.out.println("主线程结束");
    }

    public static void noReturnExample3() {

        // 2个线程有先后执行顺序，111先跑，222后跑，且222线程可以获取111线程的结果


        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("runAsync..111..." + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            return 100;
        }, executor).thenApplyAsync(t -> {
            System.out.println("runAsync..222..." + Thread.currentThread().getName() + "res from 111 " + t);
            return t * 2;
        }, executor);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }

    /**
     * 2 个任务都完成
     */
    public static void noReturnExample4() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync..111..." + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync..222..." + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 100;
        }, executor);

        // future，future2 2个都执行结束之后，执行后续的Runnable
        future.runAfterBothAsync(future2, () -> {
            System.out.println("runAfterBothAsync..end..." + Thread.currentThread().getName());
        }, executor);

        // future，future2 2个都执行结束之后，执行后续的 BiConsumer，可以获取2个Future 的返回
        future.thenAcceptBothAsync(future2, (futureRes, future2Res) -> {
            System.out.println("thenAcceptBothAsync=" + " futureRes=" + futureRes + " future2Res=" + future2Res);
        });

        // future，future2 2个都执行结束之后，执行后续的 BiFunction，可以获取2个Future 的返回
        // 以及可以返回一个最终结果

        CompletableFuture<Integer> combineAsync = future.thenCombineAsync(future2, (f1, f2) -> {
            System.out.println("f1=" + f1);
            System.out.println("f2=" + f2);
            return f1 + f2;
        }, executor);

        System.out.println(combineAsync.get());

        System.out.println("主线程结束");
    }

    /**
     * 2个任务中有一个完成
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void noReturnExample5() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync..111..." + Thread.currentThread().getName());
            return 10;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync..222..." + Thread.currentThread().getName());
            return 20;

        }, executor);

        // future，future2 2个执行完成一个之后结束之后，就执行后续的Runnable
        future.runAfterEitherAsync(future2, () -> {
            System.out.println("runAfterEitherAsync..end..." + Thread.currentThread().getName());
        }, executor);

        // future，future2 2个都执行结束之后，执行后续的 Consumer，可以获取执行玩的那个 Future 的结果
        future.acceptEitherAsync(future2, (featureOr2Result) -> {
            System.out.println("acceptEitherAsync..end..." + Thread.currentThread().getName());
            System.out.println("featureOr2Result= " + featureOr2Result);

        });

        // future，future2 2个都执行结束之后，执行后续的 BiFunction，可以获完成的Future 的返回
        // 以及可以返回一个最终结果

        CompletableFuture<Integer> combineAsync = future.applyToEitherAsync(future2, (f1) -> {
            System.out.println("f1=" + f1);
            return f1 * 2;
        }, executor);
//
        System.out.println(combineAsync.get());

        System.out.println("主线程结束");
    }

    /**
     * 多任务组合处理
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void noReturnExample6() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync..111..." + Thread.currentThread().getName());
            return 10;
        }, executor);

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync..333..." + Thread.currentThread().getName());
            return 20;

        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync..222..." + Thread.currentThread().getName());
            return 20;

        }, executor);


        CompletableFuture<Void> allOf = CompletableFuture.allOf(future, future2, future3);
        // 3个全部执行完成，才会去做后续的处理

        System.out.println(allOf.get() + " f1= " + future.get() + " f2=" + future2.get() + " f3=" + future3.get());

        CompletableFuture<Object> future1 = CompletableFuture.anyOf(future, future2, future3);
        // 3个有一个执行完成，就去做后续的处理

        System.out.println(future1.get());
        System.out.println("主线程结束");
    }


}