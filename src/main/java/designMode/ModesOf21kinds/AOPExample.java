package designMode.ModesOf21kinds;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Subject {
    void doSomething(String str);
}

class RealSubject implements Subject {

    @Override
    public void doSomething(String str) {
        System.out.println("----" + str);
    }
}

class MyInvocationHandler implements InvocationHandler {
    // 被代理的对象
    private Object target = null;

    // 通过构造函数传递一个对象
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    // 代理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行代理方法
        return method.invoke(this.target, args);
    }
}

class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader,
                                       Class<?>[] interfaces,
                                       InvocationHandler h) {
        // 寻找JoinPoint 连接点，AOP 框架使用元数据定义
        if (true) {
            // 执行一个前置通知
            (new BeforeAdvice()).exec();
        }
        // 执行目标，并返回结果
        return (T) Proxy.newProxyInstance(loader, interfaces, h);
    }
}

class SubjectDynamicProxy extends DynamicProxy {
    public static <T> T newProxyInstance(Subject subject) {
        // 获得classLoader
        ClassLoader classLoader = subject.getClass().getClassLoader();

        // 获得接管数组
        Class<?>[] interfaces = subject.getClass().getInterfaces();

        //获得handler，接管被代理类方法的调用处理
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(subject);
        return newProxyInstance(classLoader, interfaces, myInvocationHandler);
    }
}

interface IAdvice {
    void exec();
}

class BeforeAdvice implements IAdvice {

    @Override
    public void exec() {
        System.out.println("我是前置通知，我被通知了");
    }
}


public class AOPExample {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
//        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
//
//        Subject proxy = DynamicProxy.newProxyInstance(realSubject.getClass().getClassLoader(),
//                realSubject.getClass().getInterfaces(), myInvocationHandler);

        Subject proxy = SubjectDynamicProxy.newProxyInstance(realSubject);
        proxy.doSomething("finish");

    }
}
