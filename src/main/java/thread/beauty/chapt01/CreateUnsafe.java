package thread.beauty.chapt01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

public class CreateUnsafe {

    private static Unsafe unsafe;

    static {
        // 直接调用会报错，JVM 官方不建议直接调用 unsafe 方法的，
//        unsafe = Unsafe.getUnsafe();

        try {
            // 通过反射可以拿到
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final long stateOffset = 0L;

    private volatile long state = 0;

    public static void main(String[] args) throws NoSuchFieldException {

        CreateUnsafe unsafeTest = new CreateUnsafe();
        /**
         * 获取变量所属类的内存偏移地址
         */
        long offsetAddress = unsafe.objectFieldOffset(unsafeTest.getClass().getDeclaredField("state"));

        boolean sucess = unsafe.compareAndSwapInt(unsafeTest, offsetAddress, 0, 1);
        System.out.println(sucess);
        System.out.println(unsafeTest.state);

    }
}
