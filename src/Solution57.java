/*
57. 插入区间
给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。



示例 1：

输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
输出：[[1,5],[6,9]]
示例 2：

输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出：[[1,2],[3,10],[12,16]]
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。


注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 */

import java.util.ArrayList;
import java.util.List;

public class Solution57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 存放结果区间
        List<int[]> resultList = new ArrayList<>();
        // 新区间是否放好
        boolean isNewPlaced = false;
        // 遍历每个区间
        for (int[] interval : intervals) {
            // 根据新区间与当前区间的位置进行分支
            if (interval[1] < newInterval[0]) { // 当前区间在新区间左侧，即 当前区间右侧 小于 新区间左侧
                resultList.add(interval);
            } else if (interval[0] > newInterval[1]) {  // 当前区间在新区间右侧，即 当前区间左侧 大于 新区间右侧
                // 此时代表后面要遍历的其余当前区间都在新区间后方，则将新区间放入结果中
                if (!isNewPlaced) {
                    resultList.add(new int[]{newInterval[0], newInterval[1]});
                    isNewPlaced = true;
                }
                resultList.add(interval);
            } else {    // 当前区间与新区间有重叠时，与新数组合并
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        // 如果新数组还没有在过程中放入结果，则放入最后
        if (!isNewPlaced) {
            resultList.add(new int[]{newInterval[0], newInterval[1]});
        }
        // 构建结果数组并返回
        int length = resultList.size();
        int[][] resultArr = new int[length][2];
        for (int i = 0; i < length; i++) {
            resultArr[i] = resultList.get(i);
        }
        return resultArr;
    }

}
