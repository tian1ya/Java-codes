package proxy.Cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class TargetMethodCallbackFilter implements CallbackFilter {
    /*
        返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
     */
    @Override
    public int accept(Method method) {
        if (method.getName().equals("sayOthers")){
            System.out.println("method name is sayOthers");
            return 0;
        }
        if (method.getName().equals("sayHello")){
            System.out.println("method name is sayHello");
            return 1;
        }
        if (method.getName().equals("sayHi")){
            System.out.println("method name is sayHi");
            return 2;
        }
        return 0;
    }
}
