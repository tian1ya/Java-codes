package thread.chapt1.chapt1;

public class templateDesignPattern {
    /*
        这里提到的 模板设计方法，也就是 Thread 中的设计模式了

        父类实现算法结构代码，子类实现逻辑细节

        程序的结构是由父类实现的，并且是 final 的，子类不可以重写

        子类需要实现想要的逻辑即可
     */

    public final void print(String msg){
        System.out.println("**********************");
        wrapPrint(msg);
        System.out.println("**********************");
    }

    public void wrapPrint(String msg) { }

    public static void main(String[] args) {
        templateDesignPattern t1 = new templateDesignPattern() {

            @Override
            public void wrapPrint(String msg) {
                System.out.println("&& " + msg + " &&");
            }
        };

        t1.print("hello");


        templateDesignPattern t2 = new templateDesignPattern() {
            public void wrapPrint(String msg) {
                System.out.println("@@ " + msg + " @@");
            }
        };

        t2.print("hello");
    }
}
