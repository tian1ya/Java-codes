package zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZK {
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper("192.168.56.100:2181", 20000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                List<String> children = null;
                try {
                    children = zkClient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (String child : children) {
                    System.out.println(child);
                }
            }
        });
    }

    @Test
    public void createNode() throws KeeperException, InterruptedException,IOException {
        String path = zkClient.create("/atguig", "dahai".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    @Test
    public void getChildDataAndWatch() throws KeeperException, InterruptedException, IOException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child + " ##");
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void isNodeExist() throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists("/atguig", false);
        System.out.println("exists: " + exists);
    }
}
