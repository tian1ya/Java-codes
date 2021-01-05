package designMode.kindsOfSinglonMode;

public class LazyMode {
    private static  LazyMode instance = null;
    public LazyMode() { }

    public static LazyMode getInstance() {
        /*
            这里会有线程安全问题，于是加锁，就是 SynchronizedLazyMode
         */
        if (instance == null)
            instance = new LazyMode();
        return instance;
    }
}
