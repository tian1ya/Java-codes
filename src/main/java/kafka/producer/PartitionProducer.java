package kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class PartitionProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        //批次大小
        properties.put("batch.size", 16384);

        // 等待时间，批次大小到达配置或者等待时间到了之后就发送, 写到 RecordAccumulator缓冲区中
        properties.put("linger.ms", 1);

        // RecordAccumulator 缓冲区大小
        properties.put("buffer.memory", 33554432);

        // 序列号类
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 分区器设置
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"kafka.partitioner.partition1");

        KafkaProducer producer = new KafkaProducer<String, String>(properties);

        /*
            如果不给partition值，而给了key值，那么消息 会发送到key的哈希值的那个分区去
         */
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord("first","key","=+=:" + i), (recordMetadata, e) -> {
                if (e == null){
                    // 发送成功
                    System.out.println("partition: "+ recordMetadata.partition() + " offset: " + recordMetadata.offset());
                } else {
                    System.out.println("发送消息失败");
                    e.printStackTrace();
                }
            });
        }

        producer.close();
    }

}
