import cn.hutool.core.util.ReUtil;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void regex(String sourceStr, String regexStr) {
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(sourceStr);
        matcher.replaceAll("1");
        System.out.println("pattern:" + pattern);
        System.out.println(matcher.matches());
        String[] split = "th?id=1".split("th\\?id=");
        System.out.println(split);
    }

    @Test
    public void testRegex() {
        String text = "{\"modelId\":\"6CC0E19A-CC02-42C2-81BD-72F6A4416AD0\",\"bounds\":{\"lowerRight\":{\"x\":1328,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"properties\":{\"process_id\":\"zjbx\",\"name\":\"直接报销\",\"documentation\":\"\",\"process_author\":\"\",\"process_version\":\"\",\"process_namespace\":\"http://www.flowable.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"childShapes\":[{\"resourceId\":\"sid-F9B5DF1E-F9F6-4D2D-8119-5EA8F647C529\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-EF39F755-459C-4D2D-B0CD-5C0BAED2F604\"}],\"bounds\":{\"lowerRight\":{\"x\":150,\"y\":555},\"upperLeft\":{\"x\":120,\"y\":525}},\"dockers\":[]},{\"resourceId\":\"sid-A7D0E71A-E6DC-4140-A469-E0957A736769\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"usertaskassignment\":\"\",\"prioritydefinition\":\"\",\"tasklisteners\":\"\",\"skipexpression\":\"\",\"approvalpoints\":\"\",\"privilege\":\"\",\"notify\":\"\",\"isprint\":\"\",\"inform\":\"\",\"printbutton\":\"\",\"cross\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"signmode\":\"\",\"taskconclusion\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-E89967CA-E56F-48D9-A475-250811308E2C\"}],\"bounds\":{\"lowerRight\":{\"x\":268,\"y\":580},\"upperLeft\":{\"x\":168,\"y\":500}},\"dockers\":[]},{\"resourceId\":\"sid-EF39F755-459C-4D2D-B0CD-5C0BAED2F604\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-A7D0E71A-E6DC-4140-A469-E0957A736769\"}],\"bounds\":{\"lowerRight\":{\"x\":167.71875,\"y\":540},\"upperLeft\":{\"x\":150.265625,\"y\":540}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-A7D0E71A-E6DC-4140-A469-E0957A736769\"}},{\"resourceId\":\"sid-EF267415-4715-4C7D-B5F7-4A194818DA13\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"usertaskassignment\":{\"assignment\":{\"type\":\"idm\",\"idm\":{\"type\":\"user\",\"assignee\":{\"id\":\"00000000-0000-0000-0000-000000000000\",\"creatorId\":\"00000000-0000-0000-0000-000000000000\",\"creatorNickname\":\"系统管理员\",\"createTime\":1527523200000,\"username\":\"admin\",\"nickname\":\"管理员\",\"letter\":\"xtgly\",\"uniteId\":\"admin\",\"gender\":\"男\",\"password\":null,\"organizationId\":\"0E8A7BEC-5D6C-4626-9C66-47C97A9ACC84\",\"esealAccountId\":null,\"employeeNumber\":null,\"lastLoginTime\":1650784935000,\"failedTimes\":0,\"isModifyInitialPassword\":1,\"modifyPasswordTime\":1624452198000,\"gradeId\":\"C53FE623-A709-40BE-B106-E031EA50D5CF\",\"positionGradeId\":\"81D0E75F-E25E-4EBD-8E98-53FC08FC6C67\",\"remark\":null,\"telephone\":null,\"mobilePhone\":null,\"governmentCard\":null,\"governmentBank\":null,\"governmentCity\":null,\"bonusCard\":null,\"bonusBank\":null,\"bonusCity\":null,\"paymentCard\":null,\"paymentBank\":null,\"paymentCity\":null,\"sealId\":\"448BE53E-B8E5-4C4F-A917-46AEEDB7ACE6\",\"signaturePath\":\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABkAKQDASIAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAAn/xAAUEAEAAAAAAAAAAAAAAAAAAAAA/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/AKpgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA//Z\",\"headPath\":\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCACCAGQDASIAAhEBAxEB/8QAFQABAQAAAAAAAAAAAAAAAAAAAAn/xAAUEAEAAAAAAAAAAAAAAAAAAAAA/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/AKpgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA//Z\",\"portalId\":null,\"identityNumber\":null,\"departmentId\":\"0270BC59-784C-4464-BEE6-00C51871CAB8\",\"departmentName\":\"东城街道\",\"userPosition\":null,\"inductionTime\":1611763200000,\"driverFlag\":0,\"isAdministrator\":1,\"supervisorEmployeeId\":null,\"governmentBankNumber\":null,\"paymentBankNumber\":null,\"isForbidden\":0,\"vehicleStandard\":null,\"vehicleStandardControl\":null,\"position\":null,\"content1\":null,\"content2\":null,\"content3\":null,\"content4\":null,\"content5\":null,\"content6\":null,\"content7\":null,\"content8\":null,\"content9\":null,\"content10\":null,\"positionList\":null,\"autoId\":11,\"$$hashKey\":\"object:1726\"}}}},\"prioritydefinition\":\"\",\"tasklisteners\":\"\",\"skipexpression\":\"\",\"approvalpoints\":\"\",\"privilege\":\"\",\"notify\":\"\",\"isprint\":\"\",\"inform\":\"\",\"printbutton\":\"\",\"cross\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"signmode\":\"\",\"taskconclusion\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-3923DBF9-5817-4B95-9AA3-F3B85DAB7852\"}],\"bounds\":{\"lowerRight\":{\"x\":415,\"y\":580},\"upperLeft\":{\"x\":315,\"y\":500}},\"dockers\":[]},{\"resourceId\":\"sid-E89967CA-E56F-48D9-A475-250811308E2C\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-EF267415-4715-4C7D-B5F7-4A194818DA13\"}],\"bounds\":{\"lowerRight\":{\"x\":314.6171875,\"y\":540},\"upperLeft\":{\"x\":268.3828125,\"y\":540}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-EF267415-4715-4C7D-B5F7-4A194818DA13\"}},{\"resourceId\":\"sid-2A79E58B-4FF1-4817-9A7C-1C86DE4573E3\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"EndNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":538,\"y\":554},\"upperLeft\":{\"x\":510,\"y\":526}},\"dockers\":[]},{\"resourceId\":\"sid-3923DBF9-5817-4B95-9AA3-F3B85DAB7852\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-2A79E58B-4FF1-4817-9A7C-1C86DE4573E3\"}],\"bounds\":{\"lowerRight\":{\"x\":509.3359375,\"y\":540},\"upperLeft\":{\"x\":415.6875,\"y\":540}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"sid-2A79E58B-4FF1-4817-9A7C-1C86DE4573E3\"}}],\"stencil\":{\"id\":\"BPMNDiagram\"},\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\",\"url\":\"../editor/stencilsets/bpmn2.0/bpmn2.0.json\"}}";
        String originStr = ".*\"assignee\":.\"id\":\"00000000-0000-0000-0000-000000000000\".*?}";
        System.out.println(Pattern.matches(originStr,text));
        String p = "\"assignee\":.\"id\":\"00000000-0000-0000-0000-000000000000\".*?}";

        Pattern pattern = Pattern.compile(p);
        String target = "\"assignee\":{\"id\":\"" + UUID.randomUUID().toString().toUpperCase(Locale.ROOT) + "\",\"nickname\":\"" + UUID.randomUUID().toString().toUpperCase(Locale.ROOT) + "\",\"username\":\"" + UUID.randomUUID().toString().toUpperCase(Locale.ROOT) + "\"}";
        String assignee = RegExUtils.replaceAll(text, pattern, target);
        System.out.println(assignee);
    }

    @Test
    public void testRegexReplace(){
        List<String > uuidList = new ArrayList<String>();
        uuidList.add(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
        String text = "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('1EBD0F5C-F296-4427-AB0A-370F59950346', '1D086B95-1670-4C2B-8508-6636C04EC536', '核销', 4, '核销', 1, NULL, 2, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:04:56', '00000000-0000-0000-0000-000000000000', '系统管理员', '2022-01-19 13:42:45');\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('F1CEB267-82FF-4DCD-B8CE-1894F20E80F8', '1D086B95-1670-4C2B-8508-6636C04EC536', '招标书审批', 5, '招标书审批', 1, NULL, 3, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:05:09', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('B2B98806-0EAC-476F-B03D-B22FB84A44E3', '1D086B95-1670-4C2B-8508-6636C04EC536', '签订合同', 6, '签订合同', 1, NULL, 4, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:05:24', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('6E36BA18-EFEA-4CBA-BD68-D92ACE416817', '1D086B95-1670-4C2B-8508-6636C04EC536', '确认收款', 7, '确认收款', 1, NULL, 5, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:05:44', '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:08:40');\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('1BF560E2-91C8-4DBE-8654-2C82E852EE17', '1D086B95-1670-4C2B-8508-6636C04EC536', '维修费用申请', 8, '维修费用申请', 1, NULL, 6, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:08:15', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('73DFA0F4-6AAC-4FE8-8901-2B19A8DF82D4', '1D086B95-1670-4C2B-8508-6636C04EC536', '会议完成', 9, '会议取消', 1, NULL, 7, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:08:35', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('4ED077EC-EBB3-4B85-8E74-B16C3CF11E7A', '1D086B95-1670-4C2B-8508-6636C04EC536', '会议取消', 10, '会议取消', 1, NULL, 8, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:08:54', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('5ED569E7-E7DF-4098-8098-0EFD7C54E55D', '1D086B95-1670-4C2B-8508-6636C04EC536', '项目信息调整', 11, '项目信息调整', 1, NULL, 9, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:09:15', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('EE4AE965-C45F-4F29-B776-3DA5445E0CF1', '1D086B95-1670-4C2B-8508-6636C04EC536', '采购结果确认', 12, '采购结果确认', 1, NULL, 10, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:09:50', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('17E484E3-847F-441E-B141-AB3A85E9F4F5', '1D086B95-1670-4C2B-8508-6636C04EC536', '维修结果确认', 13, '维修结果确认', 1, NULL, 11, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:11:01', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('DD85BBB5-A1B0-4610-B723-EB7EC18FE76C', '1D086B95-1670-4C2B-8508-6636C04EC536', '登记批复结果', 14, '登记批复结果', 1, NULL, 12, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:13:58', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('449D74F2-765E-4BBE-911D-6F9C1BA1B5E0', '1D086B95-1670-4C2B-8508-6636C04EC536', '结项', 15, '结项', 1, NULL, 13, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:15:45', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('064FDA00-AE01-423E-8A4A-6EAD49E0F07C', '1D086B95-1670-4C2B-8508-6636C04EC536', '作废', 16, '作废', 1, NULL, 14, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:16:42', '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:16:48');\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('1EA2528E-6E6D-4DDC-A7F8-428BC4D93F20', '1D086B95-1670-4C2B-8508-6636C04EC536', '招标文件审批', 17, '招标文件审批', 1, NULL, 15, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:17:06', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('8E454E31-0A10-47B9-852E-6EE2028BCF7C', '1D086B95-1670-4C2B-8508-6636C04EC536', '招标结果登记', 18, '招标结果登记', 1, NULL, 16, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:17:53', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('207D1768-D8C4-45F3-8350-8ADD75564639', '1D086B95-1670-4C2B-8508-6636C04EC536', '修改联系结果', 19, '修改联系结果', 1, NULL, 17, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:19:09', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('2F10FCBD-FE44-484B-A83A-50F3FB9AC006', '1D086B95-1670-4C2B-8508-6636C04EC536', '关闭活动', 20, '关闭活动', 1, NULL, 18, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:19:29', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('128EAB84-1273-4191-ABB7-9F0C51201FBA', '1D086B95-1670-4C2B-8508-6636C04EC536', '抽取专家', 21, '抽取专家', 1, NULL, 19, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:20:03', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('3EF7D3CE-709B-45CB-95E5-4EA8A9D4902C', '1D086B95-1670-4C2B-8508-6636C04EC536', '联系专家', 22, '联系专家', 1, NULL, 20, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:20:26', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('B4A04530-21DE-406A-8777-004F4D464E68', '1D086B95-1670-4C2B-8508-6636C04EC536', '活动评价', 23, '活动评价', 1, NULL, 21, '00000000-0000-0000-0000-000000000000', '系统管理员', '2021-08-11 17:20:55', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('8E379898-A91F-4E36-81FE-D0A097BD0A1F', '1D086B95-1670-4C2B-8508-6636C04EC536', '补传附件', 29, '补传附件', 1, NULL, 22, '00000000-0000-0000-0000-000000000000', '系统管理员', '2022-01-19 11:37:28', NULL, NULL, NULL);\n" +
                "INSERT INTO `bd_custom_ledger_operation` (`id`, `custom_ledger_id`, `name`, `type`, `title`, `is_display`, `show_formula`, `code`, `creator_id`, `creator_nickname`, `create_time`, `modifier_id`, `modifier_nickname`, `modify_time`) VALUES ('40BCD2DC-B3CB-46C2-96DD-9451962226F1', '1D086B95-1670-4C2B-8508-6636C04EC536', '报销', 3, '报销', 1, NULL, 1, '00000000-0000-0000-0000-000000000000', '系统管理员', '2022-01-19 11:39:20', '00000000-0000-0000-0000-000000000000', '系统管理员', '2022-01-19 13:44:31');";
        String success = ReUtil.replaceAll(text, "VALUES \\(\\'.*?\\',", "VALUES ('"+UUID.randomUUID().toString().toUpperCase(Locale.ROOT)+"', ");
        System.out.println(success);
    }
}
