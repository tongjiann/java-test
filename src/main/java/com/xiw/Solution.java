package com.xiw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static int chalkReplacerBig(int[] chalk, int k, int length) {
        int idx = 0;
        while (k >= 0) {
            idx = idx % length;

            int i = chalk[idx];
            if (k < i) {
                return idx;
            }
            k = k - i;
            idx++;
        }
        return 0;
    }

    public static int chalkReplacerSmall(int[] chalk, int k, int length) {
        int sum = Arrays.stream(chalk).sum();
        k = k % sum;
        for (int i = 0; i < length; i++) {
            int i1 = chalk[i];
            if (k < i1) {
                return i;
            }
            k -= i1;
        }
        return 0;
    }

    public static int chalkReplacer(int[] chalk, int k) {
        long total = 0;
        for (int i : chalk) {
            total += i;
        }
        long res = k % total;
        int length = chalk.length;
        for (int i = 0; i < length; i++) {
            int i1 = chalk[i];
            if (res < i1) {
                return i;
            }
            res -= i1;
        }
        return 0;
    }

    public static int chalkReplacer2(int[] chalk, int k) {
        int length = chalk.length;
        if (length <= 1000) {
            return chalkReplacerSmall(chalk, k, length);
        }
        return chalkReplacerBig(chalk, k, length);
    }

    public static String maskPII(String s) {
        char c = s.charAt(0);
        if ((c >= 'a' && c <= 'z') || c >= 'A' && c <= 'Z') {
            s = s.toLowerCase();
            int i = s.indexOf("@");
            return s.charAt(0) + "*****" + s.charAt(i - 1) + s.substring(i);
        } else {
            String replaced = s.replace("(", "").replace(")", "").replace("-", "").replace("+", "");
            int length = replaced.length();
            if (length == 10) {
                return "***-***-" + replaced.substring(length - 4, length);
            } else {
                return "+" + "*".repeat(length - 10) + "***-***-" + replaced.substring(length - 4, length);
            }
        }
    }

    public static int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[0] = matrix[0];
        }
        for (int row = 1; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (col == 0) {
                    dp[row][col] = matrix[row][col] + Math.min(dp[row - 1][col], dp[row - 1][col + 1]);
                } else if (col == n - 1) {
                    dp[row][col] = matrix[row][col] + Math.min(dp[row - 1][col], dp[row - 1][col - 1]);
                } else {
                    dp[row][col] = matrix[row][col] + Math.min(dp[row - 1][col], Math.min(dp[row - 1][col - 1], dp[row - 1][col + 1]));
                }
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }

    public static int numFriendRequests(int[] ages) {
        int l = 0;
        int r = 0;
        int res = 0;
        int length = ages.length;
        Arrays.sort(ages);
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[l] <= age * 0.5 + 7) {
                l++;
            }
            while (r + 1 < length && ages[r + 1] <= age) {
                r++;
            }
            res += r - l;
        }
        return res;
    }

    public static int numFriendRequests3(int[] ages) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        Arrays.sort(ages);
        for (int age : ages) {
            if (age < 14) {
                continue;
            }
            if (map.containsKey(age)) {
                res += map.get(age);
                continue;
            }
            int ageCnt = 0;
            int min = age / 2 + 7;
            for (int innerAge : ages) {
                if (innerAge > min && innerAge <= age) {
                    ageCnt++;
                } else if (innerAge > age) {
                    break;
                }
            }
            ageCnt--;
            map.put(age, ageCnt);
            res += ageCnt;
        }
        return res;
    }

    /**
     * TLE
     */
    public static int numFriendRequests2(int[] ages) {
        int res = 0;
        int length = ages.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j == i) {
                    continue;
                }
                int iAge = ages[i];
                int jAge = ages[j];
                if (iAge * 0.5 + 7 >= jAge || iAge < jAge) {
                    continue;
                }
                res++;
            }
        }
        return res;
    }

    public static boolean validMountainArray(int[] arr) {
        int length = arr.length;
        if (length <= 2) {
            return false;
        }
        if (arr[1] <= arr[0]) {
            return false;
        }
        boolean isUp = arr[2] >= arr[1];
        for (int i = 2; i < length - 1; i++) {
            if (arr[i] == arr[i - 1]) {
                return false;
            }
            if (isUp) {
                if (arr[i] < arr[i - 1]) {
                    return false;
                }
                if (arr[i + 1] < arr[i]) {
                    isUp = false;
                }
            } else {
                if (arr[i] > arr[i - 1]) {
                    return false;
                }
            }
        }
        return arr[length - 1] < arr[length - 2];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        if (row == 1) {
            for (int i = 0; i < col; i++) {
                if (obstacleGrid[0][i] == 1) {
                    return 0;
                }
            }
            return 1;
        } else if (col == 1) {
            for (int i = 0; i < row; i++) {
                if (obstacleGrid[i][0] == 1) {
                    return 0;
                }
            }
            return 1;
        }

        int[][] dp = new int[row + 1][col + 1];
        dp[0][0] = 1;
        for (int colIdx = 1; colIdx < col; colIdx++) {
            if (dp[0][colIdx - 1] == 1 && obstacleGrid[0][colIdx] == 0) {
                dp[0][colIdx] = 1;
            } else {
                dp[0][colIdx] = 0;
            }
        }
        for (int rowIdx = 1; rowIdx < row; rowIdx++) {
            if (dp[rowIdx - 1][0] == 1 && obstacleGrid[rowIdx][0] == 0) {
                dp[rowIdx][0] = 1;
            } else {
                dp[rowIdx][0] = 0;
            }
        }
        for (int rowIdx = 1; rowIdx < row; rowIdx++) {
            for (int colIdx = 1; colIdx < col; colIdx++) {
                if (obstacleGrid[rowIdx][colIdx] == 1) {
                    dp[rowIdx][colIdx] = 0;
                } else {
                    dp[rowIdx][colIdx] = dp[rowIdx - 1][colIdx] + dp[rowIdx][colIdx - 1];
                }
            }
        }

        return dp[row - 1][col - 1];
    }

    public static int minPathSum(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        if (row == 1) {
            int res = 0;
            for (int i = 0; i < col; i++) {
                res += grid[0][i];
            }
            return res;
        } else if (col == 1) {
            int res = 0;
            for (int i = 0; i < row; i++) {
                res += grid[i][0];
            }
            return res;
        }

        int[][] dp = new int[row + 1][col + 1];
        dp[0][0] = grid[0][0];
        for (int colIdx = 1; colIdx < col; colIdx++) {
            dp[0][colIdx] = dp[0][colIdx - 1] + grid[0][colIdx];
        }
        for (int rowIdx = 1; rowIdx < row; rowIdx++) {
            dp[rowIdx][0] = dp[rowIdx - 1][0] + grid[rowIdx][0];
        }
        for (int rowIdx = 1; rowIdx < row; rowIdx++) {
            for (int colIdx = 1; colIdx < col; colIdx++) {
                dp[rowIdx][colIdx] = grid[rowIdx][colIdx] + Math.min(dp[rowIdx - 1][colIdx], dp[rowIdx][colIdx - 1]);
            }
        }

        return dp[row - 1][col - 1];
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int dp[][] = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }
        Arrays.sort(dp[n - 1]);
        return dp[n - 1][0];

    }

    public static int[][] convertTo2DArray(String input) {
        // 移除首尾的方括号并拆分字符串为行
        String[] rows = input.substring(1, input.length() - 1).split("],\\[");

        int numRows = rows.length;
        int[][] result = new int[numRows][];

        for (int i = 0; i < numRows; i++) {
            String[] elements = rows[i].split(",");
            int numElements = elements.length;
            result[i] = new int[numElements];
            for (int j = 0; j < numElements; j++) {
                result[i][j] = Integer.parseInt(elements[j]);
            }
        }

        return result;
    }

    public static char[][] convertStringToCharMatrix(String input) {
        input = input.substring(2, input.length() - 2); // 去除外层的 "[[" 和 "]]"
        String[] rowStrings = input.split("\\],\\["); // 分割成行
        int numRows = rowStrings.length;
        int numCols = rowStrings[0].split(",").length;
        char[][] charMatrix = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] colStrings = rowStrings[i].split(",");
            for (int j = 0; j < numCols; j++) {
                charMatrix[i][j] = colStrings[j].charAt(1); // 去除字符两边的引号
            }
        }

        return charMatrix;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 0; i <= length; i++) {
            for (String string : wordDict) {
                int strLength = string.length();
                if (i - strLength >= 0 && dp[i - strLength] && s.substring(i - strLength, i).equals(string)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }

    public static List<String> convertStringToList(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> stringList = null;
        try {
            stringList = objectMapper.readValue(input, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }

    public static int lengthOfLIS(int[] nums) {
        int max = 1;
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < length; i++) {
            int innerMax = 1;
            int num = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < num) {
                    innerMax = Math.max(dp[j] + 1, innerMax);
                }
            }
            dp[i] = innerMax;
            max = Math.max(innerMax, max);
        }
        return max;
    }

    public static int findNumberOfLIS(int[] nums) {
        int maxLength = 1;
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < length; i++) {
            int innerMax = 1;
            int num = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < num) {
                    innerMax = Math.max(innerMax, dp[j] + 1);
                }
            }
            dp[i] = innerMax;
            maxLength = Math.max(innerMax, maxLength);
        }
        final int max = maxLength;
        return (int) Arrays.stream(dp).filter(e -> e == max).count();
    }

    public static int[] convertStringToIntArray(String input) {
        // 去掉字符串两端的中括号
        input = input.substring(1, input.length() - 1);

        // 使用逗号分隔符拆分字符串
        String[] parts = input.split(",");

        // 创建一个整数数组，用于存储转换后的整数值
        int[] intArray = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            // 将字符串转换为int并存储在整数数组中
            intArray[i] = Integer.parseInt(parts[i].trim());
        }

        return intArray;
    }

    @Test
    public void test673() {
        System.out.println(findNumberOfLIS(convertStringToIntArray("[1,3,5,4,7]")));
    }

    @Test
    public void test139() {
        System.out.println(wordBreak("catsandog", convertStringToList("[\"cats\", \"dog\", \"sand\", \"and\", \"cat\"]")));
    }

    @Test
    public void test1190() {
        System.out.println(reverseParentheses2("(ed(et(oc))el)"));
    }

    @Test
    public void test1894() {
        System.out.println(chalkReplacer(new int[]{3, 4, 1, 2}, 25));
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

    @Test
    public void test120() {
        List<List<Integer>> lists = Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4), Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal(lists));
    }

    @Test
    public void test64() {
        int[][] ints = new int[2][2];
        ints[0] = new int[]{1, 2};
        ints[1] = new int[]{1, 1};
        System.out.println(minPathSum(ints));
    }

    @Test
    public void test825() {
        System.out.println(numFriendRequests(new int[]{108, 115, 5, 24, 82}));
    }

    @Test
    public void test931() {
        int[][] ints = new int[3][3];
        ints[0] = new int[]{2, 1, 3};
        ints[1] = new int[]{6, 5, 4};
        ints[2] = new int[]{7, 8, 9};
        System.out.println(minFallingPathSum(ints));
    }

    @Test
    public void test941() {
        System.out.println(validMountainArray(new int[]{0, 3, 2, 1}));
    }

    @Test
    public void test831() {
        System.out.println(maskPII("1(234)567-890"));
    }

    @Test
    public void test740() {
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }

    public int deleteAndEarn(int[] nums) {
        int max = 0;
        int[] help = new int[10002];
        for (int num : nums) {
            help[num] += num;
            max = Math.max(max, num);
        }
        for (int i = 2; i <= max; i++) {
            help[i] = Math.max(help[i - 1], help[i - 2] + help[i]);
        }
        return help[max];
    }

    @Test
    public void test63() {
        int[][] ints = new int[3][3];
        ints[0] = new int[]{0, 0};
        ints[1] = new int[]{1, 1};
        ints[2] = new int[]{0, 0};
        int[][] ints2 = new int[1][1];
        ints2[0] = new int[]{0, 1};
        System.out.println(uniquePathsWithObstacles(ints));
    }

    @Test
    public void test221() {
        System.out.println(maximalSquare(convertStringToCharMatrix("[[\"0\",\"1\"],[\"1\",\"0\"]]")));
    }

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        if (row == 1) {
            for (int i = 0; i < col; i++) {
                if (matrix[0][i] == '1') {
                    return 1;
                }
            }
            return 0;
        } else if (col == 1) {
            for (int i = 0; i < row; i++) {
                if (matrix[i][0] == '1') {
                    return 1;
                }
            }
            return 0;
        }
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        int max = dp[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            max = Math.max(dp[0][i], max);
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(dp[i][0], max);
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j], max);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max * max;
    }

    @Test
    public void test300() {
        System.out.println(lengthOfLIS(convertStringToIntArray("[10,9,2,5,3,7,101,18]")));
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

