package thread.chapt1.chapt1;

//public class AnExampleTicketWindow extends Thread {
//
//    private final String name;
//    private static int index = 1;
//
//    private final static int MAX = 50;
//
//    public AnExampleTicketWindow(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public void run() {
//        while (index <= MAX) {
//            System.out.println("柜台:" + name + "当前提取号码是: " + (index++));
//        }
//    }
//
//    public static void main(String[] args) {
//        AnExampleTicketWindow t1 = new AnExampleTicketWindow("一号出号机");
//        t1.start();
//
//        AnExampleTicketWindow t2 = new AnExampleTicketWindow("二号出号机");
//        t2.start();
//
//        AnExampleTicketWindow t3 = new AnExampleTicketWindow("三号出号机");
//        t3.start();
//    }
//}

    /*
        上述的代码都三个线程都是从1号开始呼号码，而不是三个线程都从1-50中取一个号码，不会重复，这里首先想到的解决方法是，将 index 变成多个线程共享的一个变量
        这样每次每个线程就不会只从1开始哪，

        将 jindex 变为 static 的，这样多个线程下共享唯一的资源，这里MAX 只有 50 个值，看着似乎是没有什么问题，但是将 它增加越来越大，那么
        就会出现重复的问题，

        Java 提供另外一个接口 Runnable 将线程的控制盒业务逻辑的运行彻底分开

        Runnable是一个接口，只定义了一个 返回是 void 的 run() 方法

        很多地方看到创建线程有两种方法，
        1. 使用 Thread
        2. 使用 Runnable

        这种说法是错误的，不严谨的，JDK 重代表线程的只有Thread 接口这个类，

       更加准确的是，创建线程只有一种方式，那就是构造Thread 类，而实现是有两种方式，
        1. 重写 Thread 类的 run 方法
        2. 实现 Runnable 接口的 run 方法

        重写Thread 的 run 方法和实现 Runnable 接口的 run 方法还有一个很重要的不同，那就是
        Thread 类的 run 方法是不能共享的，也就是 A 线程不能吧B 线程的 run 方法当做自己的执行单元，
        但是使用 Runnable 就可以很简单的实现这一点

        下面的代码中 线程中的资源就是共享的，使用 Runnable 接口的方式，程序运行几次或者Max 值不管增加到多少
        一个号码，都不会出现每一个机器都是从 1 叫到 50

        但是以上的三种情况，都会有问题，都不能保证一个号码都被唯一的叫到

        因为存在线程安全的问题

     */
    public class AnExampleTicketWindow implements Runnable {

        private int index = 1;

        private final static int MAX = 50;


        @Override
        public void run() {
            while (index <= MAX) {
                System.out.println("柜台:" + Thread.currentThread() + "当前提取号码是: " + (index++));
            }
        }

        public static void main(String[] args) {

            AnExampleTicketWindow t = new AnExampleTicketWindow();
            Thread t1 = new Thread(t, "一号柜台");
            Thread t2 = new Thread(t, "二号柜台");
            Thread t3 = new Thread(t, "三号柜台");

            t1.start();
            t2.start();
            t3.start();
        }
    }