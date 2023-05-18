package dateStructure.dsPlay.dsa.algrithem.AboutGraph;

import cats.kernel.Hash;

import java.util.HashSet;

/*
    这类问题的总结
    https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/

    floodfill(洪水填充) 算法：有一个二维空间中，并
       将二维网格看成是一个图，然后使用dfs 遍历，从一点出发，对二维空间中的元素进行染色
    这里岛的问题，就是使用该算法
    涉及到的题目还有：200、1020、130、733、1034、827、1091

 */
public class IslandsAlgori {


    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '1', '0'}
        };

        char[][] grid2 = new char[][]{
                {'1', '1'},
        };

//        System.out.println(numIslands(grid));

        // 也可以将二维数组转为以为数组
        // index = 27 的元素，分到 13 * 13 的二维，转为一维度
        // x = 27 / 13 = 2
        // y = 27 % 13 = 1
        // 也就是一维度 index=27的元素，在13*13 的二维度的坐标为 第二行，第1列
        int[][] a = new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        // 这个二维举证可以认为是，邻接矩阵保存一个图结构
        // 每一个格子都是一个边，一个边链接这两个维度的顶点
//        System.out.println(maxAreaOfIsland(a));
        System.out.println(maxAreaOfIslandV2(a));

        int[][] grid22 = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int[][] grid3 = new int[][]{{1, 1}};

        // 周长
//        System.out.println(islandPerimeter(grid3));
    }

    public static int islandPerimeter(int[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    res = Perimeter(grid, i, j);
            }
        }
        return res;
    }

    private static int Perimeter(int[][] grid, int row, int col) {
        if (!InArea(grid, row, col))
            return 1;

        if (grid[row][col] == 0)// 遇到0，表示到达边界地方, 那么返回1，
            return 1;

        if (grid[row][col] != 1)
            return 0;

        grid[row][col] = 2; // 表示原来是岛屿(值为1)的地方已经被访问，后续不再进入访问

        return Perimeter(grid, row - 1, col)
                + Perimeter(grid, row + 1, col)
                + Perimeter(grid, row, col - 1)
                + Perimeter(grid, row, col + 1);
    }

    /**
     * 坐标 {x,y}
     * 四联通：dirs = [[-1,0],[0,1],[1,0],[0,-1]]
     * 当前点 a = [n,m]
     * a + dirs[0] a 往左移动一格
     * a + dirs[1] a 往右移动一格
     * a + dirs[2] a 往下移动一格
     * a + dirs[3] a 往上移动一格
     * <p>
     * for(index i = 0; i<4;i++) {
     * int nextx = x + dirs[i][0]
     * int nexty = y + dirs[i][1]
     * }
     */

    public static int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    res = Math.max(res, area(grid, i, j));
            }
        }
        return res;
    }

    public static int maxAreaOfIslandV2(int[][] grid) {

        if (grid == null || grid.length == 0) return 0;
        Integer C = grid[0].length;

        HashSet<Integer>[] G = constructGraph(grid);
        boolean[] visited = new boolean[G.length];

        int res = 0;
        for (int i = 0; i < G.length; i++) {
            int gridX = i / C;
            int gridY = i % C;
            if (!visited[i] && grid[gridX][gridY] == 1) { // 找到新的联通分量
                res = Math.max(dfs(G, i, visited), res);
            }
        }
        return res;
    }

    private static Integer dfs(HashSet<Integer>[] G, int index, boolean[] visited) {
        visited[index] = true;
        int res = 1;
        HashSet<Integer> adjs = G[index];
        for (Integer adj : adjs) {
            if (!visited[adj]) {
                res += dfs(G, adj, visited);
            }
        }
        return res;
    }

    public static HashSet<Integer>[] constructGraph(int[][] grid) {
        int rowNum = grid.length;
        int colNum = grid[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        HashSet<Integer>[] g = new HashSet[rowNum * colNum];

        // 初始化 graph
        for (int i = 0; i < g.length; i++) {
            g[i] = new HashSet<>();
        }

        // 生成 graph
        for (int i = 0; i < g.length; i++) {
            int x = i / colNum;
            int y = i % colNum;
            if (grid[x][y] == 1) {
                for (int j = 0; j < 4; j++) {
                    int nextX = x + dirs[j][0];
                    int nextY = y + dirs[j][1];
                    if (InArea(grid, nextX, nextY) && grid[nextX][nextY] == 1) {
                        int nextEle = nextX * colNum + nextY;
                        g[i].add(nextEle);
                        g[nextEle].add(i);
                    }
                }
            }
        }
        return g;
    }

    private static int area(int[][] grid, int row, int col) {
        if (!InArea(grid, row, col))
            return 0;

        if (grid[row][col] != 1)
            return 0;

        grid[row][col] = 2;

        return 1 + area(grid, row + 1, col)
                + area(grid, row - 1, col)
                + area(grid, row, col - 1)
                + area(grid, row, col + 1);
    }

    private static boolean InArea(int[][] grid, int row, int col) {
        return (row < grid.length && row >= 0) && (col >= 0 && col < grid[0].length);
    }

    public static int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    // 表示是岛屿，且没有被遍历过
                    traverseIsland(grid, i, j);
                    // 这里直接加1，因为经过一次遍历，会将已经遍历过的属于同一个岛的全部置为 2
                    res += 1;
                }
            }
        }
        return res;
    }

    private static void traverseIsland(char[][] grid, int row, int col) {
        if (!InArea(grid, row, col)) {
            return;
        }

        if (grid[row][col] != '1')
            return;

        // 表示已经遍历过了
        grid[row][col] = '2';

        traverseIsland(grid, row + 1, col);
        traverseIsland(grid, row - 1, col);
        traverseIsland(grid, row, col + 1);
        traverseIsland(grid, row, col - 1);
    }

    private static boolean InArea(char[][] grid, int row, int col) {
        return (row < grid.length && row >= 0) && (col >= 0 && col < grid[0].length);
    }
}
