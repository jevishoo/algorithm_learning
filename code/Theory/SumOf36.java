package code.Theory;

/**
 * @author Jevis Hoo
 * @since 2021/5/7 16:15
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

        result = addFunWithStr("zx1", "92");
        //1y
        System.out.println("result = " + result);
    }

    private static final String SYM = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static int getIntFromChar(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        } else {
            // if (ch >= 'a' && ch <= 'z')
            ch = Character.toLowerCase(ch);
            return (ch - 'a') + 10;
        }
    }

    private static int addChar(int sum, int jin, StringBuilder res) {
        int index = sum + jin;

        if (index >= 36) {
            index -= 36;
            char ch = SYM.charAt(index);
            res.append(ch);
            return 1;
        } else {
            char ch = SYM.charAt(index);
            res.append(ch);
            return 0;
        }
    }


    public static String addFunWithStr(String param1, String param2) {
        int len1 = param1.length() - 1;
        int len2 = param2.length() - 1;

        if (len1 < 0 || len2 < 0) {
            return len1 < 0 ? param2 : param1;
        }

        StringBuilder res = new StringBuilder();

        int jin = 0;
        while (len1 >= 0 && len2 >= 0) {

            char ch1 = param1.charAt(len1);
            char ch2 = param2.charAt(len2);

            int v1 = getIntFromChar(ch1);
            int v2 = getIntFromChar(ch2);

            int sum = v1 + v2;

            jin = addChar(sum, jin, res);
            len1--;
            len2--;
        }


        while (len1 >= 0) {
            char ch = param1.charAt(len1);

            if (jin == 1) {
                int value = getIntFromChar(ch);
                jin = addChar(value, jin, res);
            } else {
                res.append(ch);
            }
            len1--;
        }

        while (len2 >= 0) {
            char ch = param2.charAt(len2);

            if (jin == 1) {
                int value = getIntFromChar(ch);
                jin = addChar(value, jin, res);
            } else {
                res.append(ch);
            }
            len2--;
        }

        if (jin == 1) {
            res.append(jin);
        }

        return res.reverse().toString();
    }
}
