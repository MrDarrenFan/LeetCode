/*
91. 解码方法
一条包含字母 A-Z 的消息通过以下方式进行了编码：

'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

示例 1:

输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2:

输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */

public class Solution91 {

    public static void main(String[] args) {
        System.out.println(numDecodings("10"));
    }

    public static int numDecodings(String s) {
        // 处理该题的智障特殊情况判断
        if ("1".equals(s)) {
            return 1;
        }
        if (s.charAt(0) == '0') {   // 首字母为0为不可解码
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];  // dp[i]表示从字符串s位置从0到i的串有几种解码方法
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            /*
                每遍历到一个新的数字，有两种选择
                     第一种是当前数字与前边数字组合，解码情况就是前边数字的解码所有情况后边加上当前数字转化的字母
                     第二种是与前一个数字组成两位数，先看该两位数是否可以转化为某个字母
                         若可以，则解码情况就是这两位前面的数字解码的所有情况后边加上该两位数所成的字母
                         若不可以，则不进行组合
                最后解码方式为两种选择解码情况数量相加
             */

            // 一位数字
            // 当前数字为0只能与前面数字组合为两位数
            if (Integer.parseInt(String.valueOf(s.charAt(i))) != 0) {
                dp[i] += dp[i - 1];
            }
            // 两位数字
            int num = Integer.parseInt(s.substring(i - 1, i + 1));
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[i - 2];
                }
            }

        }
        return dp[n - 1];
    }

}
