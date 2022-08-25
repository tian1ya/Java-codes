package thread.beauty.chapt01;


import java.util.LinkedList;

public class ThreadWaitAndNotifyByConsumerAndProduce {
    private LinkedList<String> queue = new LinkedList();
    private int capacity = 10;

    public void produce(String ele) {
        synchronized (queue) {
            while (this.queue.size() >= 10) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("插入值 +++：" + ele);

            queue.add(ele);
            queue.notifyAll();
        }
    }

    public String consume() {
        synchronized (queue) {
            while (this.queue.size() == 0) {
                try {
                    queue.wait();
//                    queue.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String el = this.queue.poll();
            System.out.println("获取值 --- ：" + el);
            queue.notifyAll();
            return el;
        }

    }

    public static void main(String[] args) {
        ThreadWaitAndNotifyByConsumerAndProduce consumerAndProduce = new ThreadWaitAndNotifyByConsumerAndProduce();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int tem = i;
                consumerAndProduce.produce(tem + "");
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                consumerAndProduce.consume();
            }
        }).start();
    }
}
