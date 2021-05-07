package code.Theory;

/**
 * @author Jevis Hoo
 * @date 2021/5/7 16:15
 * @description 36进制加法
 */
public class SumOf36 {
    public static void main(String[] args) {
        String result = addFunWithStr("1b", "2x");
        //48
        System.out.println("result = " + result);

        result = addFunWithStr("z", "1");
        //10
        System.out.println("result = " + result);

        result = addFunWithStr("z", "z");
        //1y
        System.out.println("result = " + result);
    }

    public static int getIntFromChar(char ch) {

        int ret = -1;

        if (ch >= '0' && ch <= '9') {
            ret = ch - '0';
        } else if (ch >= 'a' && ch <= 'z') {

            ret = (ch - 'a') + 10;
        }

        return ret;

    }

    private static int addChar(int ret, int jin, StringBuilder res) {
        if (ret >= 36) {
            int index = ret - 36 + jin;
            char sv = SYM.charAt(index);
            res.append(sv);
            jin = 1;
        } else {
            int index = ret + jin;
            char sv = SYM.charAt(index);
            res.append(sv);
            jin = 0;
        }

        return jin;
    }

    private static final String SYM = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static String addFunWithStr(String param1, String param2) {

        StringBuilder res = new StringBuilder();
        int len1 = param1.length();
        int len2 = param2.length();

        int i = len1 - 1;
        int j = len2 - 1;

        if (i < 0 || j < 0) {
            return null;
        }


        int jin = 0;
        while (i >= 0 && j >= 0) {

            char ch1 = param1.charAt(i);
            char ch2 = param2.charAt(j);

            int v1 = getIntFromChar(ch1);
            int v2 = getIntFromChar(ch2);

            int ret = v1 + v2;

            jin = addChar(ret, jin, res);
            i--;
            j--;
        }


        while (i >= 0) {
            char ch = param1.charAt(i);

            if (jin == 1) {
                int index = getIntFromChar(ch) + 1;
                jin = addChar(index, jin, res);
            } else {
                res.append(ch);
            }
            i--;
        }

        while (j >= 0) {
            char ch = param2.charAt(j);

            if (jin == 1) {
                int index = getIntFromChar(ch) + 1;
                jin = addChar(index, jin, res);
            } else {
                res.append(ch);
            }
            j--;
        }

        if (jin == 1) {
            res.append(jin);
        }

        StringBuilder result = res.reverse();

        return result.toString();
    }
}
