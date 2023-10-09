package com.xiw.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.*;
import java.util.stream.Collectors;

public class HutoolTest {
    public static void main01(String[] args) {
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        String s = "{\"msg\":\"err\",\"success\":false}";
        JSONObject jsonObject = JSONUtil.parseObj(s);
        if (!(boolean) jsonObject.get("success")) {
            throw new RuntimeException(
                    (String) jsonObject.get("msg"));
        }
    }

    public static void main02(String[] args) {
        List<String> s = null;
        List<String> st = new ArrayList<>();
        List<String> str = new ArrayList<>();
        str.add("123");
        Map<String,List<String>> map = new HashMap<>();
        map.put("1",s);
        map.put("12",st);
        map.put("123",str);
        System.out.println(map.get("1"));
        System.out.println(map.get("12"));
        System.out.println(map.get("123"));
    }

    public static void main03(String[] args) {
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("2");
        System.out.println("s.size()="+s.size());
    }

    public static void main(String[] args) {
        String post = HttpUtil.post("http://localhost:8050/",new HashMap<>());
        System.out.println(post);
    }
}

