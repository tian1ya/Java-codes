package dateStructure.dsPlay.dsa.algrithem.AboutGraph;

import java.util.LinkedList;

/*
    给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
    二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：

        路径途经的所有单元格都的值都是 0 。
        路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
        畅通路径的长度 是该路径途经的单元格总数。

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
        return nextx >= 0 && nextx < R && nexty >= 0 && nexty < C;
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
