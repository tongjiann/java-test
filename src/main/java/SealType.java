import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public enum SealType {
    HAND_WRITING_SEAL(0, "手写签章"),
    SYSTEM_SEAL(1, "系统签章"),
    E_SEAL(2, "E签宝"),
    ZHEJIANG_UNIT_SEAL(3, "浙江省统一电子印章");

    // 成员变量
    private int key;
    private String value;

    // 构造方法
    SealType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public static SealType getByKey(int key) throws Exception {
        SealType[] values = SealType.values();
        SealType res = null;
        List<SealType> sealTypes = Arrays.asList(values);
        for(Iterator<SealType> iterator =  sealTypes.iterator();iterator.hasNext();){
            SealType next = iterator.next();
            if(next.key==key){
                res = next;
            }
        }
        if(res==null){
            throw new Exception("未找到");
        }
        return res;
    }

    @Override
    public String toString() {
        return "SealType{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}