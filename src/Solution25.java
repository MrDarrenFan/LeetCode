/*
25. K 个一组翻转链表

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

示例：

给你这个链表：1->2->3->4->5
当 k = 2 时，应当返回: 2->1->4->3->5
当 k = 3 时，应当返回: 3->2->1->4->5

说明：

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution25 {

    public static void main(String[] args) {

    }

    public static void reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {    // 无结点或者结点为一个时，不须交换
            return;
        }
        ListNode eachSegLastNode = null;    // 指向上一个逆序分段的最后一个结点
        ListNode preNode = null;    // 指向当前结点的上一个结点
        ListNode currentNode = head;    // 指向当前结点
        ListNode nextNode = head.next;  // 指向当前结点的下一个结点
        int count = 0;  // 记录当前是第几个节点
        while (currentNode != null) {
            count++;
            if (count == k) {   // 第k个结点为新的head结点
                head = currentNode;
            }
            if (count % k != 0) {   // 如果当前结点不是每段的最后一个结点
                if (eachSegLastNode != null) {  // 若为第一个分段（无上一个分段）
                    if (currentNode.next != null) { // 若该分段有两个结点
                        eachSegLastNode.next = currentNode.next;    // 则将上一段最后一个结点指向当前这一段的头结点（即为当前结点的下一个结点）
                    } else {    // 若该分段只有一个结点（即两个为一段，只有第一个）（且可以判断是当前链表最后一个结点）
                        eachSegLastNode.next = currentNode; // 则将上一段最后一个结点指向当前这一段的头结点（即为当前结点）
                        eachSegLastNode = currentNode;  // 且因为当前分段是链表最后一个，且为链表最后一个结点，则将指针移到正确的位置上
                        break;  // 结束循环
                    }
                }
                eachSegLastNode = currentNode;  // 每段第一个结点即为逆序后的最后一个结点
            } else {    // 如果当前结点不是每段的第一个结点（按照该题意则为每段第二个结点）
                // 这里应该为当前结点前一个结点，即为preNode，但该题每段只有两个结点，所以preNode可用eachSegLastNode代替
                currentNode.next = eachSegLastNode; // 逆序当前段
            }
            currentNode = nextNode; // 指针后移
            if (nextNode != null) {
                nextNode = nextNode.next;   // 指针后移
            }
        }
        eachSegLastNode.next = null;    // 最后将逆序后的末尾结点的next设为空
//        return head;
    }

//    public static void reverseKGroup(ListNode head, int k) {
//        if (head == null || head.next == null) {
//            return;
//        }
//        ListNode pre = head;
//        ListNode begin = head.next;
//        ListNode end = null;
//        ListNode tempEnd = null;
//
//        int count = 1;
//        while (begin != null) {
//            end = begin;
//            for (; count<k; count++) {
//                if (end.next != null) {
//                    end = end.next;
//                } else {
//                    return;
//                }
//            }
//            tempEnd = end.next;
//            end.next = null;
//
//            pre.next = reverse(begin);
//
//            begin.next = tempEnd;
//            pre = begin;
//            begin = tempEnd;
//
//            count = 1;
//        }
//    }
//
//    public static ListNode reverse(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode pre = head;
//        ListNode cur = head.next;
//        ListNode next = cur.next;
//        pre.next = null;
//
//        while (cur != null) {
//            next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//
//        return pre;
//    }

}
