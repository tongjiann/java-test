public class EnumTest {
    public static void main(String[] args) throws Exception {
        SealType sealType = SealType.getByKey(1);
        System.out.println(sealType);
    }
}
