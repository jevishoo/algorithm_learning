package code.Array;

/**
 * 种花问题
 * <p>
 * 花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
 * <p>
 * @author Jevis Hoo
 * @since 2021/9/24 12:18
 */
public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        int len = flowerbed.length, ind = 0;

        while (ind < len) {
            if (flowerbed[ind] == 1) {
                ind += 2;
            } else {
                if (ind == len - 1 || flowerbed[ind + 1] == 0) {
                    ind += 2;
                    n--;
                    if (n == 0) {
                        return true;
                    }
                } else {
                    ind += 3;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1, 0, 0, 0, 1};
        System.out.println(canPlaceFlowers(array, 1));
    }
}
