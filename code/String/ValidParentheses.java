package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/17 9:30
 * @description 括号字符串的有效性和最长有效长度
 * 给定一个字符串str，判断是不是整体有效的括号字符串。
 * <p>
 * 补充问题：给定一个括号字符串str，返回最长的有效括号子串长度。
 */
public class ValidParentheses {

    public static boolean isValid(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (char ch : chars) {
            if (ch != '(' && ch != ')') {
                return false;
            }
            if (ch == ')' && --count < 0) {
                return false;
            }

            if (ch == '(') {
                count++;
            }
        }

        return count == 0;
    }


    public static int maxValidLength(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];
        int pre = 0, result = 0;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        String s1 = "(()())";
        String s2 = "(())";
        String s3 = "()";
        String s4 = "())";
        String s5 = "()a()";
        String s6 = ")(()";

        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
        System.out.println(isValid(s4));
        System.out.println(isValid(s5));
        System.out.println(isValid(s6));


        String str1 = "(()())";
        String str2 = "())";
        String str3 = "()(()()(";

        System.out.println(maxValidLength(str1));
        System.out.println(maxValidLength(str2));
        System.out.println(maxValidLength(str3));
    }
}
