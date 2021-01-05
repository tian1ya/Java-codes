package thread.chapt1.chapt2;

import java.util.stream.IntStream;

public class ThreadName {

    public static void main(String[] args) {
        IntStream.range(0,5).boxed().map(i -> new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        )).forEach(Thread::start);

        // 推荐给每一个线程一个合适的名称

        // Thread 的所有构造函数，最终都会去调用一个静态方法 init，任何一个线程第一回有一个父线程
        // 如使用 方法 currentThread() 代表创建这个线程的线程，
        // 一个现成的创建肯定是由另外一个线程创建的
        // 被创建线程的浮现出是创建它的那个线程
        // 线程的名称：
        // 线程默认会有一个前缀为 『Thread-』的名称
    }
}
