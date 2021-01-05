package proxy.dynamic1.myCode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MeetInStreet implements InvocationHandler {
    public MeetInStreet(Object object) {
        this.object = object;
    }

    private Object object;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        r1();

        method.invoke(object, args);

        r2();
        return null;
    }

    public void r1() {
        System.out.println("加强方法1");
    }

    public void r2() {
        System.out.println("加强方法2");
    }
}
