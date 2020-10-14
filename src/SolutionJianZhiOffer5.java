/*

剑指 Offer 05. 替换空格
请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

示例 1：

输入：s = "We are happy."
输出："We%20are%20happy."


限制：

0 <= s 的长度 <= 10000

 */

/*
思路：
一、利用Java中可变字符串StringBuilder
二、利用数组（String类为动态char数组），先计算所需全部替换后所需数组大小，然后使用双指针从后向前移动并替换
    （从后先前的原因是可以减少移动的次数，从而提高效率）
 */

public class SolutionJianZhiOffer5 {

    public static void main(String[] args) {

    }

    // 思路一
    public String replaceSpace(String s) {
        // 利用可变字符串StringBuilder类
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == ' ') {
                result.append("%20");
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    // 思路二
//    public String replaceSpace2(String s) {
//
//    }

}
