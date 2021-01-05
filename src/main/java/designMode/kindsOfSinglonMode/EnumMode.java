package designMode.kindsOfSinglonMode;

/*
    effective java 作者极力推荐的单例实现模式，因为枚举类型是线程安全的，并且只会装载一次，
 */
public class EnumMode {
    public EnumMode() { }

    private enum Singleton {
        INSTANCE;

        private final EnumMode instance;

        Singleton() {
            instance = new EnumMode();
        }
    }

    public EnumMode getInstance() {
        return Singleton.INSTANCE.instance;
    }
}

