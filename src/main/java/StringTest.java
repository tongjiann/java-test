import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import helper.LevenshteinHelper;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                CREATE TABLE `xyzgjjdxt.biz_06043051_zj_sw_yssjxx_fr`(
                	`tyshxydm` STRING COMMENT '统一社会信用代码',
                	`yhh` STRING COMMENT '用户号',
                	`yhdz` STRING COMMENT '用户地址',
                	`yhxz` STRING COMMENT '用户性质',
                	`frjgmc` STRING COMMENT '法人机构名称',
                	`cbrq` STRING COMMENT '抄表日期',
                	`zdlsh` STRING COMMENT '账单流水号',
                	`sqcj` STRING COMMENT '上期抄见',
                	`sccbrq` STRING COMMENT '上次抄表日期',
                	`sjly` STRING COMMENT '数据来源',
                	`bqcj` STRING COMMENT '本期抄见',
                	`bqsl` STRING COMMENT '本期水量',
                	`znje` STRING COMMENT '滞纳金额',
                	`bsh` STRING COMMENT '表计号',
                	`xzqydm` STRING COMMENT '行政区域代码',
                	`gsbm` STRING COMMENT '公司编码',
                	`op` STRING COMMENT '取值:insert,update,delete',
                	`biz_time` STRING COMMENT '记录产生时间，部门提供',
                	`load_time` STRING COMMENT '推送时间，自动生成'
                ) COMMENT '诸暨市用水数据表（法人）';
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

    @Test
    public void elTest() {
        String source = """
                部门【${部门}】用户【${用户}】创建 岗位:$<[${岗位}-岗位]>成功
                """;
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> po = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("岗位", "pos1");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("岗位", "pos2");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("岗位", "pos3");
        po.add(map1);
        po.add(map2);
        po.add(map3);
        map.put("部门", "开发部");
        map.put("用户", "张三");

        map.put("岗位", po);
        map.put("岗位1", "po");
        String target = getContent(source, map);
        System.out.println(target);
    }


    public String getContent(String str, Map<String, Object> map) {
        str = dealWithList(str, map);
        return dealWithSimple(str, map);
    }

    /**
     * 处理EL表达式变量的替换
     */
    private static String dealWithSimple(String str, Map<String, Object> map) {
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String key = ObjectUtil.defaultIfNull(matcher.group(1), "");
            var value = ObjectUtil.defaultIfNull((String) map.get(key), "");
            str = str.replace("${" + key + "}", value);
        }
        return str;
    }

    /**
     * 处理集合的EL表达式变量的替换
     */
    private String dealWithList(String str, Map<String, Object> map) {
        return dealWithList("岗位", str, map);
    }

    private String dealWithList(String listName, String str, Map<String, Object> map) {
        Pattern pattern = Pattern.compile("\\$<(.*?)>");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            List<Map<String, Object>> value;
            StringBuilder stringBuilder = new StringBuilder();
            Object o = ObjectUtil.defaultIfNull(map.get(listName), new ArrayList<>());
            if (o instanceof List) {
                value = (List<Map<String, Object>>) o;
                value.forEach(e -> stringBuilder.append(dealWithSimple(group, e)));
            }
            str = str.replace("$<" + group + ">", stringBuilder.toString());
        }
        return str;
    }

    @Test
    public void similarTest() {

        var 中标工程名称 = "2021年滨江幼儿园改造工程$$$$$东和乡中校园整体改造工程$$$$$南悦幼儿园改扩建工程$$$$$唐山小学外墙、道路硬化及幼儿园室内等装修改造工程$$$$$学勉中学1#、2#食堂改造工程$$$$$应店街镇小改造工程$$$$$技师学院特色餐厅及排污系统改造工程$$$$$暨阳分校体能测试室装修工程$$$$$暨阳初中宿舍楼改造工程$$$$$枫桥新镇中功能室等装修工程（施工）$$$$$枫桥镇学校改造工程$$$$$柱山小学老教学楼及室外活动场地改造工程$$$$$次坞镇秀松中学体艺馆改造工程$$$$$江藻初中改造工程（二期）$$$$$浣江初中耕深楼改造工程$$$$$浣纱初中运动场改建工程$$$$$湄池中学教学楼强弱电桥架预铺设及广播线路改造工程$$$$$湄池中学消防和饮水管网改造工程$$$$$湖头小学（复办）改造工程-室外改造项目$$$$$湖头小学（复办）改造工程-教学楼改造项目$$$$$牌头中学三幢教工宿舍维修改造工程$$$$$职教中心教学楼行政楼厕所改造及校园地下水管维修工程$$$$$职教中心新能源实训室维修工程$$$$$草塔中学体育馆外立面及校东大门维修工程$$$$$草塔中学学生宿舍3号楼改造、体育馆屋顶及排水管道维修工程$$$$$草塔中学学生食堂装修工程$$$$$草塔中学教学楼学生卫生间改造装修、食堂二楼地面改造、格致楼及报告厅大门出口原铝塑板雨棚工程$$$$$诸暨中学暨阳分校南校门新建及配套工程$$$$$诸暨中学暨阳分校科技馆外立面改造工程$$$$$诸暨中学校园安全防护提升工程$$$$$诸暨中学行政电教图书楼及食堂男生楼外墙面维修改造工程$$$$$诸暨工业职校户外体验中心建设工程$$$$$诸暨市教育体育局2022年度50万元以下小额基建工程—房屋建筑、市政、园林、绿化工程$$$$$诸暨市教育体育局2022年度房屋建筑、市政工程监理招标$$$$$诸暨市新世纪小学食堂改造工程$$$$$诸暨市暨阳街道大侣小学食堂改造及塑胶修补项目$$$$$诸暨市浣纱小学二校区塑胶运动场改造工程$$$$$诸暨市璜山初中改造提升工程$$$$$诸暨市菲达幼儿园维修工程$$$$$诸暨市陶朱街道跨湖幼儿园会议室拓宽、户外活动场等装修改造工程$$$$$诸暨技师学院部分消防管道更换维修、管道阀门井增加工程$$$$$陶朱中心幼儿园、白门幼儿园室内及室外场地等改造工程$$$$$陶朱小学室内外场地、污水管道等改造工程$$$$$陶朱明德小学攀岩壁项目$$$$$陶朱街道明德小学走廊、功能室等装修工程";
        var 存在考勤工程名称 = "浙江农林大学暨阳学院 学生公寓新建工程项目$$$$$浙江农林大学暨阳学院 学生公寓新建工程项目$$$$$浙江省诸暨市职业教育中心 诸暨市职教中心高职教学楼新建工程$$$$$浙江诸暨荣怀教育投资发展有限公司荣怀春江幼儿园$$$$$绍兴枫桥学院$$$$$诸暨市人民政府暨阳街道办事处 暨阳小学风雨操场建设工程$$$$$诸暨市人民政府陶朱街道办事处  诸暨市跨湖幼儿园新建工程（主楼、保安室）$$$$$诸暨市人民政府陶朱街道办事处 诸暨市陶朱街道崇真初中新建工程$$$$$诸暨市实验小学 诸暨市西子小学新建工程$$$$$诸暨市店口镇中心学校-诸暨市店口镇杨梅桥小学扩建工程$$$$$诸暨市店口镇第二中心小学重建工程$$$$$诸暨市教育体育局  诸暨市城东初中新建工程$$$$$诸暨市枫桥镇人民政府 枫桥镇初级中学易地新建工程-1#~3#教学楼、1#~2#宿舍、报告厅、体艺及食堂楼、1#~3#门卫、配电房$$$$$诸暨市浬浦中学 诸暨市浬浦中学老女生楼重建工程$$$$$诸暨市湄池中学-诸暨市湄池中学教工宿舍楼新建及校舍改造工程（教工宿舍楼新建部分）$$$$$诸暨市越盛教育发展有限公司 诸暨市幼托一体普惠园-三江幼儿园扩建工程$$$$$诸暨市越盛教育发展有限公司 诸暨市幼托一体普惠园-东江幼儿园项目$$$$$诸暨市越盛教育发展有限公司 诸暨市幼托一体普惠园-望湖幼儿园新建工程$$$$$诸暨市越盛教育发展有限公司 诸暨市幼托一体普惠园-西子幼儿园新建工程$$$$$诸暨市越盛教育发展有限公司 诸暨市幼托一体普惠园-越都幼儿园项目 ";
        LevenshteinHelper helper = new LevenshteinHelper();
        var 中标工程列表 = Arrays.asList(中标工程名称.split("\\$\\$\\$\\$\\$"));
        var 存在考勤工程列表 = Arrays.asList(存在考勤工程名称.split("\\$\\$\\$\\$\\$"));
        List<Float> floats = new ArrayList<>();
        for (String s : 中标工程列表) {
            for (String s1 : 存在考勤工程列表) {
                float similarityRatio = helper.getSimilarityRatio(s, s1);
                if (similarityRatio > 0.7F) {
                    System.out.println(s + "," + s1);
                } else {
                    floats.add(similarityRatio);
                }
            }
        }
        floats.sort(Comparator.reverseOrder());
        System.out.println(floats);
    }

    @Test
    public void codeTest() {
        String s = HttpUtil.get("http://fundgz.1234567.com.cn/js/012414.js");
        System.out.println(s);
    }

    @Test
    public void nullTest2() {
        Object s = null;
        System.out.println(String.valueOf(s));
    }
}

