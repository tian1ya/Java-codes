package redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

public class RedisTest {

    private Jedis client;

    @Before
    public void buildConnection() {
        client = new Jedis("192.168.56.100", 6379);
    }

    @Test
    public void testSet() {
        client.set("name", "itheima");
        System.out.println(client.get("name"));
    }

    @Test
    public void testList() {
        client.lpush("list1", "1","2","3");
        List<String> lis1 = client.lrange("list1", 0, -1);
        System.out.println(lis1.toString());
        System.out.println(client.llen("list1"));
    }

    @Test
    public void testHash() {
        client.hset("hset1", "1","1");
        client.hset("hset1", "2","2");
        client.hset("hset1", "3","3");

        Map<String, String> stringMap = client.hgetAll("hset1");
        for (Map.Entry<String, String> stringStringEntry : stringMap.entrySet()) {
            System.out.println(stringStringEntry.getKey() + " -> " + stringStringEntry.getValue());
        }

        System.out.println(client.hlen("hset1"));
    }

    @Test

    @After
    public void closeConnection () {
        client.close();
    }
}
