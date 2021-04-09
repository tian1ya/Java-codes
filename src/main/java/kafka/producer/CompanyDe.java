package kafka.producer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import javax.print.attribute.standard.MediaSize;
import javax.sql.rowset.serial.SerialException;
import java.nio.ByteBuffer;
import java.util.Map;

public class CompanyDe implements Deserializer<Company> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public Company deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        if (data.length < 8) {
            try {
                throw new SerialException("aa");
            } catch (SerialException throwables) {
                throwables.printStackTrace();
            }
        }

        ByteBuffer buffer = ByteBuffer.wrap(data);
        int numLen, addressLen;
        String name, address;

        int nameLen = buffer.getInt();
        byte[] nameByte = new byte[nameLen];
        buffer.get(nameByte);

        addressLen = buffer.getInt();
        byte[] addressBytes = new byte[addressLen];
        buffer.get(addressBytes);

        name = new String(nameByte);
        address = new String(addressBytes);
        return new Company(name, address);
    }

    @Override
    public void close() {

    }
}
