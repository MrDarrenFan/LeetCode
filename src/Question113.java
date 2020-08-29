/*

113. 路径总和 II
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Question113 {

    public static void main(String[] args) {

    }

    List<List<Integer>> answerList = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        LinkedList<Integer> path = new LinkedList<>();
        findPath(root, sum, path);
        return answerList;
    }

    // 使用dfs递归遍历二叉树
    public void findPath(TreeNode node, int sum, LinkedList<Integer> path) {
        if (node == null) {     // 这里无法通过sum - node.val < 0提前结束是因为有负数
            // 当前node不存在则直接结束当前递归
            return;
        }

        path.addLast(node.val);

        if (node.left == null && node.right == null && sum - node.val == 0) {   // 是否找到满足要求的叶子节点
            answerList.add(new ArrayList(path));    // 则将该路径记录到结果集中
        }

        findPath(node.left, sum - node.val, path);
        findPath(node.right, sum - node.val, path);

        path.removeLast();  // 递归过程中遍历完该结点后，要把当前node移除，因为是在同一个path对象上操作，当前操作完要回归初始状态
    }

}

