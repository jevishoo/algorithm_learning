package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @since 2021/1/29 9:58
 * @description
 */
public class IsSameTree {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        return (p != null && q != null) && (p.value == q.value) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);

        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(2);

        System.out.println(isSameTree(head1, head2));
    }
}
