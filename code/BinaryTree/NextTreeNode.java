package code.BinaryTree;

/**
 * @since 2020/12/11 16:11
 * @Created by Jevis_Hoo
 * @Description 在二叉树中找到一个节点的后继节点
 */
public class NextTreeNode {
    public static TreeNode getNextNode(TreeNode head) {
        if (head.right != null) {
            TreeNode mostLeft = head.right;
            while (mostLeft.left != null) {
                mostLeft = mostLeft.left;
            }

            return mostLeft;
        } else {
            TreeNode child = head;
            if (head.parent == null)
                return null;

            head = head.parent;
            while (head.left != child) {
                child = head;
                if (head.parent == null) {
                    return null;
                }
                head = head.parent;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3, node6);
        TreeNode node9 = new TreeNode(9, node6);

        TreeNode node1 = new TreeNode(1, node3);
        TreeNode node4 = new TreeNode(4, node3);

        TreeNode node8 = new TreeNode(8, node9);
        TreeNode node10 = new TreeNode(10, node9);

        TreeNode node2 = new TreeNode(2, node1);
        TreeNode node5 = new TreeNode(5, node4);
        TreeNode node7 = new TreeNode(7, node8);

        node6.left = node3;
        node6.right = node9;

        node3.left = node1;
        node3.right = node4;

        node1.right = node2;
        node4.right = node5;

        node9.left = node8;
        node9.right = node10;

        node8.left = node7;

        TreeNode next = getNextNode(node5);
        if (next != null) {
            System.out.println(next.value);
        }
    }
}
