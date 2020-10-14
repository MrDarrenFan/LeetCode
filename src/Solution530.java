/*
530. 二叉搜索树的最小绝对差

给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

示例：
    输入：
       1
        \
         3
        /
       2
    输出：
        1
    解释：
        最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

提示：
    树中至少有 2 个节点。
    本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
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

public class Solution530 {

    int pre = -1;   // 记录当前结点的前序结点的值
    int minSub = Integer.MAX_VALUE;     // 记录两结点的差的绝对值的最小值

    // 利用二叉搜索树的中序遍历为顺序序列的特点，相邻两位逐个计算差值的绝对值，并记录最小值
    public int minDiffInBST(TreeNode root) {
        dfsByInorder(root);
        return minSub;
    }

    // 中序dfs
    public void dfsByInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        dfsByInorder(root.left);
        if (pre != -1) {    // pre不为-1时，代表这不是第一个结点，则可以计算差值
            minSub = Math.min(minSub, root.val - pre);
        }
        pre = root.val;
        dfsByInorder(root.right);
    }

}
