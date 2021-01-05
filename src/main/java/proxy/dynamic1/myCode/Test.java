package proxy.dynamic1.myCode;

import java.lang.reflect.Proxy;

public class Test {
    /*
        https://segmentfault.com/a/1190000011291179
        动态代理，动态的在内存中构建代理对象，从而实现对目标对象的代理功能

        它不需要实现接口，但是要求目标对戏必须实现接口，否则不能使用动态代理
     */

    public static void main(String[] args) {
        Dog dog = new Dog();

        People people = new People();

        /*
            1. 首先实现一个InvocationHandler，方法调用会被转发到该类的invoke()方法。

             和静态代理的不同，这里 MeetInStreet 和 Dog 并没有实现相同的接口
         */
        MeetInStreet dogMeetInStreet = new MeetInStreet(dog);
        MeetInStreet peopleMeetInStreet = new MeetInStreet(people);

        /*
            Proxy.newProxyInstance: 实现了指定接口的代理对象
                该方法会根据指定的参数动态创建代理对象。三个参数的意义如下：
                loader，指定代理对象的类加载器；
                interfaces，代理对象需要实现的接口，可以同时指定多个接口；
                handler，方法调用的实际处理者，代理对象的方法调用都会转发到这里（*注意1）。
         */
        Greeting dogGreeting = (Greeting) Proxy.newProxyInstance(Dog.class.getClassLoader(), Dog.class.getInterfaces(), dogMeetInStreet);

        Greeting peopleGreeting = (Greeting) Proxy.newProxyInstance(Dog.class.getClassLoader(), Dog.class.getInterfaces(), peopleMeetInStreet);

        dogGreeting.say();
        System.out.println("\n");
        peopleGreeting.say();
    }
}
