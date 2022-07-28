package entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-06-27 16:58
 */
public class UserTest {
    @Test
    public void test(){
        User user = new User();
        user.setId("123");
        List<String > s = new ArrayList<>();
        s.add("1");
        s.add("1");
        s.add("1");
        Map<String ,String > map = new HashMap<>();
        map.put("1","1");
        map.put("2","1");
        user.setS(s);
        user.setMap(map);
        Map<String ,User> map1 = new HashMap<>();
        map1.put("1",user);
        map1.put("2",user);
        System.out.println(map1);
    }
}
