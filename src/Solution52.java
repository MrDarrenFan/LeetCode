/*
52. N皇后 II

n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回 n 皇后不同的解决方案的数量。

示例:
输入: 4
输出: 2
解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]


提示：

皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 */

import java.util.HashSet;
import java.util.Set;

public class Solution52 {

    public int totalNQueens(int n) {
        // 表示当前列有无皇后（用列号来标识）
        Set<Integer> columns = new HashSet<>();
        // 表示左上到右下的斜线上有无皇后（用元素行下标减列下标来标识）
        Set<Integer> diagonals1 = new HashSet<>();
        // 表示左下到右上的斜线上有无皇后（用元素行下标加列下标来标识）
        Set<Integer> diagonals2 = new HashSet<>();
        // 递归回溯计算结果
        return backTrack(n, 0, columns, diagonals1, diagonals2);
    }

    // curRow：当前行号
    public int backTrack(int n, int curRow, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (curRow == n) {  // 代表此时n个皇后都放完毕，所以返回一种解法
            return 1;
        } else {    // 皇后还未放完
            int count = 0;
            // 遍历该行上的每一个位置
            for (int i = 0; i < n; i++) {
                // 当前列上是否有皇后
                if (columns.contains(i)) {
                    continue;
                }
                // 当前所在左上到右下的斜线上是否有皇后
                int diagonal1 = curRow - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                // 当前所在左下到右上的斜线上是否有皇后
                int diagonal2 = curRow + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                // 通过上述三个条件后代表该位置可以放，则放入并标记，然后递归到下一行求解，之后记得回溯
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backTrack(n, curRow + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

}
