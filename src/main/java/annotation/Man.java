package annotation;

import java.lang.annotation.Repeatable;
/*
    而 @Repeatable 后面括号中的类相当于一个容器注解。
    什么是容器注解呢？就是用来存放其它注解的地方。它本身也是一个注解。
 */
@Repeatable(Persons.class)
@interface Person{
    String role() default "";
}

@interface Persons{
    Person[] value();
}

@Person(role = "artist")
@Person(role = "coder")
public class Man {
}
