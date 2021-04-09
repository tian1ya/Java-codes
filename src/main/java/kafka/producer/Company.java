package kafka.producer;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class Company implements Serializer<Company> {
    private String name;
    private String address;

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // 可以不配置，默认使用utf-8 编码
    }

    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null) return null;

        byte[] name, address;
        try {
            byte[] nameBytes = data.name.getBytes("utf-8");
            byte[] addressBytes = data.address.getBytes("utf-8");

            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + nameBytes.length + addressBytes.length);
            buffer.putInt(nameBytes.length);
            buffer.put(nameBytes);

            buffer.putInt(addressBytes.length);
            buffer.put(addressBytes);
            return buffer.array();

        } catch (Exception e) {

        }
        return new byte[0];
    }

    @Override
    public void close() {
    }
}
