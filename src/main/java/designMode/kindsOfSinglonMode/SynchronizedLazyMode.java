package designMode.kindsOfSinglonMode;

/*
    虽然加锁了，安全了，但是加锁比较是一个重的操作。可以使用静态内部类改善
 */
public class SynchronizedLazyMode {
    private static SynchronizedLazyMode instance = null;
    public SynchronizedLazyMode() { }

    public synchronized static SynchronizedLazyMode getInstance() {
        if (instance == null)
            instance = new SynchronizedLazyMode();
        return instance;
    }
}
