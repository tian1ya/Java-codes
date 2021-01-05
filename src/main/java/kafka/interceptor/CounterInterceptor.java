package kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CounterInterceptor implements ProducerInterceptor {

    int success;
    int failure;

    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null){
            // 发送成功了
            success ++;
        }else {
            failure ++;
        }
    }

    @Override
    public void close() {
        System.out.println("成功：" + success + " " + "失败：" + failure);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
