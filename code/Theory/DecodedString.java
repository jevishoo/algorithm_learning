package code.Theory;

/**
 * @Date 2020/10/6 18:53
 * @Created by Jevis_Hoo
 * @Description Decoded String at Index
 * <p>
 * An encoded string S is given.  To find and write the decoded string to a tape,
 * the encoded string is read one character at a time and the following steps are taken:
 * <p>
 * >>> If the character read is a letter, that letter is written onto the tape.
 * >>> If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
 * <p>
 * Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.
 */
public class DecodedString {

    public static String decodeAtIndex1(String S, int K) {
        char[] chars = S.toCharArray();
        long size = 0;// 展开后字符总长
        for (char ch : chars) {
            if (Character.isDigit(ch)) size *= ch - '0';
            else size++;
        }

        /* size:用来指向展开后字串的第 size 字元(由 1 开始) size 不断减小*/
        for (int ind = chars.length - 1; ind >= 0; ind--) {
            char ch = chars[ind];
            // K==0 即 1~size 内最后的字符为解
            if (K == 0 && Character.isLetter(ch)) return String.valueOf(ch);

            if (Character.isDigit(ch)) {
                int times = ch - '0';// 0~size 内重复 times 次
                size = size / times; // 重复 times 次，替换 size
                K %= size; // 同理替换 K
            } else if (K == size) {// 若 K==size 即当前字符为解
                return String.valueOf(ch);
            } else {
                size--;
            }
        }
        return null;
    }


    /* 超内存 */
    public static String decodeAtIndex(String S, int K) {
        char[] chs = S.toCharArray();
        int size = 0;
        for (char ch : chs) {
            if (Character.isDigit(ch)) size *= ch - '0';
            else size++;
        }

        String[] strings = new String[Math.min(size, K)];

        int length = 0, begin = 0, index = 0;

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (Character.isDigit(ch)) {
                int times = Integer.parseInt(String.valueOf(ch));
                int temp = length + i - begin;
                length = (length + i - begin) * times;

                if (K <= length) {
                    int ind = K % temp;
                    if (ind == 0)
                        return strings[temp - 1];
                    return strings[ind - 1];
                }

                for (int j = 1; j < times; j++) {
                    if (temp >= 0) System.arraycopy(strings, 0, strings, temp * j, temp);
                }
                begin = i + 1;
                index += temp * (times - 1);

            } else {
                if (K - 1 == index) {
                    return String.valueOf(ch);
                }
                strings[index] = String.valueOf(ch);
                index++;
            }
        }

        return null;
    }

    public static void main(String[] args) {
//        String S = "ha22";
//        String S = "leet2code3";
//        String S = "a23456789999";
//        String S = "a2b3c4d5e6f7g8h9";
        String S = "y959q969u3hb22odq595";
        int K = 222280369;

        System.out.println(decodeAtIndex1(S, K));

    }
}
