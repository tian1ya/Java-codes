//package Java.JavaReflection;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//public class reflact {
//
//    public static void demo01(){
//        /*
//            获取反射对象(反射入口)
//            1. Class.forName()
//            2. xx.class
//            3. 对象.getClasss
//         */
//
//        try {
//            // 1.   Class.forName(全类名)
//            Class<?> aClass = Class.forName("Java.JavaReflection.Person");
//            System.out.println(aClass);
//
//            // 2. 类名.class
//            Class<Person> personClass = Person.class;
//            System.out.println(personClass);
//
//            // 3. 对象.getClass
//            Person person = new Person();
//            Class<? extends Person> aClass1 = person.getClass(); // getClass() 方法来自于 Objects
//            System.out.println(aClass1);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /*
//            获取方法
//     */
//    public static void demo2() {
//
//
//          // 入口
//        Class<?> aClass = null;
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // 获取所有公共方法，public 定义的方法
//        Method[] methods = aClass.getMethods();
//        for (Method method:methods) {
//            System.out.println(method);
//        }
//        /*
//            定义的方法   public String getName()
//            获取的方法： public java.lang.String Java.JavaReflection.Person.getName()
//                        获取的全部都是全路径
//
//            获取到的 public 方法，还包括当前父类、接口的方法，
//         */
//
//        // 获取当前类的所有方法public 和 private，只能是当前类(不包括父类)，忽略访问修饰符限制
//        Method[] declaredMethod = aClass.getDeclaredMethods();
//
//        for (Method m: declaredMethod){
//            System.out.println(m);
//        }
//    }
//
//    /*
//        获取当前类所有的接口
//     */
//
//    public static void demo3(){
//        Class<?> aClass = null;
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Class<?>[] interfaces = aClass.getInterfaces();
//        for (Class i :interfaces) {
//            System.out.println(i);
//        }
//    }
//
//    /*
//        获取所有的父类
//     */
//
//    public static void demo4(){
//        Class<?> aClass = null;
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Class<?> superclass = aClass.getSuperclass();
//        System.out.println(superclass);
//    }
//
//    /*
//        获取构造方法
//
//     */
//
//    public static void demo5(){
//        Class<?> aClass=null;
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Constructor<?>[] constructors = aClass.getConstructors();
//        for (Constructor c : constructors) {
//            System.out.println(c);
//        }
//    }
//
//    /*
//        获取所有的公共 public 属性
//     */
//    public static void demo6(){
//        Class<?> aClass=null;
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // public shuxing
//        Field[] fields = aClass.getFields();
//        for (Field f: fields){
//            System.out.println(f);
//        }
//
//        // 获取所有的属性 public 和 private
//        Field[] declaredFields = aClass.getDeclaredFields();
//        for (Field f: declaredFields) {
//            System.out.println(f);
//        }
//
//    }
//
//    /*
//        获取对象的实例，并操作对象
//     */
//
//    public static void demo7(){
//        Class<?> aClass=null;
//
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Person per = null;
//        try {
//            per = (Person) aClass.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        per.setAge(23);
//        per.setId(2);
//        per.setName("ss");
//        System.out.println(per.getAge());
//    }
//
//    // 反射操作属性,方法
//    public static void demo8() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Class<?> aClass=null;
//
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Person per = null;
//        try {
//            per = (Person) aClass.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        Field id = aClass.getDeclaredField("id");
//        // 这里id 字段是私有的，虽然能够获取这个字段，但是并不是可以修饰的，如果需要修改
//        // 那么久需要修改访问权限
//
//        System.out.println("@@@ " + String.class.getTypeName());
//        System.out.println("@@@ " + id.getAnnotatedType());
//        System.out.println("@@@ " + id.getAnnotatedType().getType().getTypeName());
//        System.out.println("@@@ " + id.getAnnotatedType().getType().getTypeName());
//
//        id.setAccessible(true);
//        id.set(per, 2);
//        System.out.println(per.getId());
//
//        System.out.println("====================");
//
//        Method privateMethod = aClass.getDeclaredMethod("privateMethod", null);
//        privateMethod.setAccessible(true);
//
//        // 方法调用
//        privateMethod.invoke(per, null);
//
//        Method privateMethod2 = aClass.getDeclaredMethod("privateMethod2", Long.class);
//        privateMethod2.setAccessible(true);
//
//        // 方法调用
//        privateMethod2.invoke(per, 12L);;
//
//    }
//
//    // 操作构造方法
//    public static void demo9() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Class<?> aClass=null;
//
//        try {
//            aClass = Class.forName("Java.JavaReflection.Person");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // 只能拿到全部的public 构造方法
//        Constructor<?>[] constructors = aClass.getConstructors();
//        for (Constructor c: constructors){
//            System.out.println(c);
//        }
//
//        // 拿到全部的构造方法，包括 private 的
//        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
//        for (Constructor c: declaredConstructors){
//            System.out.println(c);
//        }
//
//        // 获取指定的构造方法,需要时public 的方法
//        // 再反射中，根据类型获取方法时候，基本类型 int char 等和包装类型(Integer charset)是不通的
//        Constructor<?> constructor = aClass.getConstructor(int.class);
//        System.out.println(constructor);
//
//        // 是有的构造器需要使用 getDeclaredConstructor 方法获取
//        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
//        System.out.println(declaredConstructor);
//
//        // 构造方法可以获取对象,这里可以给参数，这个参数就是给到了 构造方法中的那个值
//        Person person = (Person)constructor.newInstance(12);
//        System.out.println(person.getId());
//
//
//        // 无惨书构造器
//        Constructor<?> nonarg = aClass.getDeclaredConstructor();
//        System.out.println(nonarg);
//        Person personnonarg = (Person)nonarg.newInstance();
//
//        /*
//            上面使用构造函数创建对象的时候，给到的参数一定是和获取构造函数的时候写入的 参数类型是一致的
//            获取无参数的构造器，那么使用这个构造器创建对象就是不需要参数的
//
//            如果构造器的获取是带有一个参数的，那么使用这个构造器创建对象的时候，就是需要相对应的参数
//         */
//
//
//    }
//
//    //动态加载类名和方法
//    public static void demo10() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
//        Properties properties = new Properties();
//        properties.load(new FileReader("/Users/xuxliu/Ifoods/Java/leetcode/src/thread.book.main/java/Java/JavaReflection/class.txt"));
//
//        // 文件里面写的是谁，那么久动态加载谁,修改读取文件中的配置，就可以直接调用对应的方法，对象
//        String classname = properties.getProperty("classname");
//        String methodname = properties.getProperty("methodname");
//        Class<?> aClass=null;
//
//        try {
//            aClass = Class.forName(classname);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Method method = aClass.getMethod(methodname);
//
//        method.invoke(aClass.newInstance());
//    }
//
//    //反射可以越过泛型检查
//    public static void demo05(){
//        List<Integer> list = new ArrayList();
//        // List<Integer> 指定类型之后
//        list.add(2);
//
//
//
//    }
//
//
//    public static void thread.book.main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
//        demo8();
//
//    }
//}
