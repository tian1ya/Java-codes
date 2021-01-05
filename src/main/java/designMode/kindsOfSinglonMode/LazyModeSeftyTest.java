package designMode.kindsOfSinglonMode;

public class LazyModeSeftyTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ExtectorThread());
        Thread thread2 = new Thread(new ExtectorThread());

        thread1.start();
        thread2.start();

        System.out.println("end");
    }
}
