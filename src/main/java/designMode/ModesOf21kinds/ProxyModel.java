package designMode.ModesOf21kinds;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IGamePlayer {
    void login(String user, String password);
    void killBoss();
    void  upgrade();
}

interface IProxy {
    void count();
}


class GamePlayIH implements InvocationHandler {

    // 被代理者
    Class cls = null;

    // 被代理的实例
    Object obj = null;

    public GamePlayIH(Object obj) {
        this.obj = obj;
    }


    /*
        完成对真实方法的调用，动态代理是根据被代理的接口生成所有的方法，
        也就是说给定一个接口，动态代理会宣城，我已经实现该接口下的所有方法了

        那么动态代理是如何知道被代理接口中的方法呢，默认情况下所有的方法的返回值都是空的

        是的，代理已经实现它了，但是没有任何的逻辑含义，

        所有的方法都是由该Handler 来进行处理，即所有被代理的方法都是由 Handler 来进行处理

        即所有被代理的方法都是由 InvocationHandler 接管实际的处理任务
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(this.obj, args);
        if (method.getName().equalsIgnoreCase("login")) {
            System.out.println("有人在用我的账号登录");
        }
        return invoke;
    }
}


class GamePalyer implements IGamePlayer {
    private String name = "";

    public GamePalyer(String name) {
        this.name = name;
    }

    // 对能创建的对象进行限制
    public GamePalyer(IGamePlayer _gamePlayer ,String name) throws Exception {
        if (_gamePlayer == null) {
            throw new Exception("不能创建角色");
        }else {
            this.name = name;
        }
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录名为 " + user + "的用户" + this.name + "登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "在打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "又升级了");
    }

}

class GamePlayerProxy implements IGamePlayer, IProxy {
    private IGamePlayer gamePlayer = null;

    public GamePlayerProxy(IGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    // 通过构造函数要对谁进行代练
    public GamePlayerProxy(String gamePlayerName) {
        try {
            gamePlayer = new GamePalyer(this, gamePlayerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user,password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
        count();
    }

    @Override
    public void count() {
        System.out.println("升级总费用 150");
    }
}
public class ProxyModel {
    public static void main(String[] args) {

        // 玩家直接打游戏
        GamePalyer palyer = new GamePalyer("张三");

        GamePlayIH gamePlayIH = new GamePlayIH(palyer);

        ClassLoader cl = palyer.getClass().getClassLoader();

        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, GamePalyer.class.getInterfaces(), gamePlayIH);
        proxy.login("zhangsan","password");
        proxy.killBoss();
        proxy.upgrade();

        // 找个游戏代练帮 palyer 打升级
//        GamePlayerProxy gamePlayerProxy = new GamePlayerProxy("张三");
//        gamePlayerProxy.login("zhangsan","password");
//        gamePlayerProxy.killBoss();
//        gamePlayerProxy.upgrade();

    }
}
