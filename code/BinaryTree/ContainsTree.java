package code.BinaryTree;

import static code.StackQueue.BinaryTreeSerializationCheck.serialByPre;
import static code.String.KMP.getIndexOf;

/**
 * @Date 2020/11/24 8:52
 * @Created by Jevis_Hoo
 * @Description 判断 t1 树是否包含 t2 树全部的拓扑结构
 */
public class ContainsTree {
    public static boolean isContainTopo(TreeNode h1, TreeNode h2) {
        if (h1 == null)
            return false;

        if (h2 == null)
            return true;

        return check(h1, h2) || isContainTopo(h1.left, h2) || isContainTopo(h1.right, h2);
    }

    private static boolean check(TreeNode h1, TreeNode h2) {
        if (h2 == null)
            return true;

        if (h1 == null || h1.value != h2.value)
            return false;


        return check(h1.left, h2.left) && check(h1.right, h2.right);
    }

    /*
    O(N*M)
     */
    public static boolean isContainTree(TreeNode h1, TreeNode h2) {
        if (h1 == null)
            return false;

        if (h2 == null)
            return true;

        return checkTree(h1, h2) || isContainTree(h1.left, h2) || isContainTree(h1.right, h2);
    }

    private static boolean checkTree(TreeNode h1, TreeNode h2) {
        if (h2 == null && h1 == null)
            return true;

        if (h2 == null || h1 == null || h1.value != h2.value)
            return false;


        return checkTree(h1.left, h2.left) && checkTree(h1.right, h2.right);
    }

    public static boolean isSubTree(TreeNode h1, TreeNode h2) {
        String h1Str = serialByPre(h1);
        String h2Str = serialByPre(h2);

        return getIndexOf(h1Str, h2Str) != -1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(4);
        sub.left.left = new TreeNode(8);
        sub.left.right = new TreeNode(9);
        sub.right = new TreeNode(5);
//        sub.right.left = new TreeNode(10);
//        sub.right.right = new TreeNode(11);

        System.out.println("拓扑结构：" + isContainTopo(root, sub));
        System.out.println("子树结构1：" + isContainTree(root, sub));
        System.out.println("子树结构2：" + isSubTree(root, sub));

    }
}
