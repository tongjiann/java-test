public class UrlEncoded {
//    public static void main(String[] args) {
//        String url = "http://booking.grg.cn:7004/cloud/sso/data/getToken";
//        String clientId = "neik_system";
//        String clientSerect = "d29e8fda-0e84-41e8-97cb-17a164543c22";
//        try (CloseableHttpClient client = HttpClients.createDefault()) {
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
//            List<NameValuePair> nameValuePairs = new ArrayList<>();
//            nameValuePairs.add(new BasicNameValuePair("clientId", clientId));
//            nameValuePairs.add(new BasicNameValuePair("clientSerect", clientSerect));
//            StringEntity stringEntity = new StringEntity(URLEncodedUtils.format(nameValuePairs,"UTF-8"));
//            httpPost.setEntity(stringEntity);
//            try (CloseableHttpResponse response = client.execute(httpPost)) {
//                HttpEntity responseEntity = response.getEntity();
//                if (responseEntity != null) {
//                    JsonNode jsonNode = objectMapper.readTree(EntityUtils.toString(responseEntity, "UTF-8"));
//                    if (jsonNode != null) {
//                        logger.info("----jsonNode----" + jsonNode.toString());
//                        if (!jsonNode.findValue("code").asText().equals("ACK")) {
//                            String msg = jsonNode.findValue("message").asText();
//                            logger.error("调用广州国资委获取访问数据接口令牌接口失败：", msg);
//                        } else {
//                            accessToken = jsonNode.path("data").findValue("accessToken").asText();
//                            GzgzwLogin gzgzwLogin = new GzgzwLogin();
//                            gzgzwLogin.setAccessToken(accessToken);
//                            return gzgzwLogin;
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            logger.warn("调用广州国资委获取访问数据接口令牌接口失败", e);
//        }
//    }
}
