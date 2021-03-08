package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/8 14:44
 * @description 字符串的统计字符串
 * 给定一个字符串 str，返回 str 的统计字符串。例如，"aaabbadddffc"的统计字符串为
 * "a_3_b_2_a_1_d_3_f_2_c_1"。
 * <p>
 * 补充问题：给定一个字符串的统计字符串 cstr，再给定一个整数 index，返回 cstr 所代表的原始字符串上的第 index 个字符。
 * 例如，"a_1_b_100"所代表的原始字符串上第 0 个字符是'a'，第 50 个字符是'b'。
 */
public class CountString {
    public static String getCountString(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        char[] chs = str.toCharArray();
        StringBuilder res = new StringBuilder(String.valueOf(chs[0]));
        int num = 1;

        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == chs[i - 1]) {
                num++;
            } else {
                res.append("_").append(num).append("_").append(chs[i]);
                num = 1;
            }
        }
        res.append("_").append(num);

        return String.valueOf(res);
    }

    public static char getCharAt(String str, int index) {
        if (str == null || str.length() < 3) {
            return 0;
        }

        int bound = 0;
        char[] chars = str.toCharArray();
        for (int i = 2; i < str.length(); i += 4) {
            bound += chars[i] - '0';

            if (index <= bound) {
                return chars[i - 2];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String str = "aaabbadddffc";
        String cStr = getCountString(str);
        System.out.println(cStr);

        System.out.println(getCharAt(cStr, 7));
    }
}
