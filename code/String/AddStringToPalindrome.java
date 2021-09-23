package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/13 9:49
 * @description 添加最少字符使字符串整体都是回文字符串
 * 给定一个字符串str，如果可以在 str的任意位置添加字符，请返回在添加字符最少的情况下，让 str整体都是回文字符串的一种结果。
 * <p>
 * 进阶问题：给定一个字符串 str，再给定 str的最长回文子序列字符串 strlps，请返回在添加字符最少的情况下，
 * 让 str整体都是回文字符串的一种结果。进阶问题比原问题多了一个参数，请做到时间复杂度比原问题的实现低。
 */
public class AddStringToPalindrome {
    public static String getPalindrome(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        char[] chars = str.toCharArray();
        int[][] dp = process(chars);

        char[] res = new char[chars.length + dp[0][chars.length - 1]];
        int i = 0;
        int j = chars.length - 1;
        int resl = 0;
        int resr = res.length - 1;
        while (i <= j) {
            if (chars[i] == chars[j]) {
                res[resl++] = chars[i++];
                res[resr--] = chars[j--];
            } else if (dp[i][j - 1] < dp[i + 1][j]) {
                res[resl++] = chars[j];
                res[resr--] = chars[j--];
            } else {
                res[resl++] = chars[i];
                res[resr--] = chars[i++];
            }
        }
        return String.valueOf(res);
    }

    private static int[][] process(char[] chars) {
        int[][] dp = new int[chars.length][chars.length];

        for (int j = 1; j < chars.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }

    public static String getPalindrome(String str, String strlps) {
        if (str == null || "".equals(str)) {
            return "";
        }

        char[] chars = str.toCharArray();
        char[] lps = strlps.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();

        int charsLeftIndex = 0, charsRightIndex = chars.length - 1;
        int lpsLeftIndex = 0, end = lps.length >> 1;

        while (charsLeftIndex < charsRightIndex) {
            if (lpsLeftIndex != end) {
                if (chars[charsLeftIndex] != lps[lpsLeftIndex]) {
                    stringBuilder.append(chars[charsLeftIndex++]);
                } else if (chars[charsRightIndex] != lps[lpsLeftIndex]) {
                    stringBuilder.append(chars[charsRightIndex--]);
                } else {
                    charsLeftIndex++;
                    charsRightIndex--;
                    stringBuilder.append(lps[lpsLeftIndex++]);
                }
            } else {
                stringBuilder.append(chars[charsLeftIndex++]);
            }
        }

        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            stringBuilder.append(stringBuilder.charAt(i));
        }
        // 左右指针最后移动到同一位置时，👆 操作未加入StringBuilder中
        if (charsLeftIndex == charsRightIndex) {
            stringBuilder.insert(stringBuilder.length() >> 1, chars[charsLeftIndex]);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String str1 = "A1BC2D3EFGH45I6JK7LMN";
        String str2 = "1XQT122517X";

        String palindrome = getPalindrome(str1, LargestPalindromeSubsequence.getLargestPalindromeSubsequence(str1));
        String pa = getPalindrome(str1);

        System.out.println(palindrome);
        System.out.println(pa);
        System.out.println("Pa length: " + pa.length());
        System.out.println("str length: " + str1.length());
        System.out.println("add length: " + (pa.length() - str1.length()));
        System.out.println("add length by LPS: " + (str1.length() - LargestPalindromeSubsequence.getLargestPalindromeSubsequence(str1).length()));

        System.out.println("======================");

        palindrome = getPalindrome(str2, LargestPalindromeSubsequence.getLargestPalindromeSubsequence(str2));
        System.out.println(palindrome);

        pa = getPalindrome(str2);
        System.out.println(pa);
        System.out.println("Pa length: " + pa.length());
        System.out.println("str length: " + str2.length());
        System.out.println("add length: " + (pa.length() - str2.length()));
        System.out.println("add length by LPS: " + (str2.length() - LargestPalindromeSubsequence.getLargestPalindromeSubsequence(str2).length()));
        System.out.println("======================");


    }
}
