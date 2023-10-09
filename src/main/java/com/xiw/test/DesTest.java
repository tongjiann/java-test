package com.xiw.test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

public class DesTest {


    public static void main(String[] args) throws Exception {


        String tokenId = "08D764883D3FECCAA03F039B395F9034";
//        System.out.println(tokenId+"加密后的tokenId：" + encryptStr(tokenId));

//        System.out.println("加密后的时间：" + encryptStr(String.valueOf(new Date().getTime())));

        //生态管理局加密后的时间
//        System.out.println(encryptStr(String.valueOf(new Date().getTime())));

//        encrypt("希望");

//        decrypt("So6SnvWS47wPa4qOmrjgdK0OzYjZwj58RC5j%2Fqk1kf51F7zU2GEoJdg5zGkoE04VTfhox1efNu3uSDwq1XTA9x1wSUWIGuFYins5mhEaD1srWYKC4fSirLzp9iHIeqEJKUK2JOFsleVzk6NGBxlIIJRO9sDCHxjUXfc1M8UNJY8bLPkPivj3qkQNU7cbrp2uLzZ1HXU3e527qtHYRl8uIWED4%2B6YyVTvFqYeyEagJSewl%2FTmzNiYcOYLyxigF9S4aYzXien3IIPpZxasJNSHC%2Bs4jjoPQsmpBPVqRcNkdmlCeg6QtQuDqrTNInJnHrZtODL4VEnKAiYU7MDMcNTbf3zXFguesUDQL5uwW6Sh47xRE8OPgoLdnSUxmjKFkIKUHFIKUwc5PJne%2BbwQBsLVEL5R2k0A%2BtNgPLxe2SkW9yAjUUuGSlkKUimprea49zBlzC6RC3j%2BilMPa4qOmrjgdJish%2BSzG4cvK1mCguH0oqxPucOJ2lWAU4O1zepQwuQiBSEpCNG9ymAbLPkPivj3qsiPWaDNe%2BK4zlWNNNfCp1kDGqES8WJxUDgy%2BFRJygImZDGzG4l%2F6Kc5haG1dcp7C1gorrKlieO%2FnnW2QiNH1%2BJ64gNIe4wke%2B3lZbc7r%2B0s97KEiLGYv2DuSDwq1XTA932ezNhG%2B%2B5bK1mCguH0oqxCoLBnipdMiLHFvp7BVUTQsGNF%2BPgUgnSzEvok3Lk%2FYObHGtMzAuWP9lp91HdHml%2F29ePY8tW2QivVWqlvY1iotDwjC8JkXI8T9yKUjr3RrwucCgEJY72sEwYK%2FBFx%2FfDEFPfg6r6jUkFd2HtZ9efaUj9VBRSKCjnal9OxlHrl4Wt1XdUgoNdu0tdwmHY%2FZrzcX8RQHQvO%2BXDtoF2vlzmCYTMVBB37ep2V3BriDrIpOTgy%2BFRJygImDSEr9ywlwFpYKK6ypYnjvwBPw7B5HP8W8FAyrLkaggY%3D"
//                , "utf8");

//        System.out.println(URLDecoder.decode("%22%3A%22%B7%E7%BF%D8%B4%FD%B0%EC%22%2C%22"));

        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
    }

    private static void encrypt(String s, String charset) throws Exception {
        System.out.println("==========开始加密[" + s + "]========");
        if (charset != null) {
            System.out.println("URLEncoder.encode():" + URLEncoder.encode(s, charset));
        } else {
            System.out.println("URLEncoder.encode():" + URLEncoder.encode(s));
        }
        System.out.println("DESUtil.encrypt():" + DESUtil.encryption(URLEncoder.encode(s), DESUtil.DES_KEY));
        if (charset != null) {
            System.out.println("URLEncoder.encode():" + URLEncoder.encode(DESUtil.encryption(URLEncoder.encode(s, charset), DESUtil.DES_KEY)));
        } else {
            System.out.println("URLEncoder.encode():" + URLEncoder.encode(DESUtil.encryption(URLEncoder.encode(s), DESUtil.DES_KEY)));
        }
        System.out.println("==========结束加密========");

    }

    private static String encryptStr(String json) throws Exception {

        String encryptJson = URLEncoder.encode(json);
        try {
            return URLEncoder.encode(DESUtil.encryption(encryptJson, DESUtil.DES_KEY));
        } catch (Exception e) {
            throw new Exception("加密错误:" + e.getMessage());
        }
    }

    private static void decrypt(String s, String charset) throws Exception {
        System.out.println("==========开始解密[" + s + "]========");
        if (charset != null) {
            System.out.println("URLDecoder.decode解码后的数据：" + URLDecoder.decode(s));
            s = URLDecoder.decode(s);
        } else {
            System.out.println("URLDecoder.decode解码后的数据：" + URLDecoder.decode(s, charset));
            s = URLDecoder.decode(s, charset);
        }
        System.out.println("DESUtil.decryption()解密后的数据：" + DESUtil.decryption(s, DESUtil.DES_KEY));
        if (charset != null) {
            System.out.println("URLDecoder.decode解码后的数据：" + URLDecoder.decode(DESUtil.decryption(s, DESUtil.DES_KEY)));
        } else {
            System.out.println("URLDecoder.decode解码后的数据：" + URLDecoder.decode(DESUtil.decryption(s, DESUtil.DES_KEY), charset));
        }
        System.out.println("==========结束解密========");
    }
}

