/*
925. 长按键入

你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。

你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。

示例 1：
    输入：name = "alex", typed = "aaleex"
    输出：true
    解释：'alex' 中的 'a' 和 'e' 被长按。
示例 2：
    输入：name = "saeed", typed = "ssaaedd"
    输出：false
    解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
示例 3：
    输入：name = "leelee", typed = "lleeelee"
    输出：true
示例 4：
    输入：name = "laiden", typed = "laiden"
    输出：true
    解释：长按名字中的字符并不是必要的。

提示：
    name.length <= 1000
    typed.length <= 1000
    name 和 typed 的字符都是小写字母。
 */

public class Solution925 {

    public static void main(String[] args) {
        Solution925 solution925 = new Solution925();
        System.out.println(solution925.isLongPressedName("saeed", "ssaaed"));
        System.out.println(solution925.isLongPressedName("laiden", "laiden"));
        System.out.println(solution925.isLongPressedName("alex", "aalleexlx"));
    }

    public boolean isLongPressedName(String name, String typed) {
        // 双指针，分别指向两字符串中应比较的字符
        int index1 = 0;
        int index2 = 0;
        // 循环比较字符
        // 在判断为false之前要遍历完typed的每个字符
        while (index2 < typed.length()) {
            if (index1 < name.length() && name.charAt(index1) == typed.charAt(index2)) {  // 如果两字符相等，则同时前进到下一位
                index1++;
                index2++;
            } else if (index2 > 0 && typed.charAt(index2) == typed.charAt(index2 - 1)) {  // 如果两字符不相等，则看typed中该字符是否与前一个字符相等
                index2++;   // 若相等则代表该字符时长按输入的字符，则跳过该位，前进到下一位
            } else {    // 若不为长按时输入的字符，则出现其他不同字符或者typed中某字符数量少于name中的，则判断为不是
                return false;
            }
        }
        // 最后查看是否name中的每个字符都进行了比较
        if (index1 < name.length()) {
            return false;
        }
        return true;
    }

}
