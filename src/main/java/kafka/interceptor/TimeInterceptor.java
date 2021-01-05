package kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class TimeInterceptor implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        String value = (String) record.value();
        long timeStamp = System.currentTimeMillis();
        String newValue = String.format("{%d}:{%s}", timeStamp, value);

        // record 对象是不可改的, 需要新创建
        return new ProducerRecord<>(record.topic(),record.partition(), record.key(), newValue);
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
