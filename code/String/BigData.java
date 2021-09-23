package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/4/7 10:03
 * @description 大数相加
 */
public class BigData {
    public static String addStrings(String num1, String num2) {


        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        int n1, n2, tmp;
        while (i >= 0 || j >= 0) {
            n1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            n2 = j >= 0 ? num2.charAt(j--) - '0' : 0;

            tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
        }

        if (carry == 1) {
            res.append(1);
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "28418138362816128348148826137141241351367324";
        String str2 = "28418138362816128348148826137141241351367324";
        //             56836276725632256696297652274282482702734648
        System.out.println(addStrings(str1, str2));
    }

}
