/*
剑指 Offer 42. 连续子数组的最大和
输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。



示例1:

输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。


提示：

1 <= arr.length <= 10^5
-100 <= arr[i] <= 100
注意：本题与主站 53 题相同
 */

public class SolutionJianZhiOffer42 {

    public int maxSubArray(int[] nums) {
        // 动态规划
        // dp[i]表示nums中以nums[i]结尾的最大子序和
        // dp[i] = Math.max(dp[i - 1] + nums[i], nums[i])
        // 根据递推公式可以得出，dp[i]只与dp[i - 1]和nums[i]有关，所以用一个变量代替dp数组
        int curMaxSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMaxSum = Math.max(curMaxSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, curMaxSum);
        }
        return maxSum;
    }

}
