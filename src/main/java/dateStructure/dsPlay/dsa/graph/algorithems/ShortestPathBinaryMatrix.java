package dateStructure.dsPlay.dsa.graph.algorithems;

import java.util.LinkedList;

/*
    在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。

    一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：

    相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
        C_1 位于 (0, 0)（即，值为 grid[0][0]）
        C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
    如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）


note: 无权的最短路径 BFS
      8 联通，不单独建图，直接在举证中计算
 */
public class ShortestPathBinaryMatrix {

    public int[][] dirs = {{-1, -1}, {-1, 1}, {-1, 0}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};


    public int R;
    public int C;


    public boolean[][] visited;

    public int shortestPathBinaryMatrix(int[][] grid) {
        R = grid.length;
        C = grid[0].length;

        visited = new boolean[R][C];

        int[][] dis = new int[R][C];

        if (grid[0][0] == 1) return -1;
        if (R == 1 && C == 1) return 1;

        //BFS
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0][0] = true;
        dis[0][0] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int curx = cur / C;
            int cury = cur % C;

            for (int i = 0; i < 8; i++) {
                int nextx = curx + dirs[i][0];
                int nexty = cury + dirs[i][1];

                if (inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == 0) {
                    queue.add(nextx * C + nexty);
                    visited[nextx][nexty] = true;
                    dis[nextx][nexty] = dis[curx][cury] + 1;

                    if (nextx == R - 1 && nexty == C - 1) {
                        return dis[nextx][nexty];
                    }
                }
            }
        }
        return -1;
    }

    private boolean inArea(int nextx, int nexty) {
        return nextx >=0 && nextx < R && nexty >= 0 && nexty < C;
    }

    public static void main(String[] args) {
        int[][] grods = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        int i = new ShortestPathBinaryMatrix().shortestPathBinaryMatrix(grods);
        System.out.println(i);
    }
}
