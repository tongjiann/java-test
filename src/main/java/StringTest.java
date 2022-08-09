import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jianweitong
 */
public class StringTest {
    public final static String DOT = ".";
    public final static String SIGNED_SUFFIX_STR = "（已签署）";

    public static void main(String[] args) throws Exception {
//        slash("\\Users\\jianweitong\\Documents\\dir\\ric\\attachment_root\\78\\787C26DA-1DAF-42A8-BDDB-078B2C446EB8\\桐庐电子签章(1).pdf");

//        dot("13.41.pdf");

//        contain(Collections.singletonList("null"),null);

        readBinFile("/Users/jianweitong/Downloads/Untitled");
    }

    private static void dot(String oldFileName) {
        String fileName;
        if (oldFileName.contains(DOT)) {
            fileName = oldFileName.substring(0, oldFileName.lastIndexOf(DOT))
                    + SIGNED_SUFFIX_STR
                    + oldFileName.substring(oldFileName.lastIndexOf(DOT));
        } else {
            fileName = oldFileName;
        }
        System.out.println(fileName);
    }


    /*
     * split中使用的是正则表达式，所以要用\\\\而不是\\来匹配字符串中的\\
     */
    private static void slash(String str) {
        String SLASH = "/";
        String OPPOSITE_SLASH = "\\\\";
        String OPPOSITE_SLASH2 = "\\";

        System.out.println(str + "包含//:" + str.contains(SLASH));
        System.out.println(str + "包含\\:" + str.contains(OPPOSITE_SLASH2));
        System.out.println(str + "包含\\\\:" + str.contains(OPPOSITE_SLASH));
        System.out.println("==============================================");
        System.out.println("使用//切割后的最后一个字符:" + str.split(SLASH)[str.split(SLASH).length - 1]);
        System.out.println("使用\\\\切割后的最后一个字符:" + str.split(OPPOSITE_SLASH)[str.split(OPPOSITE_SLASH).length - 1]);
//        System.out.println("使用\\切割后的最后一个字符:" + str.split(OPPOSITE_SLASH2)[str.split(OPPOSITE_SLASH2).length-1]);
    }

    private static <T> void contain(List<T> list, T object) throws Exception {
        if (list == null || list.size() == 0) {
            throw new Exception("数组为空");
        }
        System.out.println(list.contains(object));
    }

    private static void readBinFile(String filePath) throws IOException {
        DataInputStream dis = null;

        dis = new DataInputStream(new FileInputStream(filePath));

        byte[] b = new byte[1024];

        dis.read(b);
    }

    @Test
    public void nullTest() {
        String a = null;
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(a));
        System.out.println(bigDecimal);
    }

    @Test
    public void splitTest() {
        String str = "com.yaagoole.ric.service.ru.BudgetAllocateFormServiceImpl$$EnhancerBySpringCGLIB$$a1c72625";
        System.out.println(Arrays.toString(str.split("\\$")));
        System.out.println(111_222_333);
    }

    @Test
    public void replaceTest() {
        String str = """
                CREATE TABLE `xyzgjjdxt.dsc_sx_biz_026_by_zc_detail1_v_zj`(
                	`id_id` STRING COMMENT '唯一主键ID',
                	`bhgx0034` STRING COMMENT '家庭关系',
                	`azcp0003` STRING COMMENT '姓名',
                	`azcp0001` STRING COMMENT '身份证',
                	`azcp0004` STRING COMMENT '性别',
                	`azcp0005` STRING COMMENT '出生日期',
                	`bhgx0039` STRING COMMENT '年龄',
                	`bhgx0040` STRING COMMENT '是否保障对象',
                	`azcp0013` STRING COMMENT '户口性质',
                	`bhgx0042` STRING COMMENT '人员类别',
                	`azcp0009` STRING COMMENT '健康状况',
                	`azcp0010` STRING COMMENT '婚姻状况',
                	`azcp0007` STRING COMMENT '民族',
                	`azcp0011` STRING COMMENT '职业状况',
                	`azcp0012` STRING COMMENT '政治面貌',
                	`bhgx0048` STRING COMMENT '特定是否保障对象',
                	`bhgx0049` STRING COMMENT '残疾类别',
                	`bhgx0050` STRING COMMENT '残疾等级',
                	`bhgx0051` STRING COMMENT '残疾证号',
                	`bhgx0052` STRING COMMENT '开户银行',
                	`bhgx0053` STRING COMMENT '银行账号',
                	`azcp0008` STRING COMMENT '文化程度',
                	`bhgx0055` STRING COMMENT '劳动能力',
                	`azbe0003` STRING COMMENT '工作学习单位',
                	`bhgx0093` STRING COMMENT '主键',
                	`bhgx0094` STRING COMMENT '外键',
                	`bhgx0095` STRING COMMENT '人员信息类别',
                	`bhgx0096` STRING COMMENT '单据类型',
                	`bhgx0000` STRING COMMENT '流水号唯一键值',
                	`dsc_city` STRING COMMENT '所属地市',
                	`dsc_adm_region` STRING COMMENT '所需区/县',
                	`dsc_sydep_code` STRING COMMENT '数源单位代码',
                	`dsc_sydep_name` STRING COMMENT '数源单位',
                	`dsc_sydep_sys` STRING COMMENT '数据所属系统名称',
                	`dsc_sydep_tblname` STRING COMMENT '数源单位表名',
                	`dsc_biz_record_id` STRING COMMENT '唯一自增序列号',
                	`dsc_biz_operation` STRING COMMENT 'I插入U更新D删除',
                	`dsc_biz_timestamp` STRING COMMENT '源表数据同步时间',
                	`dsc_datasr_tblname` STRING COMMENT '数据来源表名(清洗库或基础库表名)',
                	`dsc_hash_unique` STRING COMMENT '业务主键MD5值（清洗增加）',
                	`dsc_clean_timestamp` STRING COMMENT '清洗时间（清洗增加）',
                	`dsc_dw_rksj` STRING COMMENT '地市仓数据入库时间',
                	`dsc_mark` STRING COMMENT '自然人主表常住人口/流动人口标记'
                ) COMMENT '低保边缘在册家庭子表信息-绍兴';
                """;
        String replace = str.replace("STRING", "varchar(255)");
        System.out.println(replace);
    }

    @Test
    public void splitTest2() {
        List<String> strings = new ArrayList<>();
        strings.add("zjbigdata_after.biz_06043058_zj_dzjypt_zbjgxx");
        strings.add("zjhlc_hive.dsc_da041_jg_jgdx_qyqtxx_illegal_msg_valid_old_sx_v_zj");
        strings.add("zjhlc_hive.dsc_dd041_gsj_hj_hz_e_inv_valid_delta_gt_old_sx_v_zj");
        strings.add("zjbigdata_after.biz_06043051_zj_sw_yssjxx_fr");
        strings.add("zjbigdata_after.biz_06043051_zj_sw_yssjxx_gr");
        strings.add("zjhlc_hive.dsc_sx_biz_026_db_zc_detail1_v_zj");
        strings.add("zjhlc_hive.dsc_da026_by_zc_main_valid_old_sx_v_zj");
        strings.add("zjhlc_hive.dsc_da026_db_sr_main_valid_old_sx_v_zj");
        strings.add("zjhlc_hive.dsc_da026_db_zc_main_valid_old_sx_v_zj");
        strings.add("zjhlc_hive.dsc_sx_biz_026_by_zc_detail1_v_zj");


        strings.forEach(s -> {
            StringBuilder stringBuilder = new StringBuilder();
            String[] split = s.split("\\.");
            stringBuilder
                    .append("INSERT OVERWRITE TABLE xyzgjjdxt.")
                    .append(split[1]).append(" (SELECT * FROM ")
                    .append(s)
                    .append(");");

//            stringBuilder
//                    .append("DELETE FROM xyzgjjdxt.")
//                    .append(split[1])
//                    .append(";\n")
//                    .append("INSERT INTO xyzgjjdxt.")
//                    .append(split[1])
//                    .append(" (SELECT * FROM ")
//                    .append(s)
//                    .append(");\n");
            System.out.println(stringBuilder.toString());
        });
    }


}
