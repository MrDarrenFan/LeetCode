/*
40. 组合总和 II
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：
    所有数字（包括目标数）都是正整数。
    解集不能包含重复的组合。

示例 1:
    输入: candidates = [10,1,2,7,6,1,5], target = 8,
    所求解集为:
    [
      [1, 7],
      [1, 2, 5],
      [2, 6],
      [1, 1, 6]
    ]

示例 2:
    输入: candidates = [2,5,2,1,2], target = 5,
    所求解集为:
    [
      [1,2,2],
      [5]
    ]
 */

import java.util.*;

public class Solution40 {

    public static void main(String[] args) {
        Solution40 solution40 = new Solution40();
        System.out.println(solution40.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(candidates);    // 排序以后，减少回溯次数
        backTrack(candidates, target, result, combine, 0);
        return result;
    }

    // 回溯
    public void backTrack(int[] candidates, int curTarget, List<List<Integer>> result, List<Integer> combine, int curStartPos) {
        if (curTarget == 0) {
            result.add(new ArrayList<>(combine));
            return;
        }
        for (int i = curStartPos; i < candidates.length; i++) {
            // 过滤掉相同数字的选择
            if (i > curStartPos && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // 因为已排序，所以大于当前 target 值的可以省略回溯
            if (curTarget >= candidates[i]) {
                combine.add(candidates[i]);
                backTrack(candidates, curTarget - candidates[i], result, combine, i + 1);
                combine.remove(combine.size() - 1);
            } else {
                break;
            }
        }
    }

}
