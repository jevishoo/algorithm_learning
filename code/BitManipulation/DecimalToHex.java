package code.BitManipulation;

/**
 * @author Jevis Hoo
 * @date 2021/5/2 20:22
 * @description
 */
public class DecimalToHex {
    private static final String[] ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {
        int number = 32767;
        System.out.println(getHexMethod1(number));
        System.out.println(getHexMethod2(number));
    }

    public static String getHexMethod1(int num) {
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            res.append(ARRAY[num % 16]);
            num /= 16;
        }

        return res.reverse().toString();
    }

    public static String getHexMethod2(int num) {
        StringBuilder res = new StringBuilder();

        while (num > 0) {
            int tmp = num & 15;
            res.append(ARRAY[tmp]);
            num = num >>> 4;
        }

        return res.reverse().toString();
    }
}
