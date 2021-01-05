package proxy.Cglib;

import net.sf.cglib.proxy.FixedValue;

/*
    表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
    该类实现FixedValue接口，同时锁定回调值为999
 */
public class TargetResultFixed implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        System.out.println("锁定结果");
        return 999;
    }
}
