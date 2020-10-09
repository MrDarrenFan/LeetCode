/*
75. 颜色分类
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？
 */

public class Question75 {

    public void sortColors(int[] nums) {
        int zeroPos = -1;   // [0, zeroPos]集合内元素全为0
        int twoPos = nums.length;   // [twoPos, nums.length-1]集合内元素全为2

        for (int currentPos = 0; currentPos < twoPos;) {
            if (nums[currentPos] == 0) {    // 如果当前数字为0，则加入[0, zeroPos]集合，即与集合后第一个元素交换。
                zeroPos++;
                nums[currentPos] = nums[zeroPos];
                nums[zeroPos] = 0;
                currentPos++;   // 此处可以不用再次判断换过来的元素，因为左边的都是遍历过的元素（注意与下方数字为2时区分）
            } else if (nums[currentPos] == 1) { // 如果当前数字为1，则什么也不需要做（因为目标顺序就是中间为1）
                currentPos++;
            } else {    // 如果当前数字为2，则加入[twoPos, nums.length-1]集合，即与集合前一个元素交换。
                twoPos--;
                nums[currentPos] = nums[twoPos];
                nums[twoPos] = 2;
                // 此处必须再次判断换过来的元素，因为右边元素还未遍历（注意与上方方数字为0时区分）
            }
        }
    }

}
