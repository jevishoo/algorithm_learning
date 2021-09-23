package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/21 10:43
 * @description 移除部分字符使剩余字符串是回文字符串
 * 请返回一共有多少移除方案，使得剩余字符串为回文串。
 * >>> 其中如果移除字符最后字面值相同，但位置不同也是不同的
 * <p>
 * 示例：XXY -> 4
 * 示例：ABA -> 5
 */
public class DeleteStringToPalindrome {

    public static int getDeleteStringToPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();

        int[][] dp = new int[chars.length][chars.length];

        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }

        //dp[i][j]，在空串不算回文串的情况下，求str[i...j]有多少个回文子序列
        //[i..j]
        //str[i]!=str[j]  并集
        //[i+1...j] + [i...j-1] - [i+1...j-1]
        //str[i]==str[j]  不考虑 i 或 j 的并集 + 考虑 i、j 的情况
        //[i+1...j] + [i...j-1] - [i+1...j-1] + [i+1...j-1] + 1
        for (int j = 1; j < chars.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i][j] + 1 + dp[i + 1][j - 1];
                }
            }
        }

        return dp[0][chars.length - 1];
    }

    public static int zuoWay(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length;

        int[][] dp = new int[len + 1][len + 1];

        for (int i = 0; i <= len; i++) {
            dp[i][i] = 1;
        }

        //dp[i][j]，在空串不算回文串的情况下，求str[i...j]有多少个回文子序列
        for (int subLen = 2; subLen <= len; subLen++) {
            for (int l = 1; l <= len - subLen + 1; l++) {
                int r = l + subLen - 1;

                dp[l][r] += dp[l + 1][r];
                dp[l][r] += dp[l][r - 1];

                if (chars[l - 1] == chars[r - 1]) {
                    dp[l][r] += 1;
                } else {
                    dp[l][r] -= dp[l + 1][r - 1];
                }
            }
        }
        return dp[1][len];
    }

    public static void main(String[] args) {
        String str1 = "XXY";
        String str2 = "ABA";
        String str3 = "1AB23CA21";

        System.out.println(getDeleteStringToPalindrome(str1));
        System.out.println(zuoWay(str1));
        System.out.println(getDeleteStringToPalindrome(str2));
        System.out.println(zuoWay(str2));
        System.out.println(getDeleteStringToPalindrome(str3));
        System.out.println(zuoWay(str3));
    }
}
