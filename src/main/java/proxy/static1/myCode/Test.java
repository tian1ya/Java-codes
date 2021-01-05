package proxy.static1.myCode;

public class Test {

    /*
        https://segmentfault.com/a/1190000011291179
        这种代理需要代理对象和目标对象实现一样的接口
            缺点：冗余，代理对象和目标对象实现一致的接口，产生过多的代理类
                  不易维护，一带接口增加方法，目标对戏和代理对象需要同时进行修改
     */
    public static void main(String[] args) {

        Factory factory = new Factory();
        Dhangdian dhangdian = new Dhangdian(factory);
        dhangdian.sell();
    }
}
