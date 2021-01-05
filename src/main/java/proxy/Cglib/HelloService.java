package proxy.Cglib;

// 目标对象,没有实现任何接口
public class HelloService {

    public HelloService() {
        System.out.println("HelloService构造");
    }

    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     */
    final public String sayOthers(String name) {
        System.out.println(this);
        /*
            在使用的时候使用 cglib 代理，这里this 指向的是生成的 Cglib 子类

         */
        System.out.println("HelloService:sayOthers >> "+name + " \n");
        return null;
    }

    public void sayHello() {
        System.out.println("HelloService:sayHello \n");
    }

    public void sayHi() {
        System.out.println("HelloService:say Hi \n");
    }
}
