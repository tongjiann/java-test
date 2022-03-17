import cn.hutool.core.codec.Base64Encoder;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author xiwang
 * @date 2022-03-16 17:42
 */
public class EncodeAndDecodeTest {
    @Test
    public void testEncode(){
        String  s= "<rd><iSeqno>202203250000000</iSeqno><ReimburseNo/><ReimburseNum/><StartDate/><StartTime/><PayType>1</PayType><PayAccNo>1202022719927388888</PayAccNo><PayAccNameCN>栗雪镁英保号午脏澈乓忘管鳞嘴易</PayAccNameCN><PayAccNameEN/><RecAccNo>1202051309900024733</RecAccNo><RecAccNameCN>栗雪镁英保号午乓忘嘴易憶褪苹囱夕爱澈裹榛孟嘴易</RecAccNameCN><RecAccNameEN/><SysIOFlg>1</SysIOFlg><IsSameCity/><Prop/><RecICBCCode/><RecCityName/><RecBankNo/><RecBankName/><CurrType>001</CurrType><PayAmt>1</PayAmt><UseCode/><UseCN/><EnSummary/><PostScript/><Summary/><Ref/><Oref/><ERPSqn/><BusCode/><ERPcheckno/><CrvouhType/><CrvouhName/><CrvouhNo/><BankType>102</BankType><FileNames/><Indexs/><PaySubNo/><RecSubNo/><MCardNo/><MCardName/></rd><rd><iSeqno>202203250000000</iSeqno><ReimburseNo/><ReimburseNum/><StartDate/><StartTime/><PayType>1</PayType><PayAccNo>1202022719927388888</PayAccNo><PayAccNameCN>栗雪镁英保号午脏澈乓忘管鳞嘴易</PayAccNameCN><PayAccNameEN/><RecAccNo>1202051309900024733</RecAccNo><RecAccNameCN>栗雪镁英保号午乓忘嘴易憶褪苹囱夕爱澈裹榛孟嘴易</RecAccNameCN><RecAccNameEN/><SysIOFlg>1</SysIOFlg><IsSameCity/><Prop/><RecICBCCode/><RecCityName/><RecBankNo/><RecBankName/><CurrType>001</CurrType><PayAmt>1</PayAmt><UseCode/><UseCN/><EnSummary/><PostScript/><Summary/><Ref/><Oref/><ERPSqn/><BusCode/><ERPcheckno/><CrvouhType/><CrvouhName/><CrvouhNo/><BankType>102</BankType><FileNames/><Indexs/><PaySubNo/><RecSubNo/><MCardNo/><MCardName/></rd>";
        byte[] gbks = s.getBytes(Charset.forName("gbk"));
        String encode = Base64Encoder.encode(gbks);
        System.out.println(encode);

    }
}
