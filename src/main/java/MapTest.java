import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Object> strMap = new HashMap<>();

        strMap.put("appKey", "DESUtil.encryption(appKey,DESUtil.DES_KEY)");
        strMap.put("appSerect", "DESUtil.encryption(appSecret,DESUtil.DES_KEY)");
        strMap.put("timeStamp", "DESUtil.encryption((new Date()).toString(),DESUtil.DES_KEY)");
        String json = strMap.toString();
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_FORM_URLENCODED);
        System.out.println(json);
        System.out.println(stringEntity);
    }
}
