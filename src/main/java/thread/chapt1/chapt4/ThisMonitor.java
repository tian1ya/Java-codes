package thread.chapt1.chapt4;

import java.util.concurrent.TimeUnit;

public class ThisMonitor {
    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName() + "enter method 1");
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

//    public synchronized void method2(){
//        System.out.println(Thread.currentThread().getName() + "enter method 2");
//        try{
//            TimeUnit.SECONDS.sleep(3);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }
    public  void method2(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "enter method 2");
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        /*
            这里执行的时候，执行线程1的时候，线程2会是出于 blocked 状态

            证明使用 synchronized 关键字同步类的不同实例方法，真枪的是同一个 Monitor 的lock，而与之关联的应用则是ThisMonitor 的实例引用

            同样的，如果method2使用另外一种方法，结果也是一样的，这也证明了

            使用 synchronized 修饰的同一个类的不同方法，这些方法在执行的过程中回去争强这个类的 this Monitor lock，也就是这些方法

            同一个时刻只会有一个方法在执行，其它的方法都是出于 Block 的状态

            同样的，

            如果将 method1 和 method2 都变为 static 的方法，那么二者也会有上面的现象，但是不同的是

            static 方法的 同步锁争的是 class monitor
         */
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1, "t1").start();
        new Thread(thisMonitor::method2, "t2").start();
    }
}
