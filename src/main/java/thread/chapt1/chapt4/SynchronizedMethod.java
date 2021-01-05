package thread.chapt1.chapt4;

import thread.chapt1.chapt1.AnExampleTicketWindow;

import java.util.Objects;

public class SynchronizedMethod implements Runnable{
    /*
        防止线程干扰和内存一致性错误，
            它提供了一种锁的机制，，能够确保共享变量的互斥访问，从而防止数据不一致的问题。

            可以是对代码块或者方法进行修饰，不能够对class和变量进行修饰

            还是使用之前 『叫号的例子来说明』程序无论运行多少次，都不会出现数据不质疑的问题

            提供了一种互斥机制，也就是说在同一个时刻，只能有一个线程访问同步资源

            在下面的例子中，定义的三个线程同一个时刻只有一个线程能够访问，而其它的程序都是出于阻塞状态。
     */



        private int index = 1;

        private final static int MAX = 500;

        private final static Object MUTEX = new Object();


        @Override
        public void run() {
            synchronized (MUTEX){
                while (index <= MAX) {
                    System.out.println("柜台:" + Thread.currentThread() + "当前提取号码是: " + (index++));
                }
            }
        }



    public static void main(String[] args) {

        SynchronizedMethod t = new SynchronizedMethod();
        Thread t1 = new Thread(t, "一号柜台");
        Thread t2 = new Thread(t, "二号柜台");
        Thread t3 = new Thread(t, "三号柜台");

        t1.start();
        t2.start();
        t3.start();
    }
}

/*
    以下是使用 sycriznized 方法的几点需要注意的地方
 */

/**
 * 关联的对象不能为空
 */

//class NoNull implements Runnable{

//    @Override
//    public void run() {
//        synchronized (){ // 出错
//            System.out.println("methods");
//        }
//    }
//}

/*
    作用域不能太大,对线程中的整个运行单元都进行同步锁控制
 */

class TooBigZone implements Runnable{

    @Override
    public  void run() {
        System.out.println("methods");
    }
}

/*
      锁多个对象
 */
class ManyObj implements Runnable{

    private final static Object MUTEX = new Object();

    @Override
    public synchronized void run() {
        synchronized (MUTEX){
            System.out.println("methods");
        }
    }

    /*
        炸一看下面的方法是没有错误的
        但是这里多个线程开启了多个  Runnable 实例
        也就是 synchronized 锁了5个
     */
    public  void test(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(ManyObj::new).start();
        }
    }
}