package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/17 10:46
 * @description 最长公共子串
 */
public class LargestCommonSubstring {
    public static String getLcSubstringByDp(String str1, String str2) {
        if (str1 == null || str2 == null || "".equals(str1) || "".equals(str2)) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = process(chs1, chs2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < chs1.length; i++) {
            for (int j = 0; j < chs2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static int[][] process(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < str2.length; j++) {
            if (str1[0] == str2[j]) {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }


    public static String getLcSubstring(String str1, String str2) {
        if (str1 == null || str2 == null || "".equals(str1) || "".equals(str2)) {
            return "";
        }

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        //斜线开始行
        int row = 0;
        //斜线开始列
        int column = chars2.length - 1;
        //达到的全局最大值
        int max = 0;
        //子串的最后位置索引
        int end = 0;

        while (row < chars1.length) {
            int i = row;
            int j = column;
            int len = 0;

            //沿斜线不断向右下计算
            while (i < chars1.length && j < chars2.length) {
                if (chars1[i] != chars2[j]) {
                    len = 0;
                } else {
                    len++;
                }

                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }

            //斜线开始位置不断向左或向下移动
            if (column > 0) {
                column--;
            } else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static void main(String[] args) {
        String s1 = "abcd1234obj";
        String s2 = "tad12ada1234obj";

        System.out.println(getLcSubstring(s1, s2));
        System.out.println(getLcSubstringByDp(s1, s2));
    }
}
