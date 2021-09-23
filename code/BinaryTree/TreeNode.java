package code.BinaryTree;

import java.util.Objects;

/**
 * @since 2020/11/10 8:56
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeNode treeNode = (TreeNode) o;
        return value == treeNode.value && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right) && Objects.equals(child, treeNode.child) && Objects.equals(parent, treeNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right, child, parent);
    }
}
