package proxy.Cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class Client {
    public static void main(String[] args) {
        // CGlib 动态代理获得代理对象的过程
        Enhancer enhancer = new Enhancer();

        /*
            设置 enhancer 对象的父类

            1. 首先将被代理类TargetObject设置成父类
         */
        enhancer.setSuperclass(HelloService.class);

        /*
            Enhancer 回调函数
            2. 设置拦截器TargetInterceptor
         */
        MyMethodInterceptor callback = new MyMethodInterceptor();
//        Callback noopCb= NoOp.INSTANCE; // 这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
//        Callback fixedValue=new TargetResultFixed();
//        Callback[] cbarray=new Callback[]{callback,noopCb,fixedValue};
//        enhancer.setCallbacks(cbarray);
//
//        enhancer.setCallbackFilter(new TargetMethodCallbackFilter());
        enhancer.setCallback(callback);
        /*
            创建代理对象，代理对象是目标对象的一个子类
            3. 执行enhancer.create()动态生成一个代理类
         */
        HelloService o = (HelloService)enhancer.create();

/*
         代理对象掉调用目标方法
         这里打断点，跳进去会跳到 MyMethodInterceptor.intercept 方法中
         然后可以使用前置后置增强方法了
 */
        o.sayHello();

        o.sayHi();

        o.sayOthers("nihao");
    }
}
