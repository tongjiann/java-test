import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参数 { "requestUrl": "http://10.95.253.74:17001/bp220.go?method=init",
 * "requestData": "{}", "requestMode": "POST", "contentType":
 * "application/json;charset=UTF-8", "inputCharset": "UTF-8", "outputCharset":
 * "UTF-8", "clientOutputCharset": "UTF-8",
 * headers:[{"headerName":"Content-Type","headerValue":"application/json"},{
 * "headerName":"Accept","headerValue":"gzip"}] }
 *
 *
 * 返回值 { "ResultCode":200, "ResultMsg":"SUCCESS", "Data":"{"key","value"}"
 *
 * }
 */

@Path("/rest/transfer")
public class TransferApiResource{
    private static final Logger logger              = LoggerFactory.getLogger(TransferApiResource.class);
    private static final String requestData         = "";
    private static final String requestMode         = "POST";
    private static final String contentType         = "application/json";
    private static final String xinputCharset       = "UTF-8";
    private static final String xoutputCharset      = "UTF-8";
    private static final String clientOutputCharset = "UTF-8";

    @POST
    @Path("/send")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    public String send(String body) throws JSONException {
        JSONObject bodyAsJson = new JSONObject(body);
        // 初始化参数
        String url = bodyAsJson.getString("requestUrl");
        String data = (bodyAsJson.has("requestData")) ? bodyAsJson.getString("requestData") : requestData;
        String inputCharset = (bodyAsJson.has("inputCharset")) ? bodyAsJson.getString("inputCharset") : xinputCharset;
        String outputCharset = (bodyAsJson.has("outputCharset")) ? bodyAsJson.getString("outputCharset")
                : xoutputCharset;
        String clientOutput = (bodyAsJson.has("clientOutputCharset")) ? bodyAsJson.getString("clientOutputCharset")
                : clientOutputCharset;
        String mode = (bodyAsJson.has("requestMode")) ? bodyAsJson.getString("requestMode") : requestMode;
        String type = (bodyAsJson.has("contentType")) ? bodyAsJson.getString("contentType") : contentType;

        JSONArray headArray = new JSONArray();
        if(bodyAsJson.has("headers")){
            headArray = bodyAsJson.getJSONArray("headers");
        }
        Map<String, String> headers = jsonArray2Map(headArray);

        String result = getReturn(url, data, inputCharset, outputCharset, clientOutput, mode, type, headers);
        return result;
    }

    /**
     * @Title: jsonArray2Map
     * @Description:jsonarray转换成map
     * @param headArray
     * @return Map<String,String>
     */

    private Map<String, String> jsonArray2Map(JSONArray headArray) throws JSONException {
        Map<String, String> map = new HashMap<String, String>();
        int len = headArray.length();
        for(int i = 0; i < len; i++){
            JSONObject object = headArray.getJSONObject(i);
            map.put(object.getString("headerName"), object.getString("headerValue"));
        }
        return map;
    }

    @SuppressWarnings({ "deprecation", "resource" })
    public static String getReturn(String restUrl, String requestData, String inputCharset, String outputCharset,
                                   String clientOutputCharset, String requestMode, String contentType, Map<String, String> headers) throws JSONException {
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(AllClientPNames.CONNECTION_TIMEOUT, 30000);
        httpClient.getParams().setParameter(AllClientPNames.SO_TIMEOUT, 30000);
        // httpClient.getParams().setParameter("http.protocol.version",
        // HttpVersion.HTTP_1_1);
        httpClient.getParams().setParameter("http.protocol.content-charset", inputCharset);
        HttpGet httpGet = null;
        HttpPost httpPost = null;
        JSONObject result = new JSONObject();
        HttpEntity entity = null;
        HttpResponse response = null;
        try{
            if(TransferApiResource.requestMode.equalsIgnoreCase(requestMode)){
                // POST请求
                httpPost = new HttpPost(restUrl);
                StringEntity reqEntity = new StringEntity(requestData, inputCharset);
                reqEntity.setContentType(contentType);
                reqEntity.setContentEncoding(inputCharset);
                httpPost.setEntity(reqEntity);
                // httpPost.setHeader("Accept", MediaType.APPLICATION_JSON);

                // 设置header
                for(Map.Entry<String, String> entry : headers.entrySet()){
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }

                response = httpClient.execute(httpPost);

            } else{
                httpGet = new HttpGet(restUrl);
                // httpGet.setHeader("Accept", MediaType.APPLICATION_JSON);

                // 设置header
                for(Map.Entry<String, String> entry : headers.entrySet()){
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }

                response = httpClient.execute(httpGet);
            }

            int status = response.getStatusLine().getStatusCode();
            logger.info("网络状态:status=" + status);
            if(HttpStatus.SC_OK == status){
                entity = response.getEntity();
                String ret = "";
                if(entity != null){
                    ret = new String(EntityUtils.toString(entity).getBytes(outputCharset), clientOutputCharset);
                }

                result.put("ResultCode", HttpStatus.SC_OK);
                result.put("ResultMsg", "SUCCESS");
                result.put("Data", ret);

            } else{
                entity = response.getEntity();
                String error = new String(EntityUtils.toString(entity).getBytes(outputCharset), clientOutputCharset);
                String ret = "网络错误:错误代码" + status + "," + error;

                result.put("ResultCode", status);
                result.put("ResultMsg", "FAIL");
                result.put("Data", ret);
            }
            return result.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            result.put("ResultCode", 500);
            result.put("ResultMsg", "EXCEPTION");
            return result.toString();
        }
        finally{
            if(null != entity){
                try{
                    EntityUtils.consume(entity);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != httpGet && httpGet.isAborted()){
                httpGet.abort();
            }
            if(null != httpPost && httpPost.isAborted()){
                httpPost.abort();
            }
            if(null != httpClient){
                httpClient.getConnectionManager().shutdown();
            }
        }

    }
}