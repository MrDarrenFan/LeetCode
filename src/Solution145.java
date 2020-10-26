/*
145. 二叉树的后序遍历
给定一个二叉树，返回它的 后序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import java.util.*;

public class Solution145 {

    // 非递归版 二叉树后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        TreeNode currentNode = root;    // 当前结点
        TreeNode preNode = null;    // 上一个遍历的结点
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> dequeStack = new LinkedList<>();

        while (!dequeStack.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                dequeStack.addFirst(currentNode);
                currentNode = currentNode.left;     // 左结点优先
            } else {
                currentNode = dequeStack.peekFirst();
                if (currentNode.right == null || currentNode.right == preNode) {    // 当前结点没有右节点，或者右节点已经遍历过的时候
                    result.add(currentNode.val);
                    preNode = currentNode;
                    dequeStack.removeFirst();
                    currentNode = null;
                } else {    // 有右节点且没有遍历过，则先遍历右节点（因为后序为：左右根）
                    currentNode = currentNode.right;
                }
            }
        }

        return result;
    }

}
