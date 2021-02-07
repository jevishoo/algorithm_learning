package code.BinaryTree;

/**
 * @author Jevis Hoo
 * @date 2021/2/7 10:55
 * @description Sum Root to Leaf Numbers
 * <p>
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * Input: [1,2,3]
 * ——1
 * —/ \
 * 2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 */
public class SumNumbers {
    static int sum = 0;

    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        getSumNumbers(root, root.value);
        return sum;
    }

    public static void getSumNumbers(TreeNode root, int rootNum) {
        if (root.left == null && root.right == null) {
            sum += rootNum;
            return;
        }

        if (root.left != null) {
            getSumNumbers(root.left, rootNum * 10 + root.left.value);
        }

        if (root.right != null) {
            getSumNumbers(root.right, rootNum * 10 + root.right.value);
        }
    }


    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);

        TreeNode head2 = new TreeNode(4);
        head2.left = new TreeNode(9);
        head2.left.left = new TreeNode(5);
        head2.left.right = new TreeNode(1);
        head2.right = new TreeNode(0);

        System.out.println(sumNumbers(head1));
        sum = 0;
        System.out.println(sumNumbers(head2));
    }
}
