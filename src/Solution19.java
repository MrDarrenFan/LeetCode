/*
19. 删除链表的倒数第N个节点
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class Solution19 {

    // 方法三：
    // （此题最优方法，方法一、二在下方记录）
    // 遍历链表一遍，使用双指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 哑结点，指向头节点
        ListNode dummyNode = new ListNode(0, head);
        // 使用双指针，两指针位置相差n+1，使得最后first为null时，second可以停到被删结点的前一个结点
        ListNode first = dummyNode;
        ListNode second = dummyNode;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // 错开n+1位置后，两指针同时向后走，最后first为null时，second可以停到被删结点的前一个结点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 删除倒数第n个结点
        ListNode deletedNode = second.next;    // 释放被删结点空间（面试时记得与面试官沟通是否需要）
        second.next = second.next.next;
        deletedNode.next = null;    // 释放被删结点空间（面试时记得与面试官沟通是否需要）
        return dummyNode.next;
    }

    // 方法二：
    // 遍历链表两遍，第一遍计算长度，第二遍找到相应需要删除的结点
    // 使用哑结点
    //      在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点。
    //      这样一来，我们就不需要对头节点进行特殊的判断了。
    // O(n), O(1)
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        // 哑结点，指向头节点
//        ListNode dummyNode = new ListNode(0, head);
//        // 计算链表长度
//        int length = 0;
//        while (head != null) {
//            length++;
//            head = head.next;
//        }
//        // 删除倒数第n个结点
//        ListNode curNode = dummyNode;
//        for (int i = 1; i < length - n + 1; i++) {  // 到达倒数第n个结点的前一个结点停下
//            curNode = curNode.next;
//        }
//        ListNode deletedNode = curNode.next;    // 释放被删结点空间（面试时记得与面试官沟通是否需要）
//        curNode.next = curNode.next.next;
//        deletedNode.next = null;    // 释放被删结点空间（面试时记得与面试官沟通是否需要）
//        return dummyNode.next;
//    }


    // 方法一：
    // 遍历链表两遍，第一遍计算长度，第二遍找到相应需要删除的结点
    // 不使用哑结点，需对头结点进行特殊处理
    // O(n), O(1)
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        // 计算链表长度
//        ListNode curNode = head;
//        int length = 0;
//        while (curNode != null) {
//            length++;
//            curNode = curNode.next;
//        }
//        // 删除倒数第n个结点
//        curNode = head;
//        int curCount = 0;
//        ListNode preNode = null;
//        while (curNode != null) {
//            curCount++;
//            if (curCount == length - n) {
//                preNode = curNode;
//                curNode = curNode.next;
//            } else if (curCount == length - n + 1) {
//                if (preNode == null) {  // 头结点进行特殊处理
//                    head = curNode.next;
//                } else {
//                    preNode.next = curNode.next;
//                }
//                curNode.next = null;  // 释放被删结点空间（面试时记得与面试官沟通是否需要）
//                return head;
//            } else {
//                curNode = curNode.next;
//            }
//        }
//        return head;
//    }

}
