package com.xiw.test;

import cn.hutool.http.HttpUtil;

public class HttpTest {
    public static void main(String[] args) {
        String s = HttpUtil.get("https://zhly.lyj.zj.gov.cn:8081//sso/service/validate_ticket?ticket=123124");
        System.out.println(s);
    }
}
