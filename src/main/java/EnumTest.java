import java.util.HashMap;
import java.util.Map;

public class EnumTest {
    public static void main(String[] args) throws Exception {
        SealType sealType = SealType.getByKey(1);
        Map<SealType,String > map = new HashMap<>();
        map.put(SealType.E_SEAL,"1");
        System.out.println(map);
//        System.out.println(sealType);
    }
}
