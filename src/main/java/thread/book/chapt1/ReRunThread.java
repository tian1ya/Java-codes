package thread.book.chapt1;

import java.util.concurrent.TimeUnit;

public class ReRunThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //  thread.start();
        // thread.start();
        // 抛出异常： IllegalThreadStateException
        // 一个线程是不能够同时启动两次的

        //thread.start();
        //TimeUnit.SECONDS.sleep(2);
        //thread.start();
        // 这里也会报错，中间停几分钟，就是为了上第一次start 执行结果
        // 也就是说，第一次执行结束之后，进入到 TERMINATED 状态
        // 就不能再 start 了。

        /*
            线程中真正的执行者是在 run 方法之中，run 方法称为是线程的执行单元
         */


//        String str = String.format("%16d", 120).replace(" ", "0");
//        System.out.println(str);
//
//        System.out.println(" " == "");

    }
}
