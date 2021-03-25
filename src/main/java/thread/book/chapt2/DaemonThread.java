package thread.book.chapt2;


class MyThread implements Runnable{ // 实现Runnable接口
    public void run(){  // 覆写run()方法
        for(int i=0;i<5;i++){
            try{
                Thread.sleep(500) ;
            }catch(Exception e){
            }
            System.out.println(Thread.currentThread().getName());
            if(i==2){
                Thread.currentThread().yield() ;
                // 线程礼让
            }
//            System.out.println(Thread.currentThread().getName());

            System.out.println();
        }
    }
};


class MyThread2 implements Runnable{
    public void run(){  // 覆写run()方法
        System.out.println(Thread.currentThread().getName()) ;
    }
};


public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {


        MyThread2 mt = new MyThread2() ;
            // 实例化Runnable子类对象

            Thread t = new Thread(mt,"线程");
            // 实例化Thread对象

            t.start() ;
            // 启动线程

            try{
                t.join() ;
                // 线程强制运行
            }catch(InterruptedException e){
            }

        System.out.println("main线程");



//        MyThread my = new MyThread() ;  // 实例化MyThread对象
//        Thread t1 = new Thread(my,"线程A") ;
//        Thread t2 = new Thread(my,"线程B") ;
//        t1.start() ;
//        t2.start() ;



//        Thread t1 = new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        t1.setDaemon(true);
//
//        t1.start();
//        Thread.sleep(2_000L);
//        System.out.println("thread.book.main thread termined");
//    }

    /*
        如果 t1.setDaemon(true); 是注释状态，那么当 run 程序的时候，程序是永远不会中断的
        这事一个正常程序(user thread)，只有当所有的正常程序都结束的时候 Java 进程才会结束

        但是如果将 t1.setDaemon(true);  注释打开，那么程序就会结束的，也就是 JVM 线程会退出
        所以说JVM 进程的退出是在 没有正常线程之后才会退出。

        这就是守护线程

        所谓守护线程就是：为正常线程 User Thread 服务的一个线程，可以理解为这个线程是在后台运行的，JVM 进行的运行或者
        退出是不会考虑是否有守护线程的，如垃圾回收机制。也可以理解为 服务线程，在没有用户线程可服务时会自动离开。

        守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。

        通过setDaemon(true)来设置线程为“守护线程”；将一个用户线程设置为
        守护线程的方式是在 线程对象创建 之前 用线程对象的setDaemon方法。
        但是这个设置必须是在 线程 start 之前

        守护进程（Daemon）是运行在后台的一种特殊进程。它独立于控制终端并且
        周期性地执行某种任务或等待处理某些发生的事件。也就是
        说守护线程不依赖于终端，但是依赖于系统，与系统“同生共死”。那Java的守护线程是
        什么样子的呢。当JVM中所有的线程都是守护线程的时候(没有正常线程User thread)，JVM就可以退出了；如果还有一个
        或以上的非守护线程则JVM不会退出。

     */

    }
}
