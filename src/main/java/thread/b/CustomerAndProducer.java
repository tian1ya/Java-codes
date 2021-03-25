package thread.b;


import java.util.LinkedList;

public class CustomerAndProducer {

    static final class Message {
        private int id;
        private String msg;

        public Message(int id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public int getId() {
            return id;
        }

        public String getMsg() {
            return msg;
        }
    }

    // java 线程之间的通信
    static class MessageQueue {
        private LinkedList<Message> list = new LinkedList<>();
        private int capacity;

        public MessageQueue(int capacity) {
            this.capacity = capacity;
        }

        // 获取消息
        public Message take() {
            System.out.println(Thread.currentThread().getName() + " 取一条消息");
            synchronized (list) {
                while (list.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " 你要取内容的队列是空的");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Message message = list.removeFirst();

                // 通知生产者，可以生产了
                list.notifyAll();
                return message;
            }
        }

        // 存入消息
        public void put(Message message) {
            System.out.println(Thread.currentThread().getName() + " 添加一个消息");
            synchronized (list) {
                while (list.size() == capacity) {
                    System.out.println(Thread.currentThread().getName() + " 你要取内容的队列已经满了");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                list.add(message);

                // 唤醒那些准备拿消息的消费者
                list.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(3);

        for (int i = 0; i < 3; i++) {
            int i1 = i;
            new Thread(() -> {
                messageQueue.put(new Message(i1, "内容" + i1));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = messageQueue.take();
                System.out.println("获取消息 " + message.getMsg());
            }
        }, "消费者 1").start();

    }
}
