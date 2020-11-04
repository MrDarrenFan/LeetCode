/*
941. 有效的山脉数组

给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
A.length >= 3
在 0 < i < A.length - 1 条件下，存在 i 使得：
    A[0] < A[1] < ... A[i-1] < A[i]
    A[i] > A[i+1] > ... > A[A.length - 1]

示例 1：
    输入：[2,1]
    输出：false

示例 2：
    输入：[3,5,5]
    输出：false

示例 3：
    输入：[0,3,2,1]
    输出：true

提示：
    0 <= A.length <= 10000
    0 <= A[i] <= 10000
 */

public class Solution941 {

    // 模拟一次上下山，看最后是否停在在最后一个位置
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int length = A.length;
        int count = 0;
        int pos = 0;
        // 上山
        while (pos < length - 1 && A[pos] < A[pos + 1]) {
            pos++;
        }
        // 最高点不能是数组第一个或最后一个位置
        if (pos == 0 || pos == length - 1) {
            return false;
        }
        // 下山
        while (pos < length - 1 && A[pos] > A[pos + 1]) {
            pos++;
        }
        // 看是否停在最后一个位置
        return pos == length - 1;
    }

}
