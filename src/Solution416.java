/*
416. 分割等和子集
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:
    每个数组中的元素不会超过 100
    数组的大小不会超过 200

示例 1:
    输入: [1, 5, 11, 5]
    输出: true
    解释: 数组可以分割成 [1, 5, 5] 和 [11].

示例 2:
    输入: [1, 2, 3, 5]
    输出: false
    解释: 数组不能分割成两个元素和相等的子集.
 */

/*
方法一：动态规划

思路与算法

这道题可以换一种表述：给定一个只包含正整数的非空数组 nums[0]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。
因此这个问题可以转换成「0-1 背包问题」。这道题与传统的「0-1 背包问题」的区别在于，传统的「0-1 背包问题」要求选取的物品的重量之和不能超过背包的总容量，
这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。类似于传统的「0-1 背包问题」，可以使用动态规划求解。

在使用动态规划求解之前，首先需要进行以下判断。

根据数组的长度 n 判断数组是否可以被划分。如果 n < 2，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。

计算整个数组的元素和 sum 以及最大元素 maxNum。如果 sum 是奇数，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。
如果 sum 是偶数，则令 target = sum / 2 ，需要判断是否可以从数组中选出一些数字，使得这些数字的和等于 target。
如果 maxNum > target，则除了 maxNum 以外的所有元素之和一定小于 target，因此不可能将数组分割成元素和相等的两个子集，直接返回 false。

创建二维数组 dp，包含 n 行 target+1 列，其中 dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），
是否存在一种选取方案使得被选取的正整数的和等于 j。初始时，dp 中的全部元素都是 false。

在定义状态之后，需要考虑边界情况。以下两种情况都属于边界情况：
    如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 0 ≤ i < n，都有 dp[i][0] = true。
    当 i = 0 时，只有一个正整数 nums[0] 可以被选取，因此 dp[0][nums[0]] = true。

对于 i > 0 且 j > 0 的情况，如何确定 dp[i][j] 的值？需要分别考虑以下两种情况：
    如果 j ≥ nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取，两种情况只要有一个为 true，就有 dp[i][j] = true。
        如果不选取 nums[i]，则 dp[i][j] = dp[i−1][j]；
        如果选取 nums[i]，则 dp[i][j] = dp[i−1][j−nums[i]]。
    如果 j < nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i]，因此有 dp[i][j] = dp[i−1][j]。

状态转移方程如下：
    dp[i][j] = {
        dp[i−1][j] 或者 dp[i−1][j−nums[i]],       j ≥ nums[i]
        dp[i−1][j],     j < nums[i]
最终得到 dp[n−1][target] 即为答案。
 */

public class Solution416 {

    public boolean canPartition(int[] nums) {
        // 若数组长度小于2，则无法划分
        int len = nums.length;
        if (len < 2) {
            return false;
        }

        // 计算数组和 sum，并寻找最大元素值，并提前验证可以判断是否划分的情况
        int sum = 0;
        int maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) { // 若 sum 为奇数，则无法划分
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {  // 若 最大元素 大于 target，则剩余元素和 小于 target，则无法划分。
            return false;
        }

        // dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），
        // 是否存在一种选取方案使得被选取的正整数的和等于 j。初始时，dp 中的全部元素都是 false。
        boolean[][] dp = new boolean[len][target + 1];
        // 边界情况1：j = 0的时候，代表需要选取的数字的和为0，则代表不用选，即都可以成功，所以都为true
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        // 边界情况2：当 i = 0 时，只有一个正整数 nums[0] 可以被选取，因此 dp[0][nums[0]] = true。
        dp[0][nums[0]] = true;
        // 开始dp，更新状态
        for (int i = 1; i < len; i++) {
            int currentNum = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= currentNum) {  // 当 所需数字和 大于等于 当前数字 时，可以选择 不选当前数字 或者 选。
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - currentNum];
                } else {    // 当 所需数字和 小于 当前数字 时，则不能选择当前数字
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[len - 1][target];
    }

}
