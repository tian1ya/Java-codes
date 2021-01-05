package dateStructure.dsPlay.graph.algorithems;

/*
    这就是floodfill 算法

    相关的问题还有： leecode 200/1020/130/733/1034 等个问题

    尝试使用 BFS 解决
 */
public class MaxAreaOfIslandV2 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int R, C; // 行、列
    private int[][] grid;

    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null) return 0;

        R = grid.length;
        if (R == 0) return 0;

        C = grid[0].length;
        if (C == 0) return 0;

        this.grid = grid;


        int res = 0;

        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && grid[i][j] == 1)
                    res = Math.max(dfs(i, j), res);
            }
        }
        return res;
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int res = 1;
        for (int k = 0; k < 4; k++) {
            int nextx = x + dirs[k][0];
            int nexty = x + dirs[k][1];
            if (!visited[nextx][nexty] && inArea(nextx, nexty) && grid[nextx][nexty]==1) {
                res += dfs(nextx, nexty);
            }
        }
        return res;
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
        int res = new MaxAreaOfIslandV2().maxAreaOfIsland(island);
        System.out.println(res);
    }
}
