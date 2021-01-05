//package designMode.ModesOf21kinds;
//
//
//import java.lang.reflect.Constructor;
//import java.util.HashMap;
//import java.util.Map;
//
//interface Human {
//    void getColor();
//    void talk();
//}
//
//
//class BlackHuman implements Human {
//
//    @Override
//    public void getColor() {
//        System.out.println("黑种人的皮肤是黑色的");
//    }
//
//    @Override
//    public void talk() {
//        System.out.println("黑种人说话，一般人听不懂");
//    }
//}
//
//class WhiteMan implements Human {
//
//    @Override
//    public void getColor() {
//        System.out.println("白种人的皮肤是白色的");
//    }
//
//    @Override
//    public void talk() {
//        System.out.println("白种人说话，一般人听不懂");
//    }
//}
//
//class YellowMan implements Human {
//
//    @Override
//    public void getColor() {
//        System.out.println("黄种人的皮肤是黄色的");
//    }
//
//    @Override
//    public void talk() {
//        System.out.println("黄种人说话，一般人听不懂");
//    }
//}
//
//abstract class AbstractHumanFactory {
//    public abstract Human createHuman();
//}
//
//class BlackHuamnFactory extends AbstractHumanFactory {
//    @Override
//    public Human createHuman() {
//        return new BlackHuman();
//    }
//}
//
//class WhiteHuamnFactory extends AbstractHumanFactory {
//    @Override
//    public Human createHuman() {
//        return new WhiteMan();
//    }
//}
//
//class YellowHuamnFactory extends AbstractHumanFactory {
//    @Override
//    public Human createHuman() {
//        return new YellowMan();
//    }
//}
//
//class HumanFactoryV1 {
//    public static <T extends Human> T createHuman(Class<T> clazz) {
//
//        Human human = null;
//        try {
//            human = (T) Class.forName(clazz.getName()).newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (T) human;
//    }
//}
//
//
//class SingleTon {
//    private SingleTon() {
//    }
//
//    public void doSomething() {
//
//    }
//}
//
//class SingletonFactory {
//    private static Singleton singleton;
//
//    static {
//        try {
//            Class<?> cl = Class.forName(Singleton.class.getName());
//            Constructor<?> constructor = cl.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            singleton = (Singleton) constructor.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Singleton getSingleton() {
//        return singleton;
//    }
//}
//
//class ProductFactory {
//
//    // 定义一个容器，容纳所有的对象
//    private static final Map<String, Product> prMap = new HashMap();
//
//    public static synchronized Product createProduct(String type) {
//        Product product = null;
//
//        // 加载的时候,如果在容器中已经存在那么就将类取出使用，否则新建，然后将新建的类放入到容器中
//        if (prMap.containsKey(type)) {
//            product = prMap.get(type);
//        }else {
//            if ("Product1".equals(type)) {
//                product = new ConcreteProduct1();
//            }else {
//                product = new ConcreteProduct2();
//            }
//            prMap.put(type, product);
//        }
//        return product;
//    }
//}
//
//
//public class FactoryMode {
//
//    public static void main(String[] args) {
////        HumanFactory humanFactory = new HumanFactory();
//        BlackHuman blackHuman = HumanFactoryV1.createHuman(BlackHuman.class);
//
//        blackHuman.getColor();
//        blackHuman.talk();
//
//        YellowMan yellowHuman = HumanFactoryV1.createHuman(YellowMan.class);
//
//        yellowHuman.getColor();
//        yellowHuman.talk();
//
////        ConcreteCreator concreteCreator = new ConcreteCreator();
////        ConcretProcut1 product = concreteCreator.createProduct(ConcretProcut1.class);
//    }
//}
//
//// 抽象产品类
//abstract class Product {
//    // 产品类的公共方法
//    public void method1() {
//       // 业务逻辑处理
//    }
//
//    // 抽象方法
//    public abstract void method2();
//}
//
//// 具体的产品类
//class ConcreteProduct1 extends Product {
//    @Override
//    public void method1() {
//        // 业务逻辑处理
//    }
//
//    @Override
//    public void method2() {
//        // 业务逻辑处理
//    }
//}
//
//// 具体的产品类
//class ConcreteProduct2 extends Product {
//    @Override
//    public void method1() {
//        // 业务逻辑处理
//    }
//
//    @Override
//    public void method2() {
//        // 业务逻辑处理
//    }
//}
//
//// 抽象工厂类
//abstract class Creator {
//    public abstract <T extends Product> T createProduct(Class<T> clazz);
//}
//
//class ConcreteCreator extends Creator {
//
//    @Override
//    public <T extends Product> T createProduct(Class<T> clazz) {
//        T product = null;
//        try {
//            product = (T) Class.forName(clazz.getName()).newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return product;
//    }
//}
