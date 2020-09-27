/*
235. 二叉搜索树的最近公共祖先
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6
解释: 节点 2 和节点 8 的最近公共祖先是 6。
示例 2:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。

说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。
 */

public class Question235 {

    public static void main(String[] args) {

    }

    // 迭代实现  时间复杂度O(n)，空间复杂度O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            // 根据 二叉搜索树的特性 和 当前结点的值 进行判断
            // 情况一：如果p、q在当前结点的两侧，那当前节点即为最近公共祖先
            if ((p.val > root.val && q.val < root.val) || (p.val < root.val && q.val > root.val)) {
                return root;
            }
            // 情况二：如果p、q其中一个等于当前结点，且不符合情况一，则当前结点就为最近公共祖先
            if (p.val == root.val || q.val == root.val) {
                return root;
            }
            // 情况三：如果情况一和二都不是，那两个目标点一定在左子树或者右子树，则继续往下
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }


    // 递归实现
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null) {
//            return null;
//        }
//        // 根据 二叉搜索树的特性 和 当前结点的值 进行判断
//        // 情况一：如果p、q在当前结点的两侧，那当前节点即为最近公共祖先
//        if ((p.val > root.val && q.val < root.val) || (p.val < root.val && q.val > root.val)) {
//            return root;
//        }
//        // 情况二：如果p、q其中一个等于当前结点，且不符合情况一，则当前结点就为最近公共祖先
//        if (p.val == root.val || q.val == root.val) {
//            return root;
//        }
//        // 情况三：如果情况一和二都不是，那两个目标点一定在左侧或者右侧，则递归下去
//        if (p.val < root.val && q.val < root.val) {
//            return lowestCommonAncestor(root.left, p, q);
//        } else {
//            return lowestCommonAncestor(root.right, p, q);
//        }
//    }

}


