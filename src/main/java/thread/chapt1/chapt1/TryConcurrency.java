package thread.chapt1.chapt1;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    public static void main(String[] args) {

        // 启动新的线程，只有调用了Thread 的 start 方法，才代表派生了一个心得线程
        new Thread(){
            @Override
            public void run() {
                browseNews();
            }
        }.start();

        // 创建一个线程，并且run 方法，并将 enjoyMusic 交给他执行
        enjoyMusic();

    }

    public static void browseNews(){
        for (; ;) {
            System.out.println("The good news");
            sleep(1);
        }
    }

    public static void enjoyMusic(){
        for (; ;) {
            System.out.println("The good music");
            sleep(1);
        }
    }

    public static void sleep(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
