/*
剑指 Offer 06. 从尾到头打印链表
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：

输入：head = [1,3,2]
输出：[2,3,1]


限制：

0 <= 链表长度 <= 10000
 */

import java.util.ArrayList;

public class QuestionJianZhiOffer6 {

    public static void main(String[] args) {

    }

    // 方法一：
    //      使用ArrayList辅助存储
//    public int[] reversePrint(ListNode head) {
//        ArrayList<Integer> numsList = new ArrayList<>();
//        ListNode currentNode = head;
//        while (currentNode != null) {
//            numsList.add(currentNode.val);
//            currentNode = currentNode.next;
//        }
//        int size = numsList.size();
//        int[] result = new int[size];
//        for (int i = 0; i < size; i++) {
//            result[size - i - 1] = numsList.get(i);
//        }
//        return result;
//    }

    // 方法二：
    //      上面的方法也要遍历两边，遍历链表的方法同样也需要两边，那么为什么要多使用一个ArrayList呢，所以：
    public int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }

        int[] nums = new int[count];
        currentNode = head;
        for (int i = count-1; i >= 0; i--) {
            nums[i] = currentNode.val;
            currentNode = currentNode.next;
        }
        return nums;
    }

}
