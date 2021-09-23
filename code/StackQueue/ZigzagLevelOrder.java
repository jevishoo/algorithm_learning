package code.StackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @since 2020/9/15 8:00
 * @Created by Jevis_Hoo
 * @Description ZigzagLevelOrder
 * <p>
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * <p>
 * from left to right, then right to left for the next level and alternate between.
 */
public class ZigzagLevelOrder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // fast 
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        //Final List to return
        List<List<Integer>> ret = new ArrayList<>();

        //This is the current level
        //and the next level (AKA Next level in BFS)
        Stack<TreeNode> currLvl = new Stack<>();
        Stack<TreeNode> nextLvl = new Stack<>();

        //Push root and start with leftToRight true
        currLvl.push(root);
        boolean leftToRight = true;

        //Until current level is empty AKA end of list
        List<Integer> list = new ArrayList<>();
        while (!currLvl.isEmpty()) {
            //First pop the current value of currLvl
            TreeNode curr = currLvl.pop();
            list.add(curr.val);

            //If not an empty current List 
            if (curr != null) {
                //Push accordingly
                if (leftToRight) {
                    if (curr.left != null) nextLvl.push(curr.left);
                    if (curr.right != null) nextLvl.push(curr.right);
                } else {
                    if (curr.right != null) nextLvl.push(curr.right);
                    if (curr.left != null) nextLvl.push(curr.left);
                }

            }

            //IMPORTANT Technique here
            if (currLvl.isEmpty()) {
                //Switch left to right each time going to the next level
                leftToRight = !leftToRight;
                currLvl = (Stack<TreeNode>) nextLvl.clone();
                nextLvl.clear();

                //And add the current level(array) to the double arraylist
                ret.add(new ArrayList<Integer>(list));
                list.clear();
            }

        }

        return ret;
    }

    public static List<List<Integer>> getZigzagOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) return result;

        List<Integer> sublist;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root); // 顶点入栈
        boolean flag = true; // Zigzag 型（每遍历一层就改变入栈方向） true: 顺时针  false: 逆时针

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            sublist = new LinkedList<>();
            if (flag) {
                while (!stack1.isEmpty()) { // 将stack1中的元素出栈，加入到sublist中
                    TreeNode head = stack1.pop();
                    sublist.add(head.val);

                    /*
                    stack2 中的元素 先从左边入栈，出栈时就是由右到左（逆时针）
                     */
                    if (head.left != null) {
                        stack2.push(head.left);
                    }
                    if (head.right != null) {
                        stack2.push(head.right);
                    }
                }
            } else {
                while (!stack2.isEmpty()) {
                    TreeNode head = stack2.pop();
                    sublist.add(head.val);

                    /*
                    stack1 中的元素 先从右边入栈，出栈时就是由左到右（顺时针）
                     */
                    if (head.right != null) {
                        stack1.push(head.right);
                    }
                    if (head.left != null) {
                        stack1.push(head.left);
                    }
                }
            }
            //将每次的sublist（每一层元素）加入到结果中 并且改变方向型变量
            result.add(sublist);
            flag = !flag;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

//        TreeNode head = null;

        List<List<Integer>> lst = getZigzagOrder(head);
        System.out.println(lst);

    }
}
