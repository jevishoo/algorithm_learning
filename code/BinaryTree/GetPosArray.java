package code.BinaryTree;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Date 2020/12/21 13:04
 * @Created by Jevis_Hoo
 * @Description
 */
public class GetPosArray {
    public static int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int len = pre.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);
        }
        int[] pos = new int[len];
        setPos(pre, 0, len - 1, 0, len - 1, pos, len - 1, map);

        return pos;
    }

    public static int setPos(int[] pre, int pre_s, int pre_e, int in_s, int in_e,
                             int[] pos, int pos_index, HashMap<Integer, Integer> map) {
        if (pre_s > pre_e)
            return pos_index;

        pos[pos_index--] = pre[pre_s];
        int i = map.get(pre[pre_s]);

        pos_index = setPos(pre, pre_e - in_e + i + 1, pre_e, i + 1, in_e, pos, pos_index, map);
        return setPos(pre, pre_s + 1, pre_s + i - in_s, in_s, i - 1, pos, pos_index, map);
    }

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] in = new int[]{4, 2, 5, 1, 6, 3, 7};

        System.out.println(Arrays.toString(getPosArray(pre, in)));
    }
}
