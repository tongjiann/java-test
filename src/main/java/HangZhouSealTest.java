import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class HangZhouSealTest {
    private static String appId = "23432423";
    private static String secret = "908gfdljk4390f90l3j409dfgg";
    private static String method = "POST";
    private static String accept = "*/*";
    private static String contentType = "application/json;charset=UTF-8";
    private static String url = "seal/v1/rest/signFlow/create";
    private static Long timeStamp = System.currentTimeMillis();

    //签名
    public static String clientSign(String str) throws Exception {

        //计算BodyMD5
        String contentMD5 = doContentMD5(str);//http请求体JSON格式化后作为入参
        //计算待签名字符串 url：接口的相对路径,如/seal/v1/xxxx timeStamp：时间戳，与请求头参数对应，method:GET/POST, accept:与请求头参数对应，contentType：与请求头参数对应
        String strSign = originalSignStr(url, timeStamp, method, accept, contentType, contentMD5, "");
        //计算签名值
        String sign = doSignatureBase64(strSign, secret);

        System.out.println("X-Seal-App-Id:" + appId);
        System.out.println("Content-MD5:" + contentMD5);
        System.out.println("X-Seal-Ca-Signature:" + sign);
        System.out.println("X-Seal-Ca-Timestamp:" + timeStamp);
        System.out.println("strSign:" + strSign);

        return sign;
    }

    /**
     * @param url         地址
     * @param timeStamp   时间戳
     * @param method      请求方式
     * @param contentType application/json;charset=UTF-8
     * @param contentMD5  参数的MD5
     */
    public static String originalSignStr(String url, long timeStamp, String method, String accept,
                                         String contentType, String contentMD5, String headers) {
        String enter = "\n";
        String date = "";
        StringBuilder sb = new StringBuilder();
        sb.append(method).append(enter).append(timeStamp).append(enter).append(accept).append(enter)
                .append(contentMD5).append(enter).append(contentType).append(enter)
                .append(date).append(enter);
        if (StringUtils.isBlank(headers)) {
            sb.append(headers).append(url);
        } else {
            sb.append(headers).append(enter).append(url);
        }
        return sb.toString();
    }

    /***
     *
     *@param str 待计算的消息JSON
     *@return MD5 计算后摘要值的Base64编码(ContentMD5)
     *@throws Exception 加密过程中的异常信息
     */
    public static String doContentMD5(String str) throws Exception {
        byte[] md5Bytes = null;
        MessageDigest md5 = null;
        String contentMD5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            //计算md5函数
            md5.update(JSON.parse(str).toString().getBytes(StandardCharsets.UTF_8));
            //获取文件MD5的二进制数组（128位）
            md5Bytes = md5.digest();
            //把MD5摘要后的二进制数组md5Bytes使用Base64进行编码（而不是对32位的16进制字符串进行编码）
            contentMD5 = new String(Base64.encodeBase64(md5Bytes), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            String msg = MessageFormat.format("不支持此算法:{0}", e.getMessage());
            throw new Exception(msg, e);
        }
        return contentMD5;
    }

    /***
     *计算请求签名值
     *
     *@param message 待计算的消息
     *@param secret 密钥
     *@return HmacSHA256计算后摘要值的Base64编码
     *@throws Exception 加密过程中的异常信息
     */
    public static String doSignatureBase64(String message, String secret) throws Exception {
        String algorithm = "HmacSHA256";
        Mac hmacSha256;
        String digestBase64;
        try {
            hmacSha256 = Mac.getInstance(algorithm);
            byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
            //使用HmacSHA256对二进制数据消息Bytes计算摘要
            byte[] digestBytes = hmacSha256.doFinal(messageBytes);
            //把摘要后的结果digestBytes转换成十六进制的字符串
//             String digestBase64 = Hex.encodeHexString(digestBytes);
            // 把摘要后的结果digestBytes使用Base64进行编码
            digestBase64 = new String(Base64.encodeBase64(digestBytes), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            String msg = MessageFormat.format("不支持此算法:{0}", e.getMessage());
            throw new Exception(msg, e);
        } catch (InvalidKeyException e) {
            String msg = MessageFormat.format("无效的密钥规范:{0}", e.getMessage());
            throw new Exception(msg, e);
        }
        return digestBase64;
    }

    public static void main(String[] args) throws Exception {
        String str2 = "{\n" +
                "\"callbackUrl\":\"www.baidu.com/callback\",\n" +
                "\n" +
                "\"provider\":\"esign\",\n" +
                "\"subject\":\"测试流程\",\n" +
                "\"signValidity\":\"2018-08-20 12:12:00\",\n" +
                "\"docs\": [{\n" +
                "\"docFilekey\":\"$cea6f9dd-75fd-4748-9b68-a0d93ea09775$1448648398\",\n" +
                "\"docName\":\"测试文件.pdf\",\n" +
                "\"docPwd\":\"\"\n" +
                "}],\n" +
                "\"attachments\": [{\n" +
                "\"fileKey\":\"$4e64f77e-2eb1-4d6e-aa8a-ed92120b187c$587138175\",\n" +
                "\"fileName\":\"测试附件.pdf\"\n" +
                "}],\n" +
                "\"copyViewers\": [{\n" +
                "\"name\":\"孟奇\",\n" +
                "\"mobile\":\"18268850747\"\n" +
                "}],\n" +
                "\"signers\": [{\n" +
                "\"name\":\"孟奇\",\n" +
                "\"mobile\":\"18268850747\",\n" +
                "\"uscc\":\"91330108691705420Q\",\n" +
                "\"orgName\":\"杭州一渡网络技术有限公司\",\n" +
                "\"signFiles\": [{\n" +
                "\"docFilekey\":\"$cea6f9dd-75fd-4748-9b68-a0d93ea09775$1448648398\",\n" +
                "\"signPos\": [{\n" +
                "\"addSignTime\":false,\n" +
                "\"edgePosition\":2,\n" +
                "\"edgeScope\":0,\n" +
                "\"key\":\"甲方\",\n" +
                "\"keyIndex\":2,\n" +
                "\"posPage\":\"1-3,7\",\n" +
                "\"posX\":200,\n" +
                "\"posY\":0,\n" +
                "\"signDateInfos\": [{\n" +
                "\"dateFormat\":\"YYYY-MM-DD\",\n" +
                "\"fontSize\":12,\n" +
                "\"posPage\":1,\n" +
                "\"posX\":100,\n" +
                "\"posY\":100\n" +
                "}],\n" +
                "\"signType\":0,\n" +
                "\"width\":0\n" +
                "}]\n" +
                "}],\n" +
                "\"order\":1\n" +
                "}]\n" +
                "}";
//        System.out.println(clientSign(jsonObject.toString()));
        clientSign(str2);
    }
}