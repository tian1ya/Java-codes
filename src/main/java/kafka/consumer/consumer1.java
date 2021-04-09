package kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class consumer1 {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.99.100:9092");

        //自动提交延迟时间，提交消费了数据的 offset, 默认1000ms 也就是1s
        /*
            自动提交：true 打开，那么消费者每次消费的数据都是之前没有消费过的
            如果关闭，设置为false，那么就会重复消费，事情是这样的
            当自动提交开这的时候，每次消费一笔数据，然后消费者就会提交消费记录offset，然后下次再消费的
            时候，由于消费者已经知道那部分数据已经被消费了，所以不会再消费了

            但是如果将自动提交关闭 设置为 false，那么每次消费数据的时候，消费者并不会提交，记录消费数据
            offset，下一次消费的时候还是从当前已经保存了offset开始消费数据，由于一部分数据之前已经消费了
            但是没有记录offset，所以还会再次消费

            自动提交但是该中方式的缺点在于，其实基于时间提交的，开发人员难以把握offset 的提交时间，

            还有一种手动提交的方式，分为2中方式
            1. 同步提交
            2. 异步提交
            二者都是将本地 poll 的一批数据最高的偏移量提交，不同点是 connmitSync 阻塞当前线程
            一直到提交成功，并且会自动失败充实(当然也会提交失败)， 而commitAsync 则没有失败重试机制
            故有可能提交失败

            同步提交有重试机制，所以会更加的可靠
            1. 关闭自动提交机制     properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
            2，读取到数据之后，提交  consumer.commitSync();

            异步提交会高效，使用的也多点(容易丢数据)

            不管是同步还是异步，或者自动提交，当设置的延时比较高的时候，就会出现数据重复的问题
            假如说设置的延时是在 10s 中，在10s内读取了数据，然后突然发生了故障，并没有提交数据
            然后将故障的机器重启，这时候去读数据，就会读出来上次未提交的那部分数据

            上面提到的offset 提交到了 kafka 本地存储。

            为了解决上面的提交问题，kafka还提供了自定义存储 offset，而不仅仅是将offset存储到kafka本地

            offset 的维护是非常繁琐的，因为需要考虑到消费者的 Rebalance

            当有新的消费者加入消费者组，已有的消费者退出消费者组或者所订阅的主题的分区发生了变化，就会触发
            到分区的重新分配，这个重新分配的过程就称为是 Reblance

            消费者发生Reblance 之后，每个消费者消费的分区去就会发生变化，因此消费者要首先获取到自己被分配到的分区
            并且定定位到每个分区最近提交的 offset 的位置继续消费，

           要实现自定义存储 offset， 就需要借助 ConsumerReblanceListener

         */
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"bigData1");


        /*
            现在重新启动消费者，是没有打印出来其他什么数据的，
            而且肯定可以确保的是kafka集群中肯定是有数据的，在控制台加 --from-gegining是可以读出来全部数据的
            当然在API 中也是肯定有相同的配置的，这个配置就是 AUTO_OFFSET_RESET_CONFIG
         */

        /*
            当然这个配置可以生效是需要有条件的
            重置需要满足2个中一个条件
                1. kafka 集群中没有初始化的 offset(启consumer 的时候重启一个新的组)
                2.  offset 不存在了(数据被删除了，如超过了数据保存时间，
                    然后数据就会被删除，在数据保存期间机器突然挂了，然后在数据被删除之后，重启启了机器)

            然后加上这个配置，才会去重置 offset

            有2个配置选项
            1. earliest:
            2. latest: (默认)，每次启一个consumer都会随机键一个组，这个时候是满足上面的第1个条件
                然后在latest配置下去取最新数据，从最新的开始消费，以前的消费不到的
         */

        /*
            修改默认配置到 earliest，当然这里还要记得将组名换一下GROUP_ID_CONFIG，满足上述提到的第1条件
            之前组名叫 bigdata，然后修改为 bigdata1，
            重启consumer，然后就能拿到历史所有数据了
         */
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList("first"));

        while (true) {
            // 拉数据，给一个时间，在这段时间内拉取不到数据就停止轮询, 批量拉取
            ConsumerRecords<String, String> result = consumer.poll(Duration.ofMillis(1000));

            consumer.assign(Arrays.asList(new TopicPartition("first", 0)));
            //解析打印 ConsumerRecords
            for (ConsumerRecord<String, String> record : result) {
                String key = record.key();
                String value = record.value();
                System.out.println("key: "+ key + " value: " + value);
            }
            consumer.commitSync();
        }

//        consumer.close();
    }
}
