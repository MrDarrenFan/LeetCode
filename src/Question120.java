/*
120. 三角形最小路径和
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

例如，给定三角形：

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */

import java.util.List;

public class Question120 {

    public static void main(String[] args) {

    }

    // 我们发现dp[i][j]的状态只与dp[i+1][...]有关
    // 所以dp数组不需要定义N行，只需要1行就可以。空间复杂度就可以优化到O(n)
    // 自底向上dp
    // 时间复杂度为O(n^2)，空间复杂度为O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        int numOfRows = triangle.size();
        int[] dp = new int[numOfRows+1];    // dp[j] 表示从点 (i, j) 到底边的最小路径和。
        for (int i = numOfRows - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    // 这个方法为二维数组上的动态规划，按照List下标行进
    // 自底向上dp
    // 时间复杂度为O(n^2)，空间复杂度为O(n^2)
//    public int minimumTotal(List<List<Integer>> triangle) {
//        int numOfRows = triangle.size();
//        int[][] dp = new int[numOfRows + 1][numOfRows + 1];     // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
//        // 从三角形底开始递推
//        for (int i = numOfRows - 1; i >= 0; i--) {
//            for (int j = 0; j <= i; j++) {
//                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
//            }
//        }
//        return dp[0][0];
//    }

    // 这个方法为二维数组上的动态规划，且是按照二维数组下标来遍历，在运算List下标时较为复杂
    // 自顶向下dp相较于自底向上也较为复杂
    // 时间复杂度为O(n^2)，空间复杂度为O(n^2)
//    public int minimumTotal(List<List<Integer>> triangle) {
//        int numOfRows = triangle.size();
//        int[][] dp = new int[numOfRows][numOfRows];   // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
//        dp[0][0] = triangle.get(0).get(0);
//        // 填充上边界
//        for (int i = 1; i < numOfRows; i++) {
//            dp[0][i] = dp[0][i-1] + triangle.get(i).get(i);
//        }
//        // 填充左边界
//        for (int i = 1; i < numOfRows; i++) {
//            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
//        }
//        // 开始dp
//        for (int i = 1; i < numOfRows-1; i++) {
//            for (int j = 1; j < numOfRows-i; j++) {
//                // 二维数组与二维List位置对应关系为：二维数组下标(i, j) = 二维List(i+j, j)
//                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + triangle.get(i+j).get(j);
//            }
//        }
//        // 找出最小值
//        int result = Integer.MAX_VALUE;
//        for (int i = numOfRows-1, j = 0; i >= 0 && j < numOfRows; i--, j++) {
//            result = Math.min(result, dp[i][j]);
//        }
//        return result;
//    }

}
