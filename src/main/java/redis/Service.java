package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.Objects;


public class Service {


    public void service(String id) {
//        Jedis client = new Jedis("192.168.56.100", 6379);

        Jedis client = JedisUtil.jp.getResource();


        String value = client.get("compid6:" + id);
        try {
            if (Objects.isNull(value) || "".equals(value)) {
                String value2 = Long.MAX_VALUE - 10 + "";
                client.setex("compid6:" + id, 5, value2);
            } else {
                Long incr = client.incr("compid6:" + id);
                business(10 - (Long.MAX_VALUE - incr));
            }
        } catch (JedisDataException e) {
            System.out.println("使用到达上限，请升级会员级别");
            return;
        } finally {
            client.close();
        }
    }

    public void business(Long id) {
        System.out.println("业务操作执行:" + id);
    }
}

class MyThread extends Thread {
    Service s = new Service();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s.service("初级用户");
        }
    }
}

class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
