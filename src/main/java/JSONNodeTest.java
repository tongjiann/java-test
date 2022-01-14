import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class JSONNodeTest {
    public static void main(String[] args) {
        String str = "{ \"success\": true, \"errorCode\": \"\"\n" +
                "}";
        JSONObject ret = JSONObject.parseObject(str);
        String result = ret.getString("success");
        if (!"true".equals(result)) {
            return;
        }
        JSONArray data = ret.getJSONArray("result");
        List<ZLBPEVoiceInfo> collect = data.stream().map(e -> new ZLBPEVoiceInfo((JSONObject) e)).collect(Collectors.toList());
        System.out.println("123");
    }
}
