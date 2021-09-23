package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/10 15:07
 * @description 翻转字符串
 * 给定一个字符类型的数组chas，请在单词间做逆序调整。只要做到单词的顺序逆序即可，对空格的位置没有特别要求。
 * <p>
 * 补充问题：给定一个字符类型的数组 chas 和一个整数 size，
 * 请把大小为 size 的左半区整体移到右半区，右半区整体移到左边。
 * <p>
 * 如果 chas 长度为 N，两道题都要求时间复杂度为 O(N)，额外空间复杂度为 O(1)。
 */
public class RotateString {
    public static void rotateWord(char[] chars) {
        for (int i = 0; i < chars.length >> 1; i++) {
            swap(chars, i, chars.length - i - 1);
        }

        int left = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                for (int j = 0; j <= (i - left - 1) >> 1; j++) {
                    swap(chars, left + j, i - j - 1);
                }
                left = i + 1;
            } else if (i == chars.length - 1) {
                for (int j = 0; j <= (i - left) >> 1; j++) {
                    swap(chars, left + j, i - j);
                }
            }
        }
    }

    public static String rotateString(char[] chars, int size) {
        for (int i = 0; i < size >> 1; i++) {
            swap(chars, i, size - i - 1);
        }
        for (int i = 0; i < (chars.length - size) >> 1; i++) {
            swap(chars, size + i, chars.length - i - 1);
        }

        for (int i = 0; i < chars.length >> 1; i++) {
            swap(chars, i, chars.length - i - 1);
        }

        return String.valueOf(chars);
    }


    public static String rotateString2(char[] chars, int size) {
        int lPart = size;
        int rPart = chars.length - size;

        int start = 0;
        int end = chars.length - 1;
        int min = Math.min(lPart, rPart);
        while (true) {
            for (int i = 0; i < min; i++) {
                swap(chars, start + i, end - min + i + 1);
            }
            if (lPart == rPart) {
                break;
            } else if (lPart < rPart) {
                end -= min;
                rPart = rPart - lPart;
            } else {
                start += min;
                lPart = lPart - rPart;
            }
            min = Math.min(lPart, rPart);
        }

        return String.valueOf(chars);
    }

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }


    public static String rotateString3(char[] chars, int size) {
        StringBuilder res = new StringBuilder();
        for (int i = size; i < size + chars.length; i++) {
            res.append(chars[i % chars.length]);
        }
        return res.toString();
    }


    public static void main(String[] args) {
        char[] chars = "Time is water".toCharArray();
        System.out.println(chars);
        rotateWord(chars);
        System.out.println(chars);

        chars = "E.STAR".toCharArray();
        System.out.println(chars);
        System.out.println(rotateString(chars, 4));
        System.out.println(rotateString2(chars, 2));

        chars = "12345ABCDEFGF".toCharArray();
        System.out.println("origin: " + String.valueOf(chars));
        System.out.println(rotateString(chars, 8));
        System.out.println(rotateString2(chars, 5));

        String string = "lrloseumgh";
        System.out.println("origin: " + string);
        System.out.println(rotateString2(string.toCharArray(), 6));
        System.out.println(rotateString3(string.toCharArray(), 6));
    }
}
