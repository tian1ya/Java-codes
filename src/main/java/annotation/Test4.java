package annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/*
    在注解中定义属性时它的类型必须是 8 种基本数据类型外加 类、接口、注解及它们的数组。

    注解中属性可以有默认值，默认值需要用 default 关键值指定
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    int id() default -1;

    String msg() default "Hi";
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotationField {
    int id() default -1;

    String msg() default "Hi method";
}


// 默认值
@TestAnnotation()
class Test3{}

@TestAnnotation(id = 12, msg = "hello World")
public class Test4{

    @TestAnnotationField
    public void getA(){}

    public static void main(String[] args) {
        boolean isPresent =
                Test4.class.isAnnotationPresent(TestAnnotation.class);

        if (isPresent) {
//            获取所有的注解
//            Annotation[] annotations1 = Test4.class.getAnnotations();
            TestAnnotation annotations = Test4.class.getAnnotation(TestAnnotation.class);

            System.out.println(annotations.id());
            System.out.println(annotations.msg());

            // 方法上的注解

            try {
                Method getA = Test4.class.getDeclaredMethod("getA");
                getA.setAccessible(true);

                TestAnnotationField annotation = getA.getAnnotation(TestAnnotationField.class);
                System.out.println(annotation.id());
                System.out.println(annotation.msg());


            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


        }
    }
}
