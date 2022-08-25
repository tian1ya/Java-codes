package thread.beauty.chapt01;

public class NewThreadLocal {
    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            tl.set("1");
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            tl.set("2");
            System.out.println(tl.get());
        }).start();
    }
}
