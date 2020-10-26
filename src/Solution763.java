/*
763. 划分字母区间
字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

示例 1：
    输入：S = "ababcbacadefegdehijhklij"
    输出：[9,7,8]
    解释：
    划分结果为 "ababcbaca", "defegde", "hijhklij"。
    每个字母最多出现在一个片段中。
    像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。

提示：
    S的长度在[1, 500]之间。
    S只包含小写字母 'a' 到 'z' 。
 */

import java.util.ArrayList;
import java.util.List;

public class Solution763 {

    // 贪心 + 双指针
    public List<Integer> partitionLabels(String S) {
        // 记录每个字符（小写字母）最后一次出现的位置
        int[] lastOfChars = new int[26];    // 若题目不只限制为小写字母，则就要使用 map
        int length = S.length();
        for (int i = 0; i < length; i++) {
            lastOfChars[S.charAt(i) - 'a'] = i;
        }
        // 记录结果
        List<Integer> partitionResult = new ArrayList<>();
        // 遍历字符串，start、end分别维护当前分段的首尾位置
        // end 应为：当前字符最后一次出现的位置 和 end原来值 其中较大的一个
        // 当遍历位置 = end 时，代表当前分段遍历完毕
        int start = 0;
        int end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, lastOfChars[S.charAt(i) - 'a']);
            if (i == end) { // 到达当前分段末尾位置，则记录结果
                partitionResult.add(end - start + 1);
                start = end + 1;    // 从 end 的下一个位置开始遍历下一个分段
            }
        }
        return partitionResult;
    }

}
