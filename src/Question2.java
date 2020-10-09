/*
2. 两数相加
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
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

public class Question2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;  // 构建结果链表，head为头，tail为尾
        int carry = 0;  // 表示进位

        while (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;  // 计算当前位总和

            if (head == null) {     // 如果head为空，则此时为首结点
                head = tail = new ListNode(sum % 10);
            } else {    // head不为空，则构建到结果链表尾部，并维护尾指针
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }

            carry = sum / 10;   // 计算进位

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 若最后计算完进位仍不为0，则结果链表再次构建一位
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }

}
