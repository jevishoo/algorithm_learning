package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @date 2021/2/18 10:29
 * @description Kth Smallest Element in a BST
 * <p>
 * Given the root of a binary search tree, and an integer k,
 * return the kth (1-indexed) smallest element in the tree.
 */
public class KthSmallest {
    private static int index = 0;
    private static boolean flag = false;

    public static int kthSmallest(TreeNode root, int k) {
        if (root == null || flag) {
            return index;
        }
        kthSmallest(root.left, k);
        if (flag) {
            return index;
        }
        index++;
        if (index == k) {
            flag = true;
            index = root.value;
            return index;
        }
        kthSmallest(root.right, k);
        return index;
    }


    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(3);
//        root.right = new TreeNode(6);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(4);
//        root.left.left.left = new TreeNode(1);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(kthSmallest(root, 1));
    }
}
