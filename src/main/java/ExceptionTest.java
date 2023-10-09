import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-17 19:58
 */
public class ExceptionTest {

    @Test
    public void oomTest() {
        List<Object> list = new LinkedList<>();
        while (true) {
            byte[] b = new byte[1024 * 1024];
            list.add(b);
        }
    }

    @Test
    public void stackOverflowTest() {
        stackOverflowTest();
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        String url = "https://www.baidu.com";
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)  // 设置连接超时时间为5秒
                .setSocketTimeout(5000)   // 设置读取数据超时时间为5秒
                .build();
        for (Integer integer : list) {
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                // 创建 http POST 请求
                HttpPost httpPost = new HttpPost(url);
                httpPost.setConfig(requestConfig);
                JSONObject jsonObject = new JSONObject();
                // 写入参数

                StringEntity stringEntity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                httpPost.setEntity(stringEntity);


                try (CloseableHttpResponse response = client.execute(httpPost);) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(EntityUtils.toString(entity, "UTF-8"));
                        if (jsonNode != null) {
                            if (!jsonNode.path("success").asBoolean()) {
                                if ("已存在相同资产编码的设备！".equals(jsonNode.findPath("message").asText())) {
                                    continue;
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
