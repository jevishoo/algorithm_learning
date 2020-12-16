package code.Other;

import code.BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.List;

/**
 * @Date 2020/12/16 13:46
 * @Created by Jevis_Hoo
 * @Description 并查集及其应用
 */
public class UnionFind {
    /*
    1.快速查询两个元素是否属于一个集合
    2.将两个元素各自所在的集合合并
     */

    public static class UnionFindSet {
        public HashMap<TreeNode, TreeNode> fatherMap;
        public HashMap<TreeNode, Integer> sizeMap;

        public UnionFindSet(List<TreeNode> nodes) {
            this.makeSets(nodes);
        }

        private void makeSets(List<TreeNode> nodes) {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
            for (TreeNode node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public TreeNode findHead(TreeNode node) {
            TreeNode father = fatherMap.get(node);
            if (father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);
            return father;
        }

        public boolean isSameSet(TreeNode a, TreeNode b) {
            return findHead(a) == findHead(b);
        }

        public void union(TreeNode a, TreeNode b) {
            if (a == null || b == null)
                return;

            TreeNode aHead = findHead(a);
            TreeNode bHead = findHead(b);

            if (aHead != bHead) {
                int aSize = sizeMap.get(a);
                int bSize = sizeMap.get(b);

                if (aSize <= bSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSize + bSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSize + bSize);
                }
            }
        }
    }
}
