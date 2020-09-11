/*
279. 完全平方数
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.
 */

import java.util.Arrays;

public class Question279 {

    public static void main(String[] args) {
        System.out.println(numSquares(13));
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];  // dp[i]表示n为i时的所需的完全平方数的最小个数
        for (int i = 1; i <= n; i++) {
            int currentMin = Integer.MAX_VALUE;
            int x = 1;  // 用于表示每轮的1、4、9、...
            for (int j = 1; j <= i; j = (int) Math.pow(x, 2)) {
                currentMin = Math.min(currentMin, dp[i-j] + 1);
                x++;
            }
            dp[i] = currentMin;
        }
        return dp[n];
    }

}
