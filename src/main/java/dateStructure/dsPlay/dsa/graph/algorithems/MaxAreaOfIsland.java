package dateStructure.dsPlay.dsa.graph.algorithems;

import java.util.HashSet;

/*
    695. 岛屿的最大面积
      给定一个包含了一些 0 和 1 的非空二维数组 grid 。

    一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

    找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)

    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。

    [[0,0,0,0,0,0,0,0]]
    对于上面这个给定的矩阵, 返回 0。
    注意: 给定的矩阵grid 的长度和宽度都不超过 50。

    四联通：
        如：dirs = [[-1,0], [0,1],
                    [1,0], [0,-1]]
     是一个 4 x 2 的矩阵，其中4表示4个方向，2表示y轴和x轴上的数值，假如上面的方向坐标矩阵dirs 是点 (2,1)的4个方向上代表有节点存在，
     那么这4个点分别是 [1,1],[2,2],[3,1],[2,0]
 */
public class MaxAreaOfIsland {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int R, C; // 行、列
    private int[][] grid;

    private HashSet<Integer>[] G;// 图邻接表

    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null) return 0;

        R = grid.length;
        if (R == 0) return 0;

        C = grid[0].length;
        if (C == 0) return 0;

        this.grid = grid;

        G = constructGraph();

        int res = 0;

        visited = new boolean[G.length];
        for (int v = 0; v < G.length; v++) {
            if (!visited[v] && grid[v / C][v % C] == 1) {
                res = Math.max(dfs(v), res);
            }
        }
        return res;
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (Integer adj : G[v]) {
            if (!visited[adj]) {
                res += dfs(adj); // 这里实现结果的累计
            }
        }
        return res;
    }

    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] g = new HashSet[R * C];
        for (int i = 0; i < g.length; i++) {
            g[i] = new HashSet<>();
        }

        for (int j = 0; j < g.length; j++) {
            int x = j / C;
            int y = j % C;

            if (grid[x][y] == 1) {
                for (int d = 0; d < 4; d++) {
                    int nextx = x + dirs[d][0];
                    int nexty = y + dirs[d][1];

                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        int next = nextx * C + nexty;
                        g[j].add(next); // 这里都是 HashSet 所以不会存在重复添加的情况
                        g[next].add(j);
                    }
                }
            }
        }
        return g;
    }

    private boolean inArea(int nextx, int nexty) {
        return nextx >= 0 && nextx < R && nexty >= 0 && nexty < C;
    }

    public static void main(String[] args) {
        int[][] island = new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                                    {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                                    {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        int res = new MaxAreaOfIsland().maxAreaOfIsland(island);
        System.out.println(res);
    }
}
