/*

1. 两数之和
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。



示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

 */

import java.util.HashMap;
import java.util.Map;

public class Solution1 {

    public static void main(String[] args) {

    }

    // 使用哈希表，在遍历时存入<数字, 下标>，用空间换时间      时复O(n) 空复O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numAndIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) { // 遍历每个数字
            int complement = target - nums[i];  // 当前数字的互补数
            if (numAndIndex.containsKey(complement)) {  // 在map中寻找该数字的互补数
                return new int[] {numAndIndex.get(complement), i};  // 在map中的数字一定比当前下标更靠前
            }
            numAndIndex.put(nums[i], i);    // 若未找到则放入当前数字
        }
        throw new IllegalArgumentException("无解");
    }

}
