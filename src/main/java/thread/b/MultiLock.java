package thread.b;

import java.util.concurrent.locks.ReentrantLock;

public class MultiLock {

    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
//        BigRoom room = new BigRoom();
//
//        new Thread(() -> room.sleep()).start();
//
//        new Thread(() -> room.study()).start();

//        test();
        Chopstick chopstick = new Chopstick("1");
        Chopstick chopstick2 = new Chopstick("2");
        Chopstick chopstick3 = new Chopstick("3");
        Chopstick chopstick4 = new Chopstick("4");
        Chopstick chopstick5 = new Chopstick("5");

        new Philosopher("苏格拉底", chopstick, chopstick2).start();
        new Philosopher("柏拉图", chopstick2, chopstick3).start();
        new Philosopher("亚里士多", chopstick3, chopstick4).start();
        new Philosopher("赫拉克利特", chopstick4, chopstick5).start();
        new Philosopher("阿基米德", chopstick5, chopstick).start();

//        TestLiveLock();
    }

    public static void TestLiveLock() {

        new Thread(() -> {
            while (count > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                count--;
                System.out.println("Count--: " + count);
            }
        }).start();


        new Thread(() -> {
            while (count < 20) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                count++;
                System.out.println("Count++: " + count);
            }
        }).start();
    }

    static class Chopstick extends ReentrantLock {
        String name;

        public Chopstick(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Chopstick{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Philosopher extends Thread {
        Chopstick left;
        Chopstick right;

        public Philosopher(String name, Chopstick left, Chopstick right) {
            super(name);
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            while (true) {
                if (left.tryLock()) {
//                    System.out.println(getName() + " 获得左手的筷子");
                    try {
                        if (right.tryLock()) {
                            try {
                                System.out.println(getName() + " 获得左右手筷子， 可以吃饭了");
                                eat();
                            } finally {
//                                System.out.println(getName() + " 释放右手筷子");
                                right.unlock();
                            }
                        }
                    } finally {
//                        System.out.println(getName() + " 获得左手的筷子，并随后释放了左手筷子");
                        left.unlock();
                    }
                }
            }
        }

        private void eat(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test() {
        Object studyRoom = new Object();
        Object sleepRoom = new Object();

        Thread thread = new Thread(() -> {
            synchronized (studyRoom) {
                System.out.println("locl studyRoom");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (sleepRoom) {
                    System.out.println("locl sleepRoom");
                    System.out.println("lock  sleepRoom 后的操作");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (sleepRoom) {
                System.out.println("locl sleepRoom");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (studyRoom) {
                    System.out.println("locl studyRoom");
                    System.out.println("lock  studyRoom 后的操作");
                }
            }
        });

        thread.start();
        thread2.start();
    }

    static class BigRoom {
        private final Object studyRoom = new Object();
        private final Object sleepRoom = new Object();

        public void sleep() {
            synchronized (sleepRoom) {
                System.out.println("sleep 2 hours");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void study() {
            synchronized (studyRoom) {
                System.out.println("study 1 hour");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
