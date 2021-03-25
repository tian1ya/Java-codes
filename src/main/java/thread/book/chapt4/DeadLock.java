package thread.book.chapt4;

public class DeadLock {

    /*
        什么情况会发生死锁：
            交叉锁
            内存不足
            一答一问的数据交换
            数据库锁
            文件锁
            死循环引起的死锁
     */
    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WEITER = new Object();

    public void read(){
        synchronized (MUTEX_READ){
            System.out.println(Thread.currentThread().getName() + " + get read lock");
            synchronized (MUTEX_WEITER){
                System.out.println(Thread.currentThread().getName() + " + get write lock");
            }
            System.out.println("release write lock");
        }
        System.out.println("release read lock");
    }

    public void write(){
        synchronized (MUTEX_WEITER){
            System.out.println(Thread.currentThread().getName() + " + get write lock");
            synchronized (MUTEX_READ){
                System.out.println(Thread.currentThread().getName() + " + get read lock");
            }
            System.out.println("release read lock");
        }
        System.out.println("release read lock");
    }

    public static void main(String[] args) {
        System.out.println("Bootstrap" + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(DeadLock.class.getClassLoader());
    }

}
