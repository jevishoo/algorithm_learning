package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/10 15:07
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

    public static void rotateString(char[] chars, int size) {
        for (int i = 0; i < size >> 1; i++) {
            swap(chars, i, size - i - 1);
        }
        for (int i = 0; i < (chars.length - size) >> 1; i++) {
            swap(chars, size + i, chars.length - i - 1);
        }

        for (int i = 0; i < chars.length >> 1; i++) {
            swap(chars, i, chars.length - i - 1);
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }


    public void rotateString2(char[] chas, int size) {
        if (chas == null || size <= 0 || size >= chas.length) {
            return;
        }
        int start = 0;
        int end = chas.length - 1;
        int lpart = size;
        int rpart = chas.length - size;
        int s = Math.min(lpart, rpart);
        int d = lpart - rpart;
        while (true) {
            exchange(chas, start, end, s);
            if (d == 0) {
                break;
            } else if (d > 0) {
                start += s;
                lpart = d;
            } else {
                end -= s;
                rpart = -d;
            }
            s = Math.min(lpart, rpart);
            d = lpart - rpart;
        }
    }

    public void exchange(char[] chars, int start, int end, int size) {
        int i = end - size + 1;
        char tmp;
        while (size-- != 0) {
            tmp = chars[start];
            chars[start] = chars[i];
            chars[i] = tmp;
            start++;
            i++;
        }
    }

    public static void main(String[] args) {
        char[] chars = "Time is water".toCharArray();
        System.out.println(chars);
        rotateWord(chars);
        System.out.println(chars);

        chars = "E.STAR".toCharArray();
        System.out.println(chars);
        rotateString(chars, 2);
        System.out.println(chars);
    }
}
