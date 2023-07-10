import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;

import java.util.Arrays;
import java.util.List;

public class Duplicate {
    public static void main(String[] args) throws InterruptedException {
        List<String> targetDeptIdList = Arrays.asList("331","332","334","335","337","338","340","341","342","343","345","346","348","349","351","352","354","355","357","358","360","361","363","364","366","367","369","370","372","373","375","376","378","379","381","382","384","385","387","388","390","391","393","394","396","397","399","400","402","403","405","406","408","409","411","412","414","415","417","418","420","421","423","424","425","426","427","429","430","431","432","433","434","435","436","437","438","439","441","442","443","444","446","447","448","450","451","452","453","454","456","457","458","459","460","461","462","463","464","465","466","467","470","471","472","473","474","475","476","478","479","481","482","483","484","485","486","487","488","489","490","491","492","494","495","496","497","498","499","500","501","502","503","504","505","506","507","508","509","510","511","512","514","515","516","517","518","519","520","521","523","524","525","526","527","529","530","531","532","533","534","535","536","538","539","540","541","542","543","544","546","547","549","550","551","552","553","554","555","556","557","558","559","560","566","567","568","569","570","572","573","577","578","579","580","581","582","583","584","585","586","587","588","589","590","592","593","594","595","596","597","599","600","601","602","603","604","605","606","607","608","609","610","612","613","614","615","616","617","618","619","620","621","622","623","624","625","626","627","633","634","636","637","639","640","642","643","645","646","647","648","649","650","651","652","654","655","656","657","658","659","661","662","663","664","665","666","667","668","669","670","671","672","673","674","678","679","680","681","682","683","684","686","688","689","690","691","693","694","704","728","729","730","731","732","733","735","736","738","742","743","744","746","747","749","750","751","752","753","755","756","758","761","762","763","764","765","766","767","768","769","770","771","772","773","775","776","778","779","780","781","782","783","784","785","786","787","788","792","793","794","795","796","797","798","799","800","801","803","806","807","808","809","810","812","813","814","815","816","817");
        List<String> costAllocateIdList = Arrays.asList("1659405891963289602", "1659405891963289607", "1659405891963289609", "1659405891963289610", "1659405891963289612", "1659405891963289614", "1659405891963289615", "1659405891963289618", "1659405891963289619");
        String sqlTemplate = "INSERT INTO `bd_cost_allocate_path_dept` (`id`, `cost_allocate_path_id`, `target_dept_id`, `org_id`) VALUES ({}, {}, {}, '1659453742867107842');";
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        for (String costAllocateId : costAllocateIdList) {
            for (String targetDeptId : targetDeptIdList) {
                System.out.println(CharSequenceUtil.format(sqlTemplate, snowflake.nextIdStr(), costAllocateId, targetDeptId));

            }
        }

    }
}
