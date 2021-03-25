package thread.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SellTicket {

    static Random random = new Random();

    static class TicketWindow {
        public int count;

        public TicketWindow(int count) {
            this.count = count;
        }

        // sell 方法对共享变量读写，
        public synchronized int sell(int amount) {
            if (amount <= this.count) {
                this.count = this.count - amount;
                return amount;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {


        TicketWindow window = new TicketWindow(1000);
        List<Integer> selloutAmount = new Vector<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                // 存在线程问题，多个线程访问同一个共享变量
                int sell = window.sell(randomAmount());
                try {
                    Thread.sleep(randomAmount()); // 增加指令混乱可能性
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                // Vector.add 是安全的方法
                selloutAmount.add(sell);
            });

            // 这里不存在安全问题，因为只是在主程序中使用
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("余票 " + window.count);
        System.out.println("卖出去的票 " + selloutAmount.stream().mapToInt(ii->ii).sum());
    }

    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}
