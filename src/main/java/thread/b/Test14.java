package thread.b;

public class Test14 {
    private Object response;

    // 获取结果的方法
    public Object get(long timeout) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passTime = 0;
            while (response == null) {
                // 解决虚假唤醒

                try {
                    // 应该等待时间
                    long waitTime = timeout - passTime; // 这里逻辑有点秀啊
                    if (waitTime <= 0) {
                        System.out.println("等待时间到了，退出...");
                        break;
                    }
                    this.wait(waitTime);
                    System.out.println("被唤醒了，但是还没到拿到结果，且等待时间没到，继续等待： " + waitTime);
                    passTime = System.currentTimeMillis() - begin;
                    // 这里不能增加等待时间，因为就算加了等待时间，将这个线程唤醒
                    // 但是因为 response 还是 null，所以 while 中还是会进入到 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        // 线程1等待线程2的下载结果
        Test14 test14 = new Test14();

        new Thread(() -> {
            System.out.println("t1 等待结果");
            Object o = test14.get(10000);
            System.out.println("t1 获得结果");
            System.out.println(o);
        }).start();


        new Thread(() -> {
            System.out.println("t2 下载中。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            test14.complete("t2 down load complete and return result");
        });
    }
}
