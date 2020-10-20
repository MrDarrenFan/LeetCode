/*
143. 重排链表
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:

给定链表 1->2->3->4, 重新排列为 1->4->2->3.
示例 2:

给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */

/*
执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
内存消耗：40.4 MB, 在所有 Java 提交中击败了 99.91% 的用户
 */

public class Solution143 {

    // 寻找链表中点 -> 逆转后半链表 -> 重排链表
    // O(n), O(1)
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 用快慢指针找到链表中间位置，slow指针最后停在需要逆向的起始节点的前一个结点，fast指针停在链表尾部
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            slow = slow.next;
            if (fast.next.next != null) {
                fast = fast.next.next;
            } else {    //
                fast = fast.next;
            }
        }
        // 从slow后一个结点开始逆转后半部分链表
        reverse(slow);
        // 链表两端开始重排结点
        ListNode leftNode = head;
        ListNode rightNode = fast;
        ListNode leftNextNode = leftNode.next;
        ListNode rightNextNode = rightNode.next;
        while(rightNextNode != null) {
            leftNode.next = rightNode;
            rightNode.next = leftNextNode;
            leftNode = leftNextNode;
            rightNode = rightNextNode;
            leftNextNode = leftNextNode.next;
            rightNextNode = rightNextNode.next;
        }
    }

    // 从preNode的下一个结点开始逆转链表
    public void reverse(ListNode preNode) {
        ListNode curNode = preNode.next;
        preNode.next = null;
        ListNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
    }

}
