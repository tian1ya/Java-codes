package annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Test12{}


@Test12
class Test13 {
}

/*
    Test2 拥有了Test13注解的内容

    老子非常有钱，所以人们给他贴了一张标签叫做富豪。
    老子的儿子长大后，只要没有和老子断绝父子关系，虽然别人没有给他贴标签，但是他自然也是富豪。
 */
public class Test2 extends Test13{
}
