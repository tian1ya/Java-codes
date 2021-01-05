package proxy.dynamic1.example;

import java.lang.reflect.Proxy;

public class Test {
    
    public static void main(String[] args) {
        MaotaiJiu maotaiJiu = new MaotaiJiu();

        Wuliangye wuliangye = new Wuliangye();

        Furongwang furongwang = new Furongwang();

        GuitaiA guitaiA = new GuitaiA(maotaiJiu);
        GuitaiA guitaiA1 = new GuitaiA(wuliangye);

        GuitaiA guitaiA2 = new GuitaiA(furongwang);

        // 正是通过 Proxy 的静态方法 newProxyInstance 才会动态创建代理。
        // Proxy 动态产生的代理会调用 InvocationHandler 实现类

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
