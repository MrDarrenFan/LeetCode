/*
面试题 16.19. 水域大小

你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
池塘的大小是指相连接的水域的个数。
编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。

示例：

输入：
[
  [0,2,1,0],
  [0,1,0,1],
  [1,1,0,1],
  [0,1,0,1]
]
输出： [1,2,4]
提示：

0 < len(land) <= 1000
0 < len(land[i]) <= 1000
 */

import java.util.ArrayList;
import java.util.Arrays;

public class SolutionMianShiJinDian16_19 {

    public static void main(String[] args) {

    }

    public int[] pondSizes(int[][] land) {
        int m = land.length;
        int n = land[0].length;
        ArrayList<Integer> result = new ArrayList<>();

        int temp;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp = findPool(land, i, j);
                if (temp != 0) {
                    result.add(temp);
                }
            }
        }

        // List转数组
        // 尝试使用流的方式
        int[] anwser = result.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.sort(anwser);
        return anwser;
    }

    public int findPool(int[][] land, int x, int y) {
        int pondSize = 0;
        if (x < 0 || y < 0 || x >= land.length || y >= land[0].length || land[x][y] != 0) {
            return pondSize;
        }

        pondSize++;
        land[x][y] = -1;    // 如果是水域就变为-1，避免重复搜索

        pondSize += findPool(land, x - 1, y - 1);
        pondSize += findPool(land, x - 1, y);
        pondSize += findPool(land, x - 1, y + 1);
        pondSize += findPool(land, x, y - 1);
        pondSize += findPool(land, x, y + 1);
        pondSize += findPool(land, x + 1, y - 1);
        pondSize += findPool(land, x + 1, y);
        pondSize += findPool(land, x + 1, y + 1);

        return pondSize;
    }

}
