package elk.es;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jws.Oneway;
import java.io.IOException;
import java.util.HashMap;

public class TestESLowerRESTApi {
    private RestClient restClient;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void init() {
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("0.0.0.0", 9200, "http"));

        clientBuilder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                System.out.println("出错了 -> " + node);
            }
        });

         this.restClient = clientBuilder.build();
    }

    @After
    public void after() throws IOException {
        this.restClient.close();
    }

    @Test
    public void test_get_info() throws IOException {
        Request request = new Request("GET", "haoke1/user/1009");
        request.addParameter("pretty", "true");

        Response response = this.restClient.performRequest(request);
        StatusLine statusLine = response.getStatusLine();
        System.out.println("status " + statusLine);

        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }

    @Test
    public void test_post_info() throws IOException {
        Request request = new Request("POST", "/haoke1/user/1009");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "1009");
        map.put("name", "你好");
        map.put("age", "100");
        map.put("sex", "女");

        String json = MAPPER.writeValueAsString(map);
        request.setJsonEntity(json);

        Response response = this.restClient.performRequest(request);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void test_query_search() throws IOException {
        // 低级客户端 需要装配请求体和返回
        Request request = new Request("POST", "/haoke1/user/_search");
        String searchJson = "{\"query\": {\"match\": {\"sex\": \"男\"}}}";
        request.setJsonEntity(searchJson);
        request.addParameter("pretty", "true");

        Response response = this.restClient.performRequest(request);
        String responseEntity = EntityUtils.toString(response.getEntity());
        JsonNode jsonNode = MAPPER.readTree(responseEntity);
        int total = jsonNode.get("hits").get("total").asInt();
        System.out.println(total);

        ArrayNode hits = (ArrayNode)jsonNode.get("hits").get("hits");
        for (JsonNode hit : hits) {
            System.out.println(hit.get("_source").get("sex").asText());
        }
    }

}
