/*
845. 数组中的最长山脉

我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：

B.length >= 3
存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
（注意：B 可以是 A 的任意子数组，包括整个数组 A。）

给出一个整数数组 A，返回最长 “山脉” 的长度。

如果不含有 “山脉” 则返回 0。

示例 1：
    输入：[2,1,4,7,3,2,5]
    输出：5
    解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
示例 2：
    输入：[2,2,2]
    输出：0
    解释：不含 “山脉”。
提示：
    0 <= A.length <= 10000
    0 <= A[i] <= 10000
 */

public class Solution845 {

    public static void main(String[] args) {
        Solution845 solution845 = new Solution845();
        System.out.println(solution845.longestMountain(new int[]{875,884,239,731,723,685}));
    }

    // 模拟上下坡法
    // O(n), O(1)
    public int longestMountain(int[] A) {
        // 最少需要3个数字才能构成山脉
        if (A == null || A.length < 3) {
            return 0;
        }
        int length = A.length;
        int goUp = 0;
        int goDown = 0;
        int maxLengthOfMountain = 0;
        int pos = 1;
        while (pos < length) {
            // 每经历一次上下坡，初始化
            goUp = 0;
            goDown = 0;
            // 上坡
            while (pos < length && A[pos - 1] < A[pos]) {
                goUp++;
                pos++;
            }
            // 下坡
            while (pos < length && A[pos - 1] > A[pos]) {
                goDown++;
                pos++;
            }
            // 判断是否完整完成一次上下坡
            if (goUp > 0 && goDown > 0) {   // 若完整，则记录最大长度
                maxLengthOfMountain = Math.max(goUp + goDown + 1, maxLengthOfMountain);
            }
            // 若山脉平缓，则跳过
            while (pos < length && A[pos - 1] == A[pos]) {
                pos++;
            }
        }
        return maxLengthOfMountain;
    }

}
