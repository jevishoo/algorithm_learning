package code.BinaryTree;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @since 2020/12/21 13:04
 * @Description
 */
public class GetPosArray {
    public static int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        int len = pre.length;
        HashMap<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);
        }
        int[] pos = new int[len];
        setPos(pre, 0, len - 1, 0, len - 1, pos, len - 1, map);

        return pos;
    }

    public static int setPos(int[] pre, int preS, int preE, int inS, int inE,
                             int[] pos, int posIndex, HashMap<Integer, Integer> map) {
        if (preS > preE) {
            return posIndex;
        }

        pos[posIndex--] = pre[preS];
        int i = map.get(pre[preS]);

        posIndex = setPos(pre, preE - inE + i + 1, preE, i + 1, inE, pos, posIndex, map);
        return setPos(pre, preS + 1, preS + i - inS, inS, i - 1, pos, posIndex, map);
    }

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] in = new int[]{4, 2, 5, 1, 6, 3, 7};

        System.out.println(Arrays.toString(getPosArray(pre, in)));
    }
}
