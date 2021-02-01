package code.BinaryTree;

import code.StackQueue.InorderTraversal;
import code.StackQueue.PostorderTraversal;
import code.StackQueue.PreorderTraversal;

import java.util.HashMap;

/**
 * @author Jevis Hoo
 * @date 2021/2/1 12:13
 * @description Construct Binary Tree from Preorder and Inorder Traversal
 */
public class RebuildTree {
    /**
     * ByPreInorder
     *
     * @param preorder preorder array
     * @param inorder  inorder array
     * @return tree
     */
    public static TreeNode rebuildTreeByPreIn(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>(preorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return rebuildTreeByPreIn(preorder, new int[]{0}, 0, preorder.length - 1, map);
    }

    public static TreeNode rebuildTreeByPreIn(int[] pre, int[] position, int bInd, int eInd, HashMap<Integer, Integer> map) {
        if (bInd > eInd) {
            return null;
        }

        TreeNode head = new TreeNode(pre[position[0]++]);
        int index = map.get(head.value);

        head.left = rebuildTreeByPreIn(pre, position, bInd, index - 1, map);
        head.right = rebuildTreeByPreIn(pre, position, index + 1, eInd, map);

        return head;
    }

    /**
     * ByInPostorder
     *
     * @param inorder   inorder array
     * @param postorder postorder array
     * @return tree
     */
    public static TreeNode rebuildTreeByInPost(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return rebuildTreeByInPost(postorder, new int[]{postorder.length - 1}, 0, postorder.length - 1, map);
    }

    public static TreeNode rebuildTreeByInPost(int[] pos, int[] position, int bInd, int eInd, HashMap<Integer, Integer> map) {
        if (bInd > eInd) {
            return null;
        }
        TreeNode head = new TreeNode(pos[position[0]--]);
        int index = map.get(head.value);

        head.right = rebuildTreeByInPost(pos, position, index + 1, eInd, map);
        head.left = rebuildTreeByInPost(pos, position, bInd, index - 1, map);

        return head;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode head = rebuildTreeByPreIn(preorder, inorder);
        System.out.println(PreorderTraversal.preorderTraversal(head));
        System.out.println(InorderTraversal.inorderTraversal(head));
        System.out.println(PostorderTraversal.postorderTraversal(head));
        System.out.println("========================");
        TreeNode root = rebuildTreeByInPost(inorder, postorder);
        System.out.println(PreorderTraversal.preorderTraversal(root));
        System.out.println(InorderTraversal.inorderTraversal(root));
        System.out.println(PostorderTraversal.postorderTraversal(root));

    }
}
