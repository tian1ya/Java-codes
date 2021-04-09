package kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/*
    自定义分区
 */
public class partition1 implements Partitioner {

    /*
        这里又必要查看下，默认的分区 DefaultPartitioner 的方法
        cluster 可以拿到当前集群的一些信息
     */
    @Override
    public int partition(String topic, Object key, byte[] bytes, Object value, byte[] bytes1, Cluster cluster) {
//        Integer partitionCnt = cluster.partitionCountForTopic(topic);
//        return key.hashCode() % partitionCnt;
        // 这里具体如何分区写数据，是根据具体业务逻辑去写的
        return 1;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> map) {
    }
}
