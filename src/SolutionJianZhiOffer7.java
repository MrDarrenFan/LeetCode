/*
剑指 Offer 07. 重建二叉树
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。



例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7


限制：

0 <= 节点个数 <= 5000
 */

import java.util.Arrays;
import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class SolutionJianZhiOffer7 {

    public static void main(String[] args) {

    }

    /*
        方法二（使用位置指针的方式代替方法一的Arrays.copyOfRange，提高效率）
        （方法一在下方）
     */
    HashMap<Integer, Integer> inorderIndexMap = new HashMap<>();    // 存放中序各结点位置，避免每次寻找根节点位置
    int[] preorder;     // 先序遍历序列

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        // 记录中序各结点位置
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildRecursively(0, 0, inorder.length - 1);
    }

    // rootIndexInPreorder：表示当前根节点在先序中的位置
    // startIndexInInorder：当前序列在中序中的前边界
    // endIndexInInorder：当前序列在中序中的后边界
    public TreeNode buildRecursively(int rootIndexInPreorder, int startIndexInInorder, int endIndexInInorder) {
        // 如果前边界>后边界，代表空。（相等就代表只有一个节点）
        if (startIndexInInorder > endIndexInInorder) {
            return null;
        }

        // 构造当前结点
        TreeNode currentRoot = new TreeNode(preorder[rootIndexInPreorder]);

        // 根据根节点在先序中的位置 得到 当前根节点在中序中的位置
        int rootIndexInInorder = inorderIndexMap.get(preorder[rootIndexInPreorder]);

        // 左子树
        // 根节点为在先序中的左子树序列的第一个
        // 中序中的前边界为当前的前边界
        // 中序中的后边界为中序中当前根节点位置前一个
        currentRoot.left = buildRecursively(rootIndexInPreorder + 1,
                                            startIndexInInorder,
                            rootIndexInInorder - 1);

        // 右子树
        // 根节点为在先序中的右子树序列的第一个（即当前根节点在先序中的位置 + 左子树序列长度 + 1)
        //      其中 左子树序列长度 = 上述左子树在中序中的后边界 - 前边界 + 1;
        // 中序中的前边界为中序中当前根节点位置后一个
        // 中序中的后边界为当前的后边界
        currentRoot.right = buildRecursively(rootIndexInPreorder + rootIndexInInorder - 1 - startIndexInInorder + 1 + 1,
                                            rootIndexInInorder + 1,
                                            endIndexInInorder);

        return currentRoot;
    }


    /*
        方法一
        以下答案可以通过，但用Arrays.copyOfRange，效率低，优化为上方用位置指针的方法
     */
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        // 当前递归是否还有序列（即当前结点是否还有左右子树）
//        if (preorder.length == 0) {
//            return null;
//        }
//        if (preorder.length == 1) {
//            return new TreeNode(preorder[0]);
//        }
//        // 前序的第一个结点为根结点
//        int currentRootVal = preorder[0];
//        TreeNode currentRoot = new TreeNode(currentRootVal);
//        // 根据根节点在中序的位置划分左右
//        int rootIndex = 0;
//        while (rootIndex < inorder.length) {
//            if (inorder[rootIndex] == currentRootVal) {
//                break;
//            }
//            rootIndex++;
//        }
//        // 根据左右元素，在前序中找到左右子树的前序的位置
//        // 左子数序列在前序中的起始位置 就是根结点位置后面一个，即为1
//        // 右子树序列在前序中的起始位置 就是左子树序列起始位置加上左字数序列长度，而左子树序列长度即为rootIndex
//        int rightStartIndex = 1 + rootIndex;
//        // 构建当前树（递归向下）
//        currentRoot.left = buildTree(Arrays.copyOfRange(preorder, 1, rootIndex + 1),
//                                    Arrays.copyOfRange(inorder, 0, rootIndex));
//        currentRoot.right = buildTree(Arrays.copyOfRange(preorder, rightStartIndex, preorder.length),
//                                    Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length));
//        return currentRoot;
//    }

}
