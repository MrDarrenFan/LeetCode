/*
39. 组合总和

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：
    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合。

示例 1：
    输入：candidates = [2,3,6,7], target = 7,
    所求解集为：
    [
      [7],
      [2,2,3]
    ]

示例 2：
    输入：candidates = [2,3,5], target = 8,
    所求解集为：
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]

提示：
    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    candidate 中的每个元素都是独一无二的。
    1 <= target <= 500
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            // 因为已排序，所以大于当前 target 值的可以省略回溯
            if (curTarget >= candidates[i]) {
                combine.add(candidates[i]);
                backTrack(candidates, curTarget - candidates[i], result, combine, i);
                combine.remove(combine.size() - 1);
            } else {
                break;
            }
        }
    }

}
