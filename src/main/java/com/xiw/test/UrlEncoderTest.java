package com.xiw.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncoderTest {
    public static void main(String[] args) {
        String test = "https://192.168.123.4:8899";
        System.out.println(URLEncoder.encode(test));
        System.out.println(URLDecoder.decode("%2Fapi%2Fpublic%2Fauth-login"));
    }
}
