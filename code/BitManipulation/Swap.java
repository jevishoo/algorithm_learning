package code.BitManipulation;

/**
 * @author Jevis Hoo
 * @date 2021/4/1 9:26
 * @description 不用额外变量交换两个整数的值
 */
public class Swap {
    public static void swap(int a, int b) {
        System.out.println("a：" + a + " b：" + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a：" + a + " b：" + b);
    }

    public static void main(String[] args) {
        swap(1, 4);
    }
}
