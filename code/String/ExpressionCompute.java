package code.String;

import java.util.LinkedList;

/**
 * @author Jevis Hoo
 * @date 2021/3/17 11:21
 * @description 计算表达式的值
 */
public class ExpressionCompute {
    private static int getValue(String exp) {
        return process(exp.toCharArray(), 0)[0];
    }

    private static int[] process(char[] chars, int i) {
        LinkedList<String> que = new LinkedList<>();
        int pre = 0;
        int[] bra ;
        while (i < chars.length && chars[i] != ')') {
            if (chars[i] >= '0' && chars[i] <= '9') {
                pre = pre * 10 + chars[i++] - '0';
            } else if (chars[i] != '(') {
                addNum(que, pre);
                que.addLast(String.valueOf(chars[i++]));
                pre = 0;
            } else {
                bra = process(chars, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(que, pre);
        return new int[]{getNum(que), i};
    }


    private static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur;
            String top = que.pollLast();
            if ("+".equals(top) || "-".equals(top)) {
                que.addLast(top);
            } else {
                String s = que.pollLast();
                cur = Integer.parseInt(s != null ? s : "0");
                num = "*".equals(top) ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    private static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur;
        int num;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if ("+".equals(cur)) {
                add = true;
            } else if ("-".equals(cur)) {
                add = false;
            } else {
                num = Integer.parseInt(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));
    }
}
