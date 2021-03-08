package code.String;

/**
 * @author Jevis Hoo
 * @Date 2020/11/24 10:34
 * @Description KMP algorithm
 */
public class KMP {
    public static int getIndexOf(String s, String t) {
        if (s == null || t == null || t.length() < 1 || s.length() < t.length()) {
            return -1;
        }

        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();

        int i = 0;
        int j = 0;
        int[] next = getNext(ts);
//        System.out.println(s);
//        System.out.println(t);
        while (i < ss.length && j < ts.length) {
            if (ss[i] == ts[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }

        return j == ts.length ? i - j : -1;
    }

    private static int[] getNext(char[] ts) {
        if (ts.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ts.length];
        next[0] = -1;

        int i = 2, j = 0;
        while (i < next.length) {
            if (ts[i - 1] == ts[j]) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
