package thread.book.chapt2;

public class ThreadInheritance {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1");

        ThreadGroup group = new ThreadGroup("TestGroup");

        Thread t2 = new Thread(group, "t2");

        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        System.out.println("thread.book.main thread belong group: " + mainThreadGroup.getName());
        System.out.println("t1 thread  group: " + t1.getThreadGroup().getName());
        System.out.println("t2 thread  group: " + t2.getThreadGroup().getName());
        System.out.println("t1 thread group belong to thread.book.main thread group: " + (mainThreadGroup.getName() == t1.getThreadGroup().getName()));
        System.out.println("t2 thread group belong to thread.book.main thread group: " + (mainThreadGroup.getName() == t2.getThreadGroup().getName()));

    }
    /*
       在创建 线程的时候，如果没有指定一个线程组，那么创建的线程组就是这个线程父线程所在的组

       如果显示的给创建的线程指定了线程组，那么就是属于这个线程组的

       Thread 负责线程本身相关的职责喝控制，而 Runnable 负责逻辑执行单元的部分

     */

}
