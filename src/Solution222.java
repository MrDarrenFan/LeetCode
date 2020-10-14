/*

222. 完全二叉树的节点个数
给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:

输入:
    1
   / \
  2   3
 / \  /
4  5 6

输出: 6

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

/*

首先需要明确完全二叉树的定义：它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。

再来回顾一下满二叉的节点个数怎么计算，如果满二叉树的层数为h，则总节点数为：2^h - 1.
那么我们来对root节点的左右子树进行高度统计，分别记为left和right,有以下两种结果：

left == right。
    这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，
    是2^left - 1，加上当前这个root节点，则正好是2^left。再对右子树进行递归统计。
left != right。
    说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。
    同理，右子树节点+root节点，总数为2^right。再对左子树进行递归查找。

    时复
        每次循环logn，一共有logn次循环：O((logn)^2)
    空复
        递归占用：O(depth)
 */

public class Solution222 {

    public static void main(String[] args) {

    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = computeDepth(root.left);
        int rightHeight = computeDepth(root.right);
        if (leftHeight == rightHeight) {    // 如果两边高度相同，根据完全二叉树特性，那左边一定是满二叉树
            // 则左子树和当前根节点结点个数用公式计算，右边递归去计算
            // 左子树结点个数 2^left-1，加上当前根节点为 2^left，则可用左移位运算
            // 操作数左移n位，相当于操作数乘以2^n。所以 1<<left 表示: 1 * 2^left
            return (1<<leftHeight) + countNodes(root.right);
        } else {    // 反之同理
            return (1<<rightHeight) + countNodes(root.left);
        }
    }

    // 利用完全二叉树的特性求高度，高度从1开始
    public int computeDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.left;   // 完全二叉树的特性
        }
        return depth;
    }

}
