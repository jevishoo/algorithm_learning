package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @since 2021/1/31 11:17
 * @description check whether it is a mirror of itself
 * <p>
 * ie, symmetric around its center
 */
public class IsSymmetric {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricByRecursion(root.left, root.right);
    }

    public static boolean isSymmetricByRecursion(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else {
            if (left.value != right.value) {
                return false;
            }
            return isSymmetricByRecursion(left.left, right.right) && isSymmetricByRecursion(left.right, right.left);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(4);


        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root));
    }
}
