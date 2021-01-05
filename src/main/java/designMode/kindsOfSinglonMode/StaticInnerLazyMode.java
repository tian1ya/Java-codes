package designMode.kindsOfSinglonMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
    虽然加锁了，安全了，但是加锁比较是一个重的操作。可以使用静态内部类改善 StaticInnerLazyMode
 */
public class StaticInnerLazyMode {

    public StaticInnerLazyMode() {
        /*
            防止反射机制，破坏单例
         */
        if (LazyHolder.LAZY != null)
            throw new RuntimeException("不允许创建2个实例....");
    }

    public synchronized static StaticInnerLazyMode getInstance() {

        return LazyHolder.LAZY;
    }

    private static class LazyHolder {
        private static final StaticInnerLazyMode LAZY = new StaticInnerLazyMode();
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<StaticInnerLazyMode> clazz = StaticInnerLazyMode.class;
        Constructor<StaticInnerLazyMode> constructor = clazz.getDeclaredConstructor(null);

        // 强制访问
        constructor.setAccessible(true);

        /*
            强制初始化
            第二次初始化的时候，会跑出异常，因为在构造函数中对其进行了限制
         */
        StaticInnerLazyMode o1 = constructor.newInstance();
        StaticInnerLazyMode o2 = constructor.newInstance();

        System.out.println(o1 == o2);

    }
}
