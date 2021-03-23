package code.String;

import java.util.Arrays;

/**
 * @author Jevis Hoo
 * @date 2021/3/23 10:40
 * @description 字符串匹配问题
 * 给定字符串 str，其中绝对不含有字符 '.'和 '*'。
 * 再给定字符串 exp，其中可以含有 '.'或 '*'，'*'字符不能是 exp的首字符，并且任意两个 '*'字符不相邻。
 * exp中的 '.'代表任何一个字符，exp中的 '*'表示 '*'的前一个字符可以有 0 个或者多个。
 * 请写一个函数，判断 str是否能被 exp匹配。
 */
public class StringMatch {
    public static boolean isValid(char[] chars1, char[] chars2) {
        for (char ch : chars1) {
            if (ch == '.' || ch == '*') {
                System.out.println("Str type is invalid");
                return false;
            }
        }

        if (chars2[0] == '*') {
            System.out.println("Exp type is invalid");
            return false;
        }

        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }

        char[] strings = str.toCharArray();
        char[] exps = exp.toCharArray();

        return isValid(strings, exps) && process(strings, exps, 0, 0);
    }

    private static boolean process(char[] strings, char[] exps, int si, int ei) {
        if (ei == exps.length) {
            // ei全部查询完，si也查询完才返回true
            return si == strings.length;
        }

        // ei为最后一个字符索引，或者ei后一个字符不是*
        if (ei == exps.length - 1 || exps[ei + 1] != '*') {
            //若si与ei位置字符匹配，不断判断后面一个字符
            return si != strings.length && (exps[ei] == strings[si] || exps[ei] == '.')
                    && process(strings, exps, si + 1, ei + 1);
        }

        // ei后一个字符是*，si位置字符与ei所代表字符匹配时进入循环。
        // 直到si达到最大，或者si位置字符与ei所代表字符不匹配跳出循环
        while (si != strings.length && (exps[ei] == strings[si] || exps[ei] == '.')) {
            // *为0或多个，即需查询string的“si及之后”字符是否能与exps的“*字符后面”字符匹配
            if (process(strings, exps, si, ei + 2)) {
                return true;
            }
            si++;
        }
        // ei后一个字符是*，但si达到最大，或者si位置字符与ei所代表字符不匹配时，
        // 检查exps的*后字符是否与si位置及之后匹配
        return process(strings, exps, si, ei + 2);
    }

    public static boolean isMatchByDp(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] strings = str.toCharArray();
        char[] exps = exp.toCharArray();
        if (!isValid(strings, exps)) {
            return false;
        }
        boolean[][] dp = initDpMap(strings, exps);

        for (int i = strings.length - 1; i > -1; i--) {
            for (int j = exps.length - 2; j > -1; j--) {
                if (exps[j + 1] != '*') {
                    dp[i][j] = (strings[i] == exps[j] || exps[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != strings.length && (strings[si] == exps[j] || exps[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (!dp[i][j]) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }

        for (boolean[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return dp[0][0];
    }

    private static boolean[][] initDpMap(char[] strings, char[] exps) {
        int sLen = strings.length;
        int eLen = exps.length;
        boolean[][] dp = new boolean[sLen + 1][eLen + 1];
        dp[sLen][eLen] = true;
        for (int j = eLen - 2; j > -1; j = j - 2) {
            if (exps[j] != '*' && exps[j + 1] == '*') {
                dp[sLen][j] = true;
            } else {//遇到一个不行的其他的就都不行
                break;
            }
        }
        //倒数第一列都是false
        //倒数第二列，只有sr只剩一个的时候可能相等
        if (sLen > 0 && eLen > 0) {
            if ((exps[eLen - 1] == '.' || strings[sLen - 1] == exps[eLen - 1])) {
                dp[sLen - 1][eLen - 1] = true;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "abc";
        String exp = "abc";

        System.out.println(isMatch(str, exp));
        System.out.println("=======");


        str = "abc";
        exp = "a.c";
        System.out.println(isMatch(str, exp));
        System.out.println("=======");

        str = "abcd";
        exp = ".";
        System.out.println(isMatch(str, exp));
        System.out.println("=======");

        str = "";
        exp = "..*";
        System.out.println(isMatch(str, exp));
        System.out.println("=======");

        str = "abbcd";
        exp = ".b*bbcd";
        System.out.println(isMatch(str, exp));

        System.out.println(isMatchByDp(str, exp));
    }
}
