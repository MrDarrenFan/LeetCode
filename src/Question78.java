/*
78. 子集
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question78 {

    public static void main(String[] args) {

    }

    List<List<Integer>> result = new LinkedList<>();
    List<Integer> oneSubset = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTracking(nums, 0);
        return result;
    }

    public void backTracking(int[] nums, int startIndex) {
//        result.add(oneSubset);
        // 注意这里不能 result.add(oneSubset)，要根据当前list的值构建一个ArrayList
        // 因为这样操作会把 oneSubset 的地址传过去，这样最后oneSubset的状态是什么，就会得到什么，这不符合我们要操作的含义
        result.add(new ArrayList<>(oneSubset));
        for (int i = startIndex; i < nums.length; i++) {
            oneSubset.add(nums[i]);
            backTracking(nums, i + 1);
            oneSubset.remove(oneSubset.size() - 1);
        }
    }

}
