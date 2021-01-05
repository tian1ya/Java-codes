package proxy.Cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/*
    JDK中的动态代理是通过反射类Proxy以及InvocationHandler回调接口实现的，但是，JDK中所要进行动态代理的类必须要实现一个接口，
    也就是说只能对该类所实现接口中定义的方法进行代理，这在实际编程中具有一定的局限性，而且使用反射的效率也并不是很高。

    使用CGLib实现动态代理，完全不受代理类必须实现接口的限制，而且CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，
    比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。

    CGLIB(Code Generation Library)是一个开源项目！是一个强大的，高性能，高质量的Code生成类库，
    它可以在运行期扩展Java类与实现Java接口

    cglib 是提供的方法级别的代理，也可以理解为对方法的拦截
    直接调用 proxy 的 invokeSuper 方法，将被代理的对象 obj 以及方法参数 args 传入其中即可
 */

public class MyMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o：cglib生成的代理对象
     * @param method: 被代理对象方法
     * @param objects：方法入参
     * @param methodProxy：代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======== 插入前置通知 =========");
        /*
            invoke方法调用的对象没有增强过，
            invokeSuper方法调用的对象已经是增强了的，所以会再走一遍 MyMethodInterceptor的 interceptor方法，如果是个拦截器链条，就会重新在走一次拦截器链；

            invokeSuper传入的参数是Cglib代理的子类 ，就相当于 调用 HelloService 中的方法
         */
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("======== 插入后置通知 =========");
        return result;
    }
}
