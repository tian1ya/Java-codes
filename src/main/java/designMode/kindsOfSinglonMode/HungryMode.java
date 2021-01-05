package designMode.kindsOfSinglonMode;

/*
    有点：没有加任何锁，执行效率高，
    缺点：类加载的时候就初始化，就算不使用到它，还是会占这内存
 */
public class HungryMode {

    /*
        第一种写法： 直接定义
        public static final HungryMode instance = new HungryMode();
     */


    /*
      第二写法： 静态代码块中定义
   */
    public static final HungryMode instance;
    static {
        instance = new HungryMode();
    }

    public HungryMode() { }

    public static HungryMode getInstance() {
        return instance;
    }
}
