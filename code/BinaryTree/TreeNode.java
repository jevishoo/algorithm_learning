package code.BinaryTree;

/**
 * @Date 2020/11/10 8:56
 * @Created by Jevis_Hoo
 * @Description
 */
public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode child;
    public TreeNode parent;

    public TreeNode() {
    }

    public TreeNode(int value) {
        this.value = value;
    }


    public TreeNode(int value, TreeNode parent) {
        this.value = value;
        this.parent = parent;
    }
}
