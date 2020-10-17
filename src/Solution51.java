/*
51. N 皇后
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。



示例：

输入：4
输出：[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。


提示：

皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution51 {

    public static void main(String[] args) {
        Solution51 solution51 = new Solution51();
        System.out.println(solution51.solveNQueens(4));
    }

    // 存放结果
    List<List<String>> resultList = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 表示当前列有无皇后（用列号来标识）
        Set<Integer> columns = new HashSet<>();
        // 表示左上到右下的斜线上有无皇后（用元素行下标减列下标来标识）
        Set<Integer> diagonals1 = new HashSet<>();
        // 表示左下到右上的斜线上有无皇后（用元素行下标加列下标来标识）
        Set<Integer> diagonals2 = new HashSet<>();
        // 递归回溯计算结果
        backTrack(n, 0, new ArrayList<>(), columns, diagonals1, diagonals2);
        return resultList;
    }

    // curRow：当前行号；     curSolution：当前解法
    public void backTrack(int n, int curRow, List<String> curSolution, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (curRow == n) {  // 代表此时n个皇后都放完毕，所以存放一种解法
            resultList.add(new ArrayList<>(curSolution));
        } else {    // 皇后还未放完
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
                StringBuilder sb = new StringBuilder(); // 生成表示当前行的字符串
                for (int j = 0; j < n; j++) {
                    if (j == i) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                curSolution.add(sb.toString());
                backTrack(n, curRow + 1, curSolution, columns, diagonals1, diagonals2);
                curSolution.remove(curSolution.size() - 1);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

}
