/*
234. 回文链表
请判断一个链表是否为回文链表。

示例 1:
    输入: 1->2
    输出: false
示例 2:
    输入: 1->2->2->1
    输出: true
进阶：
    你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution234 {

    // 1. 快慢指针找到链表中间位置
    // 2. 然后逆转后一段链表
    // 3. 前后两段同时开始遍历，检查是否为回文
    // O(n)，O(1)，满足进阶要求
    public boolean isPalindrome(ListNode head) {
        // 特殊值校验
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val ? true : false;
        }
        // 1. 快慢指针找到链表中间位置
        // slow 停在中间位置（偶数个链表停在中间两结点中第一个结点的位置）
        // fast 停在链表末尾
        // （fast初始在slow后一个，主要是为了调整停止的位置）
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            // 这里加入判断是为了在奇数个链表中，让 fast 停在链表最后一个结点
            if (fast.next.next != null) {
                fast = fast.next.next;
            } else {
                fast = fast.next;
            }
        }
        // 2. 然后逆转后一段链表
        reverse(slow);
        // 3. 前后两段同时开始遍历，检查是否为回文
        while (fast != slow) {
            if (head.val != fast.val) {
                return false;
            }
            head = head.next;
            fast = fast.next;
        }
        return true;
    }

    // 逆转链表，传入的结点为需逆转链表的前一个结点
    private void reverse(ListNode preNode) {
        // 初始化所需指针
        ListNode curNode = preNode.next;
        preNode.next = null;
        ListNode nextNode;
        // 开始逆转
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
    }

}
