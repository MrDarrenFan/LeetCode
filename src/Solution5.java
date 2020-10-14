/*
5. 最长回文子串

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
示例 2：
    输入: "cbbd"
    输出: "bb"

 */

/*
思路包括以下几种不等：
    动态规划
    中心扩展算法
    Manacher算法
 */

public class Solution5 {

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.longestPalindrome("babad"));
    }

    // 中心扩展算法，时间复杂度O(n^2)，空间复杂度O(1)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // [start, end) 表示s上的最长回文字串
        int start = 0;
        int end = 0;
        // 以字符串s中的每个字符或两个字符作为起点进行中心扩展算法
        for (int i = 0; i < s.length(); i++) {
            int tempLen1 = expandAroundCenter(s, i, i); // 字符个数为奇数的回文子串
            int tempLen2 = expandAroundCenter(s, i, i + 1); // 字符个数为偶数的回文子串
            int currentLen = Math.max(tempLen1, tempLen2);
            if (currentLen > end - start) { // 当当前回文子串大于当前记录的最大长度时，更新start、end记录
                start = i - (currentLen - 1) / 2;
                end = i + (currentLen + 2) / 2;
            }
        }
        return s.substring(start, end);
    }

    // 在合法的范围内，如果left与right相等，则向两边扩展
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

}
