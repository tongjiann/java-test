package com.xiw;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {


    public static void test43(String n1, String n2) {
        System.out.println("测试结果: " + multiply(n1, n2));
        System.out.println("标准结果: " + new BigDecimal(n1).multiply(new BigDecimal(n2)).toPlainString());
    }

    public static String multiply(String num1, String num2) {
        int i = num1.length();
        int j = num2.length();
        int mul;
        int[] ints = new int[i + j];
        Arrays.fill(ints, 0);
        for (int n1Pos = i - 1; n1Pos >= 0; n1Pos--) {
            for (int n2Pos = j - 1; n2Pos >= 0; n2Pos--) {
                mul = (num1.charAt(n1Pos) - '0') * (num2.charAt(n2Pos) - '0');
                ints[n1Pos + n2Pos + 1] = ints[n1Pos + n2Pos + 1] + mul % 10;
                if (ints[n1Pos + n2Pos + 1] >= 10) {
                    ints[n1Pos + n2Pos]++;
                    ints[n1Pos + n2Pos + 1] = ints[n1Pos + n2Pos + 1] - 10;
                }
                ints[n1Pos + n2Pos] = ints[n1Pos + n2Pos] + mul / 10;
                if (ints[n1Pos + n2Pos] >= 10) {
                    ints[n1Pos + n2Pos - 1]++;
                    ints[n1Pos + n2Pos] = ints[n1Pos + n2Pos] - 10;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        boolean isInFirst = true;
        for (int anInt : ints) {
            if (isInFirst && anInt == 0) {
                continue;
            }
            isInFirst = false;
            stringBuilder.append(anInt);
        }
        return stringBuilder.toString();
    }

    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (o1, o2) -> {
            char o1LastChar = o1.charAt(o1.length() - 1);
            char o2LastChar = o2.charAt(o2.length() - 1);
            if (o1LastChar >= '0' && o1LastChar <= '9') {
                if (o2LastChar >= '0' && o2LastChar <= '9') {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if (o2LastChar >= '0' && o2LastChar <= '9') {
                    return -1;
                } else {
                    int i = o1.indexOf(" ");
                    int j = o2.indexOf(" ");
                    String o1Sub = o1.substring(i);
                    String o2Sub = o2.substring(j);
                    int compared = o1Sub.compareTo(o2Sub);
                    if (compared == 0) {
                        return o1.substring(0, i).compareTo(o2.substring(0, j));
                    }
                    return compared;
                }
            }
        });
        return logs;
    }

    public static String[] reorderLogFiles2(String[] logs) {
        if (logs.length == 0) {
            return logs;
        }
        List<String> aList = new ArrayList<>();
        List<String> bList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String log : logs) {
            String[] s = log.split(" ", 2);
            String k = s[0];
            String v = s[1];
            char c = v.charAt(0);
            if (c >= 'a' && c <= 'z') {
                map.computeIfAbsent(v, key -> new ArrayList<>());
                List<String> strings = map.get(v);
                strings.add(k);
            } else {
                bList.add(log);
            }
        }
        List<String> collect = map.keySet().stream().sorted().collect(Collectors.toList());
        for (String s : collect) {
            List<String> strings = map.get(s);
            aList.addAll(strings.stream().sorted().map(e -> e + " " + s).collect(Collectors.toList()));
        }
        aList.addAll(bList);
        return aList.toArray(new String[0]);
    }

    public static String reverseParentheses(String s) {
        return getRes(s, 0).getRes();
    }

    public static String reverseParentheses2(String s) {
        Deque<String> stack = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(stringBuilder.toString());
                stringBuilder.setLength(0);
            } else if (c == ')') {
                stringBuilder.reverse();
                stringBuilder.insert(0, stack.pop());
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }


    private static Simple getRes(String s, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = s.length();
        int idx = offset;
        while (idx < length) {
            char c = s.charAt(idx);
            if (c >= 'a' && c <= 'z') {
                stringBuilder.append(c);
                idx++;
            } else if (c == '(') {
                Simple simple = getRes(s.substring(idx), 1);
                stringBuilder.append(simple.getRes());
                idx += simple.getLen();
            } else if (c == ')') {
                return new Simple(idx + offset, stringBuilder.reverse().toString());
            }
        }
        return new Simple(0, stringBuilder.toString());
    }

    @Test
    public void test1190() {
        System.out.println(reverseParentheses2("(ed(et(oc))el)"));
    }

    @Test
    public void test97() {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        int s1Len = s1.length();
        int s2Len = s2.length();
        int s3Len = s3.length();
        if (s1Len + s2Len != s3Len) {
            return false;
        }
        if (s3Len == 0) {
            return true;
        }
        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1Len; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s2Len; i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[s1Len][s2Len];
    }

    @Test
    public void test43() {
        test43("123214124", "52353252352456");
    }

    @Test
    public void test937() {
        String[] logs = Arrays.asList("dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero")
                .toArray(new String[0]);
        String[] x = reorderLogFiles(logs);
        Arrays.stream(x).forEach(System.out::println);
    }

    private static class Simple {

        private int len;

        private String res;

        public Simple(int len, String res) {
            this.len = len;
            this.res = res;
        }

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }

    }

}
// leetcode submit region end(Prohibit modification and deletion)
