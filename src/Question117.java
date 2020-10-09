/*
117. 填充每个节点的下一个右侧节点指针 II
给定一个二叉树

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

进阶：

你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

示例：

输入：root = [1,2,3,4,5,null,7]
输出：[1,#,2,3,#,4,5,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。

提示：

树中的节点数小于 6000
-100<= node.val <= 100
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

public class Question117 {

    public static void main(String[] args) {

    }

    // 遍历当前层结点时，建立下一层子结点的next关系
    // 此思想相较于队列层次遍历可以省下额外空间，使空间复杂度优化为O(1)
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node currentLevelFirst = root;  // 每一层的首个结点，初始就为root结点
        Node nextLevelCurrentLast = null;   // 为下一层构建next时，指向当前位置下一层的前一个结点，为连接next做准备
        Node nextLevelFirst = null;     // 记录下一层的首个结点，为切换到下一层做准备

        // 每一层开始遍历并构建下一层的next关系，一次循环代表一层
        while (currentLevelFirst != null) {
            // 每层开始时，两指针要重置
            nextLevelCurrentLast = null;
            nextLevelFirst = null;
            // 遍历当前层的next链，为下一层构建next
            for (Node currentNode = currentLevelFirst; currentNode != null; currentNode = currentNode.next) {
                // 如果左子结点不为空，则为其构建next
                if (currentNode.left != null) {
                    // 判断是否为下一层第一个结点
                    if (nextLevelFirst == null) {
                        nextLevelFirst = currentNode.left;
                    } else {
                        nextLevelCurrentLast.next = currentNode.left;
                    }
                    nextLevelCurrentLast = currentNode.left;
                }
                // 如果右子结点不为空，则为其构建next
                if (currentNode.right != null) {
                    // 判断是否为下一层第一个结点
                    if (nextLevelFirst == null) {
                        nextLevelFirst = currentNode.right;
                    } else {
                        nextLevelCurrentLast.next = currentNode.right;
                    }
                    nextLevelCurrentLast = currentNode.right;
                }
            }
            // 该层遍历结束，切换到下一层
            currentLevelFirst = nextLevelFirst;
        }
        return root;
    }

    // 该题数据结构
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
