/*
968. 监控二叉树
给定一个二叉树，我们在树的节点上安装摄像头。

节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

计算监控树的所有节点所需的最小摄像头数量。



示例 1：
输入：[0,0,null,0,0]
输出：1
解释：如图所示，一台摄像头足以监控所有节点。

示例 2：
输入：[0,0,null,0,null,0,null,null,0]
输出：2
解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。

提示：

给定树的节点数的范围是 [1, 1000]。
每个节点的值都是 0。
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

public class Solution968 {

    public static void main(String[] args) {

    }

    int countOfCamera = 0;  // 记录摄像头数量

    public int minCameraCover(TreeNode root) {
        // 递归结束，如果最后根节点没有覆盖，则放一个摄像头
        if (postorder(root) == 0) {
            countOfCamera++;
        }
        return countOfCamera;
    }

    // 后序遍历处理该问题逻辑
    public int postorder(TreeNode currentNode) {
        // 每个结点有三种状态
        // 0：该结点无覆盖
        // 1：该结点有覆盖
        // 2：该结点有摄像头

        // 空结点应为有覆盖状态
        if (currentNode == null) {
            return 1;
        }

        // 后序遍历
        int left = postorder(currentNode.left);
        int right = postorder(currentNode.right);

        // 情况1：左右子结点都有覆盖
        if (left == 1 && right == 1) {
            return 0;
        }

        // 情况2：如果左右子结点至少有一个为无覆盖状态
        if (left == 0 || right == 0) {
            // 当前结点应为有摄像头状态，则摄像头数量加一
            // 有一个孩子没有覆盖，父节点就应该放摄像头，保证覆盖
            countOfCamera++;
            return 2;
        }

        // 情况3：左右子结点至少有一个有摄像头
        if (left == 2 || right == 2) {
            // 当前结点应为有覆盖状态
            return 1;
        }

        return -1;
    }

}
