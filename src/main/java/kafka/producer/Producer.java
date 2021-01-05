package kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            kafka 生产者配置信息， 以下参数都是可以有默认值的

         */
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


        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 发送数据
        for (int i = 0; i < 10; i++) {
            Future<RecordMetadata> first = producer.send(new ProducerRecord<>("first", "SS","atgugui: " + i));

            /*
                send 是异步发送，返回一个Future，当调用
                get 方法会阻塞线程，执行结束后才执行其他线程
             */
            RecordMetadata recordMetadata = first.get();
        }

        //关闭连接
        producer.close();
    }
}
