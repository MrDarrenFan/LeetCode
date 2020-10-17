/*
116. 填充每个节点的下一个右侧节点指针

    给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

    struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
    }
    填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

    初始状态下，所有 next 指针都被设置为 NULL。

示例：
    输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
    输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
    解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。

提示：
    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */

public class Solution116 {

    public static void main(String[] args) {

    }

    // 遍历当前层结点时，建立下一层子结点的next关系
    // 此思想相较于队列层次遍历可以省下额外空间，使空间复杂度优化为O(1)
    // 因为是完美二叉树（满二叉树），有规律，所以要比117题简略很多
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node currentLevelFirst = root;  // 每一层的首个结点，初始就为root结点

        while (currentLevelFirst.left != null) {
            // 遍历这一层结点组织成的链表，为下一层的结点更新 next 指针
            for (Node currentNode = currentLevelFirst; currentNode != null; currentNode = currentNode.next) {
                // 连接当前结点的左右子结点
                currentNode.left.next = currentNode.right;
                // 如果当前结点有下一个相邻结点
                // 则连接自己的右子结点和相邻结点的左子结点
                if (currentNode.next != null) {
                    currentNode.right.next = currentNode.next.left;
                }
            }
            // 到下一层
            currentLevelFirst = currentLevelFirst.left;
        }

        return root;
    }

    // 该题题目所给的数据结构
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
