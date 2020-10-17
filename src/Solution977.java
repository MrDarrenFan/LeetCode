/*
977. 有序数组的平方
给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。



示例 1：

输入：[-4,-1,0,3,10]
输出：[0,1,9,16,100]
示例 2：

输入：[-7,-3,2,3,11]
输出：[4,9,9,49,121]
 */

public class Solution977 {

    public int[] sortedSquares(int[] A) {
        /*
            若直接计算完并排序，则时间复杂度为 O(nlogn)，空间复杂度为排序所需的栈空间 O(logn)
            因为原数组已经有顺序，且经过平方计算后，会得到中间为0，左边为降序，右边为升序的两段数字
            这样我们就可以利用归并排序的思想，再加上双指针，将时间复杂度降为 O(n)，空间复杂度降为 O(1)
        */
        // 存储最后排序结果（结果数组）
        int[] anwser = new int[A.length];
        // 从左右开始，将较大的平方数放入结果数组中
        for (int left = 0, right = A.length - 1, pos = anwser.length - 1; left <= right;) {
            // 利用归并排序中合并时的操作
            if (A[left] * A[left] >= A[right] * A[right]) {
                anwser[pos] = A[left] * A[left];
                left++;
            } else {
                anwser[pos] = A[right] * A[right];
                right--;
            }
            pos--;
        }
        return anwser;
    }

}
