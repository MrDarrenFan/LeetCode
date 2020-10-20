/*
    该类为LeetCode中默认的链表结点类
 */

public class ListNode {

    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
