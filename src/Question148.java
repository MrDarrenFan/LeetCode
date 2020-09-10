/*

148. 排序链表
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

输入: 4->2->1->3
输出: 1->2->3->4
示例 2:

输入: -1->5->3->4->0
输出: -1->0->3->4->5

 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
思路：
一、递归归并排序
二、非归并排序
 */

public class Question148 {

    public static void main(String[] args) {

    }

    /*
    思路一
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 得到中间节点
        ListNode mid = getMidNode(head);
        // 断开链表，mid作为第一段尾部，right为第二段头部
        ListNode right = mid.next;
        mid.next = null;
        return mergeSort(sortList(head), sortList(right));
    }

    // 得到链表中间节点，使用快慢指针
    public ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        // slow走一步，fast走两步
        while (fast.next != null && fast.next.next != null) {   // 因为fast每次移动两步，所以要检查后面两个位置
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 归并两个有序的链表
    public ListNode mergeSort(ListNode head1, ListNode head2) {
        ListNode index1 = head1;
        ListNode index2 = head2;
        ListNode newHead = null;

        // 得到合并后链表头结点newHead的指向
        if (head1.val < head2.val) {
            newHead = head1;
            index1 = index1.next;
        } else {
            newHead = head2;
            index2 = index2.next;
        }

        // 合并后链表的尾部指针
        ListNode newIndex = newHead;

        // 逐步比较两链表中的值
        while (index1 != null && index2 != null) {
            if (index1.val < index2.val) {
                newIndex.next = index1;
                index1 = index1.next;
                newIndex = newIndex.next;
            } else {
                newIndex.next = index2;
                index2 = index2.next;
                newIndex = newIndex.next;
            }
        }

        // 第二条链表先空，则第一条链表有剩余元素
        if (index1 != null) {
            newIndex.next = index1;
        }
        // 第一条链表先空，则第二条链表有剩余元素
        if (index2 != null) {
            newIndex.next = index2;
        }

        return newHead;
    }

    /*
    思路二
     */


}
