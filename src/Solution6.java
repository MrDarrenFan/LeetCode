/*
6. Z 字形变换
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G
 */

import java.util.ArrayList;
import java.util.List;

public class Solution6 {

    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        // 存放转换后每行的结果
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        // 当前行号
        int curRow = 0;
        // 当前是否在往下面一行走，初始为从上到下，所以下方开始遍历时要转化为true
        boolean isGoingDown = false;

        // 遍历字符串中字符，放到相应的行
        for (char c : s.toCharArray()) {
            // 添加到该字符所属行
            rows.get(curRow).append(c);
            // 到最上面一行或最下面一行时，更改方向
            if (curRow == 0 || curRow == numRows - 1) {
                isGoingDown = !isGoingDown;
            }
            // 根据是否再往下面一行走，计算下一行的行号
            curRow += isGoingDown ? 1 : -1;
        }

        // 转化并输出结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

}
