package annotation;

/**
 *  注解同class enum 一样，也是 Java5 之后引进来的一种类型
 *  将注解理解为标签，给事物的一种描述，或者理解为元数据，也就是类的元数据
 *
 *  元注解，也就是基本的注解类型
 *
 *  @Retention: 这个注解的存活时间，
 *      相当于给一张标签上面盖了一张时间戳，时间戳指明了标签张贴的时间周期。
 *
 *      RetentionPolicy.SOURCE 注解只在源码阶段保留，
 *          在编译器进行编译时它将被丢弃忽视。
 *      RetentionPolicy.CLASS 注解只被保留到编译进行的时候，
 *          它并不会被加载到 JVM 中。
 *      RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，
 *          它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 *
 *  @Documented 它的作用是能够将注解中的元素包含到 Javadoc 中去

    @Target：指定了注解运用的地方， 限定了运用的场景 如只能张贴到方法上、类上、方法参数上等等
        ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
        ElementType.CONSTRUCTOR 可以给构造方法进行注解
        ElementType.FIELD 可以给属性进行注解
        ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
        ElementType.METHOD 可以给方法进行注解
        ElementType.PACKAGE 可以给一个包进行注解
        ElementType.PARAMETER 可以给一个方法内的参数进行注解
        ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举


    @Inherited: Inherited 是继承的意思，但是它并不是说注解本身可以继承，
        而是说如果一个超类被 @Inherited 注解过的注解进行注解的话，
        那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
        Test2 中的例子

    @Repeatable: 可重复的意思 是 Java 1.8 才加进来的，所以算是一个新的特性。
        什么样的注解会多次应用呢？通常是注解的值可以同时取多个。
 */
public class note {
}
