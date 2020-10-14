/*
501. 二叉搜索树中的众数
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
例如：
给定 BST [1,null,2,2],

   1
    \
     2
    /
   2
返回[2].

提示：如果众数超过1个，不需考虑输出顺序

进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */

import java.util.ArrayList;
import java.util.List;

public class Solution501 {

    public static void main(String[] args) {

    }

    List<Integer> result = new ArrayList<>();   // 记录结果
    TreeNode preNode = null;   // 指向当前遍历到的结点的前驱
    int count = 0;  // 记录某数出现的次数
    int maxCount = 0;   // 记录当前众数出现了多少次

    // 进阶做法：除递归外不使用额外空间
    public int[] findMode(TreeNode root) {
        inorder(root);
        // 这里使用流来返回结果
        // 如果使用toArray()，返回的是Object[]，类型不匹配
        // 如果使用toArray(T[] a)，里面为要转换的类型，因为T为泛型，所以必须填入引用类型，所以填toArray(new int[0])会报错
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    // 中序遍历（因为二叉搜索树的中序遍历是一个有序数列）
    public void inorder(TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }

        // 左
        inorder(currentNode.left);

        // 中
        if (preNode == null) {  // 若当前结点为第一个结点，则直接计数
            count = 1;
        } else if (currentNode.val == preNode.val) {    // 若与前驱相等，则计数
            count++;
        } else {    // 若与前驱不等
            count = 1;
        }
        preNode = currentNode;  // 更新前驱
        if (count == maxCount) {    // 如果与当前众数出现次数相同，则放入结果集中
            result.add(currentNode.val);
        }
        if (count > maxCount) { // 如果当前数出现次数大于当前众数出现的次数，则清空结果集，记录新众数
            maxCount = count;
            result.clear();
            result.add(currentNode.val);
        }

        // 右
        inorder(currentNode.right);

        return;
    }

}
