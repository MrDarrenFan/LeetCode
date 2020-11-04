/*
349. 两个数组的交集
给定两个数组，编写一个函数来计算它们的交集。

示例 1：
    输入：nums1 = [1,2,2,1], nums2 = [2,2]
    输出：[2]

示例 2：
    输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    输出：[9,4]

说明：
    输出结果中的每个元素一定是唯一的。
    我们可以不考虑输出结果的顺序。
 */

import java.util.HashSet;
import java.util.Set;

public class Solution349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> filter1 = new HashSet<>();
        Set<Integer> filter2 = new HashSet<>();
        for (int num : nums1) {
            filter1.add(num);
        }
        for (int num : nums2) {
            if (filter1.contains(num)) {
                filter2.add(num);
            }
        }
        Integer[] tempArr = filter2.toArray(new Integer[]{});
        int length = tempArr.length;
        int[] ansArr = new int[length];
        for(int i = 0; i < length; i++) {
            ansArr[i] = tempArr[i];
        }
        return ansArr;
    }

}
