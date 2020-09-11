/*
63. 不同路径 II

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:

输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
 */

public class Question63 {

    public static void main(String[] args) {

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n]; // dp[i][j]表示到点(i, j)的最多路径数

        // 边界初始化，不遇到障碍为1，遇到障碍以后，该行/列后面都为0.
        //（因为只能向右或向下走，有障碍左和上边界则到不了）
        boolean hasObstacle = false;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                hasObstacle = true;
            }
            if (hasObstacle) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = 1;
            }
        }
        hasObstacle = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                hasObstacle = true;
            }
            if (hasObstacle) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }

        // 开始dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {  // 遇到障碍，将该障碍上的最大路径数置为0，然后跳过
                    dp[i][j] = 0;
                    continue;
                }
                // 每个点可以从上来或者从左来，两边路径数相加得到当前点路径
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

}
