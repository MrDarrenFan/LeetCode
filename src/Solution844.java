/*
844. 比较含退格的字符串
给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

注意：如果对空文本输入退格字符，文本继续为空。



示例 1：

输入：S = "ab#c", T = "ad#c"
输出：true
解释：S 和 T 都会变成 “ac”。
示例 2：

输入：S = "ab##", T = "c#d#"
输出：true
解释：S 和 T 都会变成 “”。
示例 3：

输入：S = "a##c", T = "#a#c"
输出：true
解释：S 和 T 都会变成 “c”。
示例 4：

输入：S = "a#c", T = "b"
输出：false
解释：S 会变成 “c”，但 T 仍然是 “b”。


提示：

1 <= S.length <= 200
1 <= T.length <= 200
S 和 T 只含有小写字母以及字符 '#'。


进阶：

你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 */

public class Solution844 {

    public static void main(String[] args) {
        Solution844 solution844 = new Solution844();
        System.out.println(solution844.backspaceCompare("abb##", "c#d#"));
    }

    // 方法二：
    // 使用双指针
    // O(N + M), O(1)   N和M分别为字符串S和T的长度
    /*
        一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。
        因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
        具体地，我们定义 needDelete 表示当前待删除的字符的数量。每次我们遍历到一个字符：
            若该字符为退格符，则我们需要多删除一个普通字符，我们让 needDelete 加 1；
            若该字符为普通字符：
                若 needDelete 为 0，则说明当前字符不需要删去；
                若 needDelete 不为 0，则说明当前字符需要删去，我们让 needDelete 减 1。
        这样，我们定义两个指针，分别指向两字符串的末尾。
        每次我们让两指针逆序地遍历两字符串，直到两字符串能够各自确定一个字符，然后将这两个字符进行比较。
        重复这一过程直到找到的两个字符不相等，或遍历完字符串为止。
     */
    public boolean backspaceCompare(String S, String T) {
        int indexS = S.length() - 1;
        int indexT = T.length() - 1;
        int needDeleteS = 0;
        int needDeleteT = 0;

        while (indexS >= 0 || indexT >= 0) {
            // 寻找字符串S下一个不应被删除的字符
            while (indexS >= 0) {
                if (S.charAt(indexS) == '#') {  // 当前字符为退格符，则需删除字符数量+1，然后移动指针
                    needDeleteS++;
                    indexS--;
                } else {    // 当前字符不为退格符
                    if (needDeleteS > 0) {   // 需删除字符数量不为0，则表示该字符会被删除，则跳过并移动指针
                        needDeleteS--;
                        indexS--;
                    } else {    // 需删除字符数量为0，则停在该字符上，等待比较
                        break;
                    }
                }
            }
            // 寻找字符串T下一个不应被删除的字符
            while (indexT >= 0) {
                if (T.charAt(indexT) == '#') {  // 当前字符为退格符，则需删除字符数量+1，然后移动指针
                    needDeleteT++;
                    indexT--;
                } else {    // 当前字符不为退格符
                    if (needDeleteT > 0) {   // 需删除字符数量不为0，则表示该字符会被删除，则跳过并移动指针
                        needDeleteT--;
                        indexT--;
                    } else {    // 需删除字符数量为0，则停在该字符上，等待比较
                        break;
                    }
                }
            }
            // 比较两字符
            if (indexS >= 0 && indexT >= 0) {
                if (S.charAt(indexS) != T.charAt(indexT)) {
                    return false;
                }
            } else {
                if (indexS >= 0 || indexT >= 0) {   // 两个if代表两字符串删除后，字符数量不一致
                    return false;
                }
            }
            // 当前字符比较完成，移动指针
            indexS--;
            indexT--;
        }
        // 通过检查
        return true;
    }

    // 方法一：
    // 用栈的思想，遇到字符入栈，遇到#出栈（注意这里没必要真的用栈，用StringBuilder足矣）
    // O(N + M), O(N + M)   N和M分别为字符串S和T的长度
//    public boolean backspaceCompare(String S, String T) {
//        return build(S).equals(build(T));
//    }
//
//    public String build(String s) {
//        StringBuilder res = new StringBuilder();
//        for (char c : s.toCharArray()) {
//            if (c != '#') {
//                res.append(c);
//            } else {
//                if (res.length() > 0) {
//                    res.deleteCharAt(res.length() - 1);
//                }
//            }
//        }
//        return res.toString();
//    }

}
