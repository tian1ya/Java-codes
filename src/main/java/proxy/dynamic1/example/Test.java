package proxy.dynamic1.example;

import java.lang.reflect.Proxy;

public class Test {
    
    public static void main(String[] args) {
        MaotaiJiu maotaiJiu = new MaotaiJiu();

        Wuliangye wuliangye = new Wuliangye();

        Furongwang furongwang = new Furongwang();

        /**
         * GuitaiA 和  maotaiJiu 不用实现相同的接口了
         */
        GuitaiA guitaiA = new GuitaiA(maotaiJiu);
        GuitaiA guitaiA1 = new GuitaiA(wuliangye);

        GuitaiA guitaiA2 = new GuitaiA(furongwang);

        // 正是通过 Proxy 的静态方法 newProxyInstance 才会动态创建代理。
        // Proxy 动态产生的代理会调用 InvocationHandler 实现类
        /*
            loader: the class loader to define the proxy class
            interfaces: the list of interfaces for the proxy class
                to implement，代表动态代理类将会实现的抽象接口，此接口是被委托类所实现的接口

            h: the invocation handler to dispatch method invocations to，代理类调用的处理器

            方法在运行阶段获取JDK生成的动态代理类的实例。注意，这一步获取的是对象而不是类。
            该方法需要三个参数，其中的第一个参数为类装载器，第二个参数为抽象接口的class对象，第三个参数为调用处理器对象。

            第二个参数和第三个参数中属性类需一样的接口方法
         */

        SellWine dynamicProxy = (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(),
                MaotaiJiu.class.getInterfaces(), guitaiA);

        // guitaiA 类中需要有一个 invoke 方法，在这个方法中调用代理累的方法，并且去定义增加的一些方法


        SellWine dynamicProxy1 = (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(),
                MaotaiJiu.class.getInterfaces(), guitaiA1);

        // 同样是通过 Proxy.newProxyInstance() 方法，却产生了 SellWine 和 SellCigarette 两种接口的实现类代理
        SellCigarette dynamicProxy2 = (SellCigarette) Proxy.newProxyInstance(Furongwang.class.getClassLoader(),
                Furongwang.class.getInterfaces(), guitaiA2);


        /*
            主要作用，还是在不修改被代理对象的源码上，进行功能的增强。
                这在 AOP 面向切面编程领域经常见。
         */
        dynamicProxy.mainJiu();
        dynamicProxy1.mainJiu();
        dynamicProxy2.sell();
    }
}
