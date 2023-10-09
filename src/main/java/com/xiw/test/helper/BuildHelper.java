package com.xiw.test.helper;


import org.junit.Test;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-06-27 15:42
 */
public class BuildHelper {
    @Test
    public void test() {
        String authDomain = "http://21.7.64.19:28081/sim";
        String rolePositionSearch = "/api/v3/authority/user/role";
        StringBuilder builder = new StringBuilder();
        builder.append(rolePositionSearch);
        try {
            builder.append("?appId=NKPT&userId=").append("zjt00002167");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String uri = builder.toString();
        builder = new StringBuilder();
        builder.append(authDomain).append(uri);
        String url = builder.toString();
        System.out.println(url);
    }
}
