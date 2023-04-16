package proxy.dynamic1.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GuitaiA implements InvocationHandler {

    private Object pingpai;

    /**
     *
     * @param pingpai 被代理对象，被代理对象需要实现的接口，和Proxy.newInstance 中给的接口需是一样的
     */
    public GuitaiA(Object pingpai) {
        this.pingpai = pingpai;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         *  这个方法决定了怎么样处理代理传递过来的方法调用。
         *  proxy 代理对象
         *  method 代理对象调用的方法
         *  args 调用的方法中的参数
         *
         *
         */
        String name = this.getClass().getSimpleName();

        System.out.println("销售开始  柜台是： "+ name);
        method.invoke(pingpai, args);
        System.out.println("销售结束");

        return null;
    }
}
