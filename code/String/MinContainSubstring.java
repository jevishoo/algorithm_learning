package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/22 15:29
 * @description 最小包含子串的长度
 * 给定字符串 str1和 str2，求 str1的子串中含有 str2所有字符的最小子串长度。
 */
public class MinContainSubstring {
    public static int getMinContainSubstring(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return 0;
        }
        char[] chars2 = str2.toCharArray();
        int[] map = new int[256];

        for (char ch : chars2) {
            map[ch]++;
        }

        int left = 0, right = 0, match = chars2.length;
        int minLength = Integer.MAX_VALUE;

        char[] chars1 = str1.toCharArray();
        while (right != chars1.length) {
            // 将map中字符减一，str2中不存在的字符如果减1会小于0
            map[chars1[right]]--;
            if (map[chars1[right]] >= 0) {
                // 将map中存在的字符减一，则总长度可以减一
                match--;
            }
            // 将match为0，则表示该情况下子串包含所有字符
            if (match == 0) {
                // 若存在字符对于map值小于0，表示中间存在重复值，向右移动。
                // 一直到发现一个不小于0的值停止，该值必然是str2中存在的。
                // 注意：向右移动过程中，每个值都需要增加1，即删除一次重复值情况
                while (map[chars1[left]] < 0) {
                    map[chars1[left++]]++;
                }
                // 经过上述步骤得到最终的left值
                minLength = Math.min(minLength, right - left + 1);
                // 然后继续向右移动，left向右移动一位，则子串必然缺少chars1[left]字符，
                // 长度和该字符对应的map值都需要加一。
                // 注意：退出该逻辑后，继续向右，找寻下一个使得match再次为0的索引
                match++;
                map[chars1[left++]]++;
            }
            // 循环向右搜索
            right++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ad";

        System.out.println(getMinContainSubstring(str1, str2));

        str1 = "1234";
        str2 = "344";

        System.out.println(getMinContainSubstring(str1, str2));

        str1 = "adabbca";
        str2 = "acb";

        System.out.println(getMinContainSubstring(str1, str2));
    }
}
