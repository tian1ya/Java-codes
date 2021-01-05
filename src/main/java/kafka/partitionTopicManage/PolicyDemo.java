package kafka.partitionTopicManage;

import org.apache.kafka.common.errors.PolicyViolationException;
import org.apache.kafka.server.policy.CreateTopicPolicy;

import java.util.Map;

public class PolicyDemo implements CreateTopicPolicy {
    @Override
    public void validate(RequestMetadata requestMetadata) throws PolicyViolationException {
        if (requestMetadata.numPartitions() != null ||
            requestMetadata.replicationFactor() != null) {
            if (requestMetadata.numPartitions() > 2) {
                throw new PolicyViolationException("Topic policy not validate");
            }
        }
        throw new PolicyViolationException("Topic policy not validate");
    }

    @Override
    public void close() throws Exception {
        // 关闭kafka 服务的时候执行
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // kafka 服务启动的时候执行
    }
}
