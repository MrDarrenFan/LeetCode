/*
142. 环形链表 II
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
如果 pos 是 -1，则在该链表中没有环。

说明：不允许修改给定的链表。

示例 1：
    输入：head = [3,2,0,-4], pos = 1
    输出：tail connects to node index 1
    解释：链表中有一个环，其尾部连接到第二个节点。

示例 2：
    输入：head = [1,2], pos = 0
    输出：tail connects to node index 0
    解释：链表中有一个环，其尾部连接到第一个节点。

示例 3：
    输入：head = [1], pos = -1
    输出：no cycle
    解释：链表中没有环。

进阶：
你是否可以不用额外空间解决此题？
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        // 先用快慢指针检测是否有环
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) { // 如果上述while停止后，slow != fast，代表无环
            return null;
        }

        /*
            从头到入环点的距离（即环外部分）设为 a
            入环点到快慢指针相遇的地方（slow指针走过的距离）设为 b
            环除b以外的距离设为 c
                slow 指针进入环后，又走了 b 的距离与 fast 相遇。此时，fast 指针已经走完了环的 n 圈
                则 快指针 走过的距离为 a + n(b + c) + b
                同时快指针走过的距离一定为慢指针的两倍
                则 a + n(b + c) + b = 2(a + b)
                所以 a = c + (n - 1)(b + c)
            从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
            因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 pos。
            起始，它指向链表头部；随后，它和slow 每次向后移动一个位置。最终，它们会在入环点相遇。
         */
        ListNode pos = head;
        while (pos != slow) {
            pos = pos.next;
            slow = slow.next;
        }
        return pos;
    }

}
