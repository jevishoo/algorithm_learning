package code.Stack_Queue;

/**
 * @Date 2020/8/16 20:01
 * @Created by Jevis_Hoo
 * @Description 汉诺塔问题
 * 【要求】
 * 限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。
 */
public class HanoiProblem {

    public static void simpleMove(int n, String a, String b, String c) {
        if (n == 1) {
            System.out.println("Move 1 from " + a + " to " + c);
        } else {
            simpleMove(n - 1, a, c, b);
            System.out.println("Move " + n + " from " + a + " to " + c);
            simpleMove(n - 1, b, a, c);
        }
    }

    public static void Move(int n, String a, String b, String c) {
        if (n == 1) {
            if ("mid".equals(a) || "mid".equals(c)) {
                System.out.println("Move 1 from " + a + " to " + c);
            } else {
                System.out.println("Move 1 from " + a + " to " + b);
                System.out.println("Move 1 from " + b + " to " + c);
            }
        } else {
            if ("mid".equals(a) || "mid".equals(c)) {
                Move(n - 1, a, c, b);
                System.out.println("Move " + n + " from " + a + " to " + c);
                Move(n - 1, b, a, c);
            } else {
                Move(n - 1, a, c, b);
                if (n - 2 > 0) {
                    Move(n - 2, b, c, a);
                }
                System.out.println("Move " + (n - 1) + " from " + b + " to " + c);
                if (n - 2 > 0) {
                    Move(n - 2, a, b, c);
                }
                System.out.println("Move " + n + " from " + a + " to " + b);
                Move(n - 1, c, b, a);
                System.out.println("Move " + n + " from " + b + " to " + c);
                Move(n - 1, a, b, c);
            }
        }
    }

    public static void main(String[] args) {
        simpleMove(4, "left", "mid", "right");
    }
}
