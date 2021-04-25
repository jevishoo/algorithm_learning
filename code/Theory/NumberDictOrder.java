package code.Theory;

/**
 * @author Jevis Hoo
 * @date 2021/4/25 9:33
 * @description 数字的字典序
 * 给定整数 n、m，将 1 到 n 这 n 个整数按字典序排列后，取第 m 个数
 */
public class NumberDictOrder {
    private static long solve(long n, long m) {
        long ans = 1;

        while (m != 0) {
            // 得到 ans 作为前缀的树的大小(数量)
            long count = getCountPreTree(ans, n);

            if (count >= m) {
                // 前缀树大小如果大于 m，当前树节点逐个比对，每次 m 减小1
                m--;
                if (m == 0) {
                    break;
                }
                // 进入子树查找
                // 1->10->100->···
                ans *= 10;
            } else {
                // 前缀树大小如果小于 m，进入兄弟树查找
                // m 减少 count个
                m -= count;
                ans++;
            }
        }

        return ans;
    }

    private static long getCountPreTree(long pre, long n) {
        // 默认ans自身的树只有一个
        long count = 1;
        long p = 10;
        // 1->10->100->···是否小于 n
        for (; p * pre < n; p *= 10) {
            // 如 10<20，count += 10
            if (p * pre + p - 1 < n) {
                count += p;
            } else {
                // 如 10<14，count += 14-10+1
                count += n - p * pre + 1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 2
        System.out.println(solve(11, 4));
        System.out.println("============");
        // 120
        System.out.println(solve(200, 25));
    }

}
