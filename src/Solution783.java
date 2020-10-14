/*
783. 二叉搜索树节点最小距离

给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。

示例：
    输入: root = [4,2,6,1,3,null,null]
    输出: 1
    解释:
        注意，root是树节点对象(TreeNode object)，而不是数组。
    给定的树 [4,2,6,1,3,null,null] 可表示为下图:
              4
            /   \
          2      6
         / \
        1   3
    最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。

注意：
    二叉树的大小范围在 2 到 100。
    二叉树总是有效的，每个节点的值都是整数，且不重复。
    本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
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

public class Solution783 {

    int pre = -1;   // 记录当前结点的前序结点的值
    int minSub = Integer.MAX_VALUE;     // 记录两结点的差的绝对值的最小值

    // 利用二叉搜索树的中序遍历为顺序序列的特点，相邻两位逐个计算差值的绝对值，并记录最小值
    public int getMinimumDifference(TreeNode root) {
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
