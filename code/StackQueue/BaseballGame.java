package code.StackQueue;

import java.util.Stack;

/**
 * @since 2020/9/25 14:32
 * @Created by Jevis_Hoo
 * @Description Baseball Game
 * <p>
 * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
 * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
 * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
 * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
 * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
 * <p>
 * 返回你在所有回合中得分的总和。
 */
public class BaseballGame {
    public static int calPoints(String[] ops) {
        Stack<Integer> validScore = new Stack<>();

        for (String s : ops) {
            switch (s) {
                case "+":
                    int num = validScore.pop();
                    int add = num + validScore.peek();
                    validScore.push(num);
                    validScore.push(add);
                    break;
                case "D":
                    validScore.push(validScore.peek() * 2);
                    break;
                case "C":
                    validScore.pop();
                    break;
                default:
                    validScore.push(Integer.parseInt(s));
                    break;
            }
        }

        int result = 0;
        while (!validScore.isEmpty()) {
            result += validScore.pop();
        }
        return result;
    }


    public static int calPoints1(String[] ops) {
        Stack<Integer> st = new Stack<Integer>();
        int sum = 0, temp = 0;
        for (String op : ops) {
            switch (op) {
                case "+":
                    temp = st.pop();
                    int tempsum = st.peek() + temp;
                    st.push(temp);
                    st.push(tempsum);
                    sum += tempsum;
                    break;
                case "C":
                    sum -= st.pop();
                    break;
                case "D":
                    st.push(2 * st.peek());
                    sum += st.peek();
                    break;
                default:
                    st.push(Integer.parseInt(op));
                    sum += st.peek();
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        String[] array1 = new String[]{"5", "2", "C", "D", "+"};
        String[] array2 = new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"};

        System.out.println(calPoints1(array1));
        System.out.println(calPoints1(array2));
    }
}
