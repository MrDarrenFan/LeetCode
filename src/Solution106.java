/*
106. 从中序与后序遍历序列构造二叉树
根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.HashMap;

public class Solution106 {

    public static void main(String[] args) {

    }

    HashMap<Integer, Integer> inorderIndexMap = new HashMap<>();    // 存放中序各结点位置，避免每次寻找根节点的位置
    int[] postorder;    // 后序遍历序列

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildRecursively(postorder.length - 1, 0, inorder.length - 1);
    }

    // rootIndexInPostorder：表示当前根节点在后序中的位置
    // startIndexInInorder：当前序列在中序中的前边界
    // endIndexInInorder：当前序列在中序中的后边界
    public TreeNode buildRecursively(int rootIndexInPostorder, int startIndexInInorder, int endIndexInInorder) {
        // 如果前边界>后边界，代表空。（相等就代表只有一个节点）
        if (startIndexInInorder > endIndexInInorder) {
            return null;
        }

        // 构造当前结点
        TreeNode currentRoot = new TreeNode(postorder[rootIndexInPostorder]);

        // 根据根节点在后序中的位置 得到 当前根节点在中序中的位置
        int rootIndexInInorder = inorderIndexMap.get(postorder[rootIndexInPostorder]);

        // 右子树
        // 根节点为在后序中的右子树序列的最后一个
        // 中序中的前边界为中序中当前根节点位置后一个
        // 中序中的后边界为当前的后边界
        currentRoot.right = buildRecursively(rootIndexInPostorder - 1,
                                            rootIndexInInorder + 1,
                            endIndexInInorder);

        // 左子树
        // 根节点为在后序中的左子树序列的最后一个（即当前根节点在后序中的位置 - 右子树序列长度 - 1)
        //      其中 右子树序列长度 = 上述右子树在中序中的后边界 - 前边界 + 1;
        // 中序中的前边界为当前的前边界
        // 中序中的后边界为中序中当前根节点位置前一个
        currentRoot.left = buildRecursively(rootIndexInPostorder - (endIndexInInorder - rootIndexInInorder - 1 + 1) - 1,
                startIndexInInorder,
                rootIndexInInorder - 1);

        return currentRoot;
    }

}
