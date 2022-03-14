import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    public  void testEncryptMap() {
        Map<String, Object> strMap = new HashMap<>();

        strMap.put("appKey", "DESUtil.encryption(appKey,DESUtil.DES_KEY)");
        strMap.put("appSerect", "DESUtil.encryption(appSecret,DESUtil.DES_KEY)");
        strMap.put("timeStamp", "DESUtil.encryption((new Date()).toString(),DESUtil.DES_KEY)");
        String json = strMap.toString();
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_FORM_URLENCODED);
        System.out.println(json);
        System.out.println(stringEntity);
    }

    @Test
    public void testEmptyMap(){
        Map<String ,String > map = new HashMap<>();
        System.out.println(map.get("1"));
    }
}
