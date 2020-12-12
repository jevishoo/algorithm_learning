package code.BinaryTree;

/**
 * @Date 2020/12/11 16:11
 * @Created by Jevis_Hoo
 * @Description 在二叉树中找到一个节点的后继节点
 */
public class NextTreeNode {
    public static TreeNode getNextNode(TreeNode head) {

        return head;
    }

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3, node6);
        TreeNode node9 = new TreeNode(9, node6);
        node6.left = node6;
        node6.right = node9;

        TreeNode node1 = new TreeNode(1, node3);
        TreeNode node4 = new TreeNode(4, node3);
        node3.left = node1;
        node3.right = node4;

        TreeNode node8 = new TreeNode(8, node9);
        TreeNode node10 = new TreeNode(10, node9);
        node9.left = node8;
        node9.right = node10;

        node1.left = new TreeNode(2, node1);
        node4.right = new TreeNode(5, node4);
        node8.left = new TreeNode(7, node8);

        System.out.println(getNextNode(node1).value);
    }
}
