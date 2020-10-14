/*
834. 树中距离之和
给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。

第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。

返回一个表示节点 i 与其他所有节点距离之和的列表 ans。

示例 1:
    输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
    输出: [8,12,6,10,10,10]
解释:
如下为给定的树的示意图：
  0
 / \
1   2
   /|\
  3 4 5

我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
说明: 1 <= N <= 10000
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution834 {

    int[] distanceSum;  // 记录其他结点到结点i的距离和（首次dfs计算结果为结点i的子树中的结点到i的距离和，第二次dfs修正为子树中的加上子树外的）
    int[] childTreeNodeNum; // 以结点i为根的子树中所有结点的数目
    List<List<Integer>> graph = new ArrayList<>(); // 用邻接表表示图

    // 树形dp
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        distanceSum = new int[N];   // 记录答案
        childTreeNodeNum = new int[N];  // 表示以结点i为根的子树中所有结点的数量

        // 构建邻接表
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // 以结点i为根的子树中所有结点的数量初始值为1，因为肯定有自己
        Arrays.fill(childTreeNodeNum, 1);

        // 开始计算结果
        // 树的根节点为0，而且没有父节点，故记为-1
        dfsByPostOrder(0, -1);
        dfsByPreOrder(0, -1);

        return distanceSum;
    }

    // 求结点root的子树中所有结点到root的距离和（首次dfs，自底向上计算，故使用后序遍历）
    private void dfsByPostOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);  // 该结点的邻居包括其父结点和其直接子结点
        for (Integer neighbor : neighbors) {
            if (neighbor == parent) {
                continue;   // 如果该邻居是root的parent，则跳过（因为要找的直接子结点）
            }
            dfsByPostOrder(neighbor, root); // 递归进行
            // 以root结点为根的子树中所有结点数目 = 以每个直接子结点为根的子树中所有节点数目之和
            childTreeNodeNum[root] += childTreeNodeNum[neighbor];
            // root结点的子树结点到root的距离和 = root每个直接子结点的距离和 + 以neighbor结点为根的子树中所有结点数量（因为每个直接子节点到root各有1条直接边没有计算）
            distanceSum[root] += (distanceSum[neighbor] + childTreeNodeNum[neighbor]);
        }
    }

    // 根据root的distanceSum更新其直接子结点的distanceSum值（第二次dfs，自顶向下计算，故使用前序遍历）
    private void dfsByPreOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);  // 该结点的邻居包括其父结点和其直接子结点
        for (Integer neighbor : neighbors) {
            if (neighbor == parent) {
                continue;   // 如果该邻居是root的parent，则跳过（因为要找的直接子结点）
            }
            // 每个结点到root结点的距离和 = root结点的子树结点到root的距离和 + 除了root结点的子树结点以外的结点到root的距离和
            //          = root结点的子树结点到root的距离和 - 以neighbor结点为根的子树中所有结点数量 + 以除neighbor子树中所有结点的其他结点为根的子树所有结点数量
            // 因为到root的距离变为到neighbor的距离和：（跨越了一条边）
            //      对于子树内的结点：距离要减少以neighbor结点为根的子树中所有结点数量（因为每个子结点到root都要经过这条边）
            //      对于子树外的结点：距离要增加以除neighbor子树中所有结点的其他结点为根的子树所有结点数量（因为之外的结点到neighbor也要经过这条边）
            distanceSum[neighbor] = distanceSum[root] - childTreeNodeNum[neighbor] + (graph.size() - childTreeNodeNum[neighbor]);
            dfsByPreOrder(neighbor, root);
        }
    }

}
