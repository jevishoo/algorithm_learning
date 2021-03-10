package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/10 14:18
 * @description 字符串的调整与替换
 * 给定一个字符类型的数组 chas[]，chas 右半区全是空字符，左半区不含有空字符。
 * 现在想将左半区中所有的空格字符替换成 "%20"。
 * 替换函数的时间复杂度为 O(N)，额外空间复杂度为 O(1)。
 * <p>
 * 给定一个字符类型的数组 chas[]，其中只含有数字字符和 “*”字符。
 * 现在想把所有的 “*”字符挪到 chas 的左边，数字字符挪到 chas 的右边。请完成调整函数。
 * 1．调整函数的时间复杂度为 O(N)，额外空间复杂度为 O(1)。
 * 2．不得改变数字字符从左到右出现的顺序。
 */
public class ReplaceAndModify {
    public static void replace(char[] chars) {
        int spaceNum = 0;
        int length = 0;
        for (char ch : chars) {
            if (ch == 0) {
                break;
            }
            if (ch == ' ') {
                spaceNum++;
            }
            length++;
        }

        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[i + 2 * spaceNum] = '0';
                chars[i - 1 + 2 * spaceNum] = '2';
                chars[i - 2 + 2 * spaceNum] = '%';
                spaceNum--;
            } else {
                chars[i + 2 * spaceNum] = chars[i];
            }
        }
    }

    public static void modify(char[] chars) {
        int num = 0;
        int length = 0;
        for (char ch : chars) {
            if (ch == 0) {
                break;
            }
            if (ch != '*') {
                num++;
            }
            length++;
        }

        int index = length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] != '*') {
                chars[index--] = chars[i];
            }

            if (i <= num) {
                chars[i] = '*';
            }
        }
    }


    public static void main(String[] args) {
        char[] chars = new char[20];
        chars[0] = 'a';
        chars[1] = ' ';
        chars[2] = 'b';
        chars[3] = ' ';
        chars[4] = ' ';
        chars[5] = 'c';
        System.out.println(chars);
        replace(chars);
        System.out.println(chars);

        chars = new char[]{
                '1', '*', '*', '2', '3', '*', '4', '*', '5'
        };
        System.out.println(chars);
        modify(chars);
        System.out.println(chars);
    }
}
