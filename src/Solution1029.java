/*
1029. 两地调度

公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。

返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。

示例：
    输入：[[10,20],[30,200],[400,50],[30,20]]
    输出：110
解释：
    第一个人去 A 市，费用为 10。
    第二个人去 A 市，费用为 30。
    第三个人去 B 市，费用为 50。
    第四个人去 B 市，费用为 20。

    最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。

提示：
    1 <= costs.length <= 100
    costs.length 为偶数
    1 <= costs[i][0], costs[i][1] <= 1000
 */

import java.util.Arrays;

public class Solution1029 {

    public static void main(String[] args) {
        Solution1029 solution1029 = new Solution1029();
        System.out.println(solution1029.twoCitySchedCost(new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}}));
    }

    public int twoCitySchedCost(int[][] costs) {
        // 存储每个人 去B费用 - 去A费用 的 差值
        int[] sub = new int[costs.length];
        // 记录最小花费
        int minCost = 0;
        // 统计每个人的差值
        // 先假设所有人都去A，统计花费
        for (int i = 0; i < costs.length; i++) {
            sub[i] = costs[i][1] - costs[i][0];
            minCost += costs[i][0];
        }
        // 对差值进行排序，靠前的代表从A转换为去B所需的代价最小
        Arrays.sort(sub);
        // 将代价最小的一半转化为去B，并计算最小花费
        for (int i = 0; i < costs.length / 2; i++) {
            minCost += sub[i];
        }
        return minCost;
    }

}
