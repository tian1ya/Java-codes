package designMode.kindsOfSinglonMode;

public class ExtectorThread implements Runnable{
    @Override
    public void run() {
        LazyMode instance = LazyMode.getInstance();
        System.out.println(Thread.currentThread().getName() + " : " + instance);
    }
}
