package elk.es;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestESHigherRESTApi {


    private RestHighLevelClient restClient;

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

        this.restClient = new RestHighLevelClient(clientBuilder);
    }

    @After
    public void after() throws IOException {
        this.restClient.close();
    }

    static class Person{
        public Person() {
        }

        public Person(String id, String name, String age, String sex) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String id;
        public String name;
        public String age;
        public String sex;

        @Override
        public String toString() {
            return "Person{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }


    @Test
    public void test_post_info_sync() throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "1010");
        map.put("name", "你好啊");
        map.put("age", "80");
        map.put("sex", "男");

        IndexRequest indexRequest = new IndexRequest("haoke", "user").source(map);

        IndexResponse indexResponse = this.restClient.index(indexRequest, RequestOptions.DEFAULT);
        String id = indexResponse.getId();
        System.out.println("id->" + id);

        String index = indexResponse.getIndex();
        System.out.println("index->" + index);

        String type = indexResponse.getType();
        System.out.println("type->" + type);

        long version = indexResponse.getVersion();
        System.out.println("version->" + version);

        DocWriteResponse.Result result = indexResponse.getResult();
        System.out.println("result->" + result);
        System.out.println("shardInfo->" + indexResponse.getShardInfo());
    }

    @Test
    public void test_post_info_async() throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "1020");
        map.put("name", "你好啊2222");
        map.put("age", "8022");
        map.put("sex", "男");

        IndexRequest indexRequest = new IndexRequest("haoke", "user").source(map);

        this.restClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                String id = indexResponse.getId();
                System.out.println("id->" + id);

                String index = indexResponse.getIndex();
                System.out.println("index->" + index);

                String type = indexResponse.getType();
                System.out.println("type->" + type);

                long version = indexResponse.getVersion();
                System.out.println("version->" + version);

                DocWriteResponse.Result result = indexResponse.getResult();
                System.out.println("result->" + result);
                System.out.println("shardInfo->" + indexResponse.getShardInfo());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println(e.getMessage());
            }
        });

        System.out.println("ok");
        Thread.sleep(10000 * 2);
    }

    @Test
    public void test_search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("haoke");
        searchRequest.types("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("sex", "男"));
        builder.from(1);
        builder.size(2);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(builder);

        SearchResponse search = this.restClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println("结果个数：" + search.getHits().totalHits);

        SearchHits hits = search.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            Person person = MAPPER.readValue(json, Person.class);
            System.out.println(person);
        }

    }
}
