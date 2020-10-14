/*

3. 无重复字符的最长子串
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 */

import java.util.HashSet;
import java.util.Set;

public class Solution3 {

    public static void main(String[] args) {

    }

    /*
        1. 涉及出现次数，需要用到散列表
        2. 涉及子串，考虑滑动窗口
     */
    // 使用滑动窗口和set
    // 以下方法可用hashmap代替set进行优化
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> alreadyGotChar = new HashSet<Character>();
        int answer = 0;
        int left = 0;
        int right = 0;

        while (left < n && right < n) {
            char currentChar = s.charAt(right);
            if (!alreadyGotChar.contains(currentChar)) {
                alreadyGotChar.add(currentChar);
                answer = Math.max(answer, right - left + 1);
                right++;
            } else {
                alreadyGotChar.remove(s.charAt(left));
                left++;
            }
        }

        return answer;
    }

}