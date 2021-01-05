package kafka.partitionTopicManage;


import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class AdminClientTest {
    public static void main(String[] args) {
        String brokerLists = "192.168.99.100:9092";
        String topic = "topic-admin";

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerLists);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        /*
            props.put("create.topic.policy.class.name", "kafka.partitionTopicManage.PolicyDemo");
            这个生效，需要在 server.properties 中配置属性，而不是在代码中

         */

        AdminClient client = AdminClient.create(props);

        NewTopic newTopic = new NewTopic(topic, 2, (short) 2);

        System.out.println("create topics");
        CreateTopicsResult createTopicsResult = client.createTopics(Collections.singleton(newTopic));

        try {
            createTopicsResult.all().get();

            System.out.println("get all topics");
            ListTopicsResult topics = client.listTopics();
            Set<String> strings = topics.names().get();
            strings.forEach(System.out::println);

            System.out.println("======================");
            System.out.println("describeConfigs");

            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
            DescribeConfigsResult result = client.describeConfigs(Collections.singleton(resource));
            Config config = result.all().get().get(resource);
            System.out.println(config);


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        client.close();
    }
}
