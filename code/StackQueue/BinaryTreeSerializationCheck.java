package code.StackQueue;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Date 2020/9/20 9:38
 * @Created by Jevis_Hoo
 * @Description 验证二叉树的前序序列化
 * <p>
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
 * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * <p>
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * <p>
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * <p>
 * 思路：‘#’ 总比 非空节点多一位
 */
public class BinaryTreeSerializationCheck {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static String serialByPre(TreeNode root) {
        if (root == null) {
            return "#,";
        }
        String result = root.val + ",";
        result += serialByPre(root.left);
        result += serialByPre(root.right);
        return result;
    }

    public static TreeNode reconPreOrderCheck(String preorder) {
        String[] values = preorder.split(",");
        LinkedList<String> list = new LinkedList<>();

        for (String v : values) {
            list.addLast(v);
        }
        return reconPreOrder(list);
    }

    private static TreeNode reconPreOrder(LinkedList<String> list) {
        String value = list.pollFirst();
        if (value != null && value.equals("#")) return null;

        TreeNode head = null;
        if (value != null) {
            head = new TreeNode(Integer.parseInt(value));
            head.left = reconPreOrder(list);
            head.right = reconPreOrder(list);
        }
        return head;
    }

    //using Stack and split
    public static boolean isValidSerialization1(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] values = preorder.split(",");

        if (values.length == 0) return false;

        for (int i = 0; i < values.length; i++) {
            if (values[i].equals("#")) {
                if (stack.isEmpty()) return i == values.length - 1;
                else stack.pop();
            } else {
                stack.push(values[i]);
            }
        }
        return false;
    }

    //using Stack and not split
    public static boolean isValidSerialization2(String preorder) {
        Stack<Boolean> stack = new Stack<>();

        if (preorder.length() == 0) return false;

        for (int i = 0; i < preorder.length(); i++) {
            if (preorder.charAt(i) == '#') {
                if (stack.isEmpty()) return i == preorder.length() - 1;
                else {
                    stack.pop();
                    i++;
                }
            } else {
                while (i < preorder.length() && preorder.charAt(i) != ',') i++;
                stack.push(false);
            }
        }
        return false;
    }

    //Not Stack and not split
    public static boolean isValidSerialization3(String preorder) {
        int size = preorder.length();
        int help = 1;
        for (int i = 0; i < size; i++) {
            char c = preorder.charAt(i);
            if (c == '#') {
                help--;
                if (help < 0 || (help == 0 && i < size - 1)) return false;
            } else if (c != ',') {
                help++;
                while (i + 1 < size && Character.isDigit(preorder.charAt(i + 1))) i++;
            }
        }
        return help == 0;
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(9);
        head.left = new TreeNode(3);
        head.right = new TreeNode(2);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(1);
        head.right.right = new TreeNode(6);

        String serial = serialByPre(head);
        System.out.println(serial.substring(0, serial.length() - 1));

//        String test = "1,#,#,2";
//        String test = "1,#,#";
        String test = "1,#";
        TreeNode root = reconPreOrderCheck(test);
        String result = serialByPre(root);
        System.out.println(result.substring(0, result.length() - 1));
        System.out.println(test.equals(result.substring(0, result.length() - 1)));

        //不重构树
        System.out.println(isValidSerialization1(test));
        System.out.println(isValidSerialization2(test));
        System.out.println(isValidSerialization3(test));

    }
}
