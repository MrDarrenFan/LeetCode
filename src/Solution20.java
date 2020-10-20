/*
20. 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true
 */

import java.util.Deque;
import java.util.LinkedList;

public class Solution20 {

    public boolean isValid(String s) {
        // 按照题意，空字符串满足规则
        if (s.length() == 0) {
            return true;
        }
        Deque<Character> dequeStack = new LinkedList<>();   // 用双端队列实现栈
        // 遍历每个字符
        for (char c : s.toCharArray()) {

            if (c == '(' || c == '[' || c == '{') { // 遇到左括号
                dequeStack.addFirst(c); // 入栈
            } else {    // 遇到右括号
                // 若遇到右括号，但栈中无左括号，代表右括号数量大于左括号，则判为无效
                if (dequeStack.size() == 0) {
                    return false;
                }
                // 弹栈，并配对，若配不上对则判为无效
                char temp = dequeStack.removeFirst();
                if (c == ')') {
                    if (temp != '(') {
                        return false;
                    }
                } else if (c == ']') {
                    if (temp != '[') {
                        return false;
                    }
                } else {
                    if (temp != '{') {
                        return false;
                    }
                }
            }
        }
        // 如果遍历完毕时，栈中还有括号，代表左括号数量多于右括号，则判为无效
        if (dequeStack.size() != 0) {
            return false;
        }
        // 都通过，则正常，返回有效
        return true;
    }

}
