package code.Theory;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Jevis Hoo
 * @date 2021/5/9 9:02
 * @description 丑数
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
 * 求按从小到大的顺序的第 n 个丑数。
 */
public class UglyNumber {
    public static void main(String[] args) {
        for (int i = 1; i < 1691; i++) {
            if (nthUglyNumber(i) != comparator(i)) {
                System.out.print(i + ": " + nthUglyNumber(i) + " " + comparator(i) + "\n");
            }
        }
        System.out.println(nthUglyNumber(1563));
        System.out.println(comparator(1563));
    }

    public static int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((v1, v2) -> v1[0] - v2[0]);
        queue.add(new int[]{2, 2});
        queue.add(new int[]{3, 3});
        queue.add(new int[]{5, 5});

        for (int i = 0; i < n - 2; i++) {
            int[] arr = queue.poll();
            System.out.println(Arrays.toString(arr));
            queue.add(new int[]{arr[0] > Integer.MAX_VALUE / 2 ? Integer.MAX_VALUE : arr[0] * 2, 2});
            if (arr[1] > 2) {
                queue.add(new int[]{arr[0] > Integer.MAX_VALUE / 3 ? Integer.MAX_VALUE : arr[0] * 3, 3});
                if (arr[1] > 3) {
                    queue.add(new int[]{arr[0] > Integer.MAX_VALUE / 5 ? Integer.MAX_VALUE : arr[0] * 5, 5});
                }
            }
        }

        return queue.poll()[0];
    }

    public static int comparator(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }

}
