import cn.hutool.http.HttpUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NacosTest {

    private static final String HOST = "127.0.0.1:8848";

    public static final String GROUP = "TEST_GROUP";

    public static final String CONFIG_API = "/nacos/v1/cs/configs";

    public static final String CONFIG_PATH = "src/main/resources/application-extension.yml";

    public static final List<String> MICRO_SERVICE_NAME_LIST = Arrays.asList(
            "abc-data",
            "abc-main",
            "abc-gateway"
    );


    public static void main(String[] args) {
        // 获取配置
//        System.out.println(getConfig("DEFAULT_GROUP", "abc-main-dev.yml"));

        // 发布配置
        System.out.println(publishConfig(GROUP, "abc-main-dev.yml", "test", Type.TEXT));

    }

    /**
     * 获取配置信息
     */
    public static String getConfig(String group, String dataId) {
        return HttpUtil.get(HOST + CONFIG_API + "?group=" + group + "&dataId=" + dataId);
    }

    /**
     * 发布配置
     */
    public static String publishConfig(String group, String dataId, String content, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("group", group);
        params.put("content", content);
        params.put("dataId", dataId);
        params.put("type", type);
        return HttpUtil.post(HOST + CONFIG_API, params);

    }

    static class Type {
        public static final String TEXT = "test";
        public static final String JSON = "json";
        public static final String XML = "xml";
        public static final String YAML = "yaml";
        public static final String HTML = "html";
        public static final String PROPERTIES = "properties";
    }
}
