/*
24. 两两交换链表中的节点

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例:
    给定 1->2->3->4, 你应该返回 2->1->4->3.
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

/*
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：36 MB, 在所有 Java 提交中击败了99.62%的用户
 */

public class Solution24 {

    public static void main(String[] args) {

    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {    // 无结点或者结点为一个时，不须交换
            return head;
        }
        ListNode eachSegLastNode = null;    // 指向上一个逆序分段的最后一个结点
        ListNode currentNode = head;    // 指向当前结点
        ListNode nextNode = head.next;  // 指向当前结点的下一个结点
        int count = 0;  // 记录当前是第几个节点
        while (currentNode != null) {
            count++;
            if (count == 2) {   // 第二个结点为新的head结点
                head = currentNode;
            }
            if (count % 2 != 0) {   // 如果当前结点是每段的第一个结点
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
            currentNode = nextNode; // 指针前移
            if (nextNode != null) {
                nextNode = nextNode.next;   // 指针前移
            }
        }
        eachSegLastNode.next = null;    // 最后将逆序后的末尾结点的next设为空
        return head;
    }

}
