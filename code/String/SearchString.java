package code.String;

/**
 * @author Jevis Hoo
 * @date 2021/3/10 9:19
 * @description 在有序但含有空的数组中查找字符串
 * 给定一个字符串数组 strings[]，在 strings 中有些位置为 null，但在不为 null 的位置上，其字符串
 * 是按照字典顺序由小到大依次出现的。再给定一个字符串 str，请返回 str 在 strings 中出现的最左的位置。
 */
public class SearchString {

    /**
     * @param strings 字符串数组
     * @param str     给定字符串
     * @return str 在 strings 中出现的最左的位置
     * @description 遍历搜索
     */
    public static int getIndex(String[] strings, String str) {
        if (strings == null || str == null) {
            return -1;
        }

        for (int i = 0; i < strings.length; i++) {
            if (strings[i] != null && str.equals(strings[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param strings 字符串数组
     * @param str     给定字符串
     * @return str 在 strings 中出现的最左的位置
     * @description 二分搜索
     */
    public static int getIndex1(String[] strings, String str) {
        if (strings == null || str == null) {
            return -1;
        }
        int result = -1;
        int left = 0;
        int right = strings.length - 1;
        int mid;
        while (left < right) {
            mid = right - ((right - left) >> 1);
            if (strings[mid] != null) {
                if (str.equals(strings[mid])) {
                    result = mid;
                    right = mid - 1;
                } else if (str.compareTo(strings[mid]) < 0) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                int tmp = mid;
                while (strings[tmp] == null && tmp - 1 >= left) {
                    tmp--;
                }

                if (tmp < left || strings[tmp].compareTo(str) < 0) {
                    left = mid + 1;
                } else {
                    if (str.equals(strings[tmp])) {
                        result = tmp;
                    }
                    right = tmp - 1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{
                null, "a", null, "a", null, "b", null, "c"
        };

        System.out.println(getIndex(strings, "a"));
        System.out.println(getIndex(strings, null));
        System.out.println(getIndex(strings, "d"));

        System.out.println(getIndex1(strings, "a"));
        System.out.println(getIndex1(strings, null));
        System.out.println(getIndex1(strings, "d"));
    }
}
