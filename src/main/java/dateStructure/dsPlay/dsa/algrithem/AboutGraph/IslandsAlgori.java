package dateStructure.dsPlay.dsa.algrithem.AboutGraph;

/*
    这类问题的总结
    https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
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

        System.out.println(numIslands(grid));

        int[][] a = new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        System.out.println(maxAreaOfIsland(a));
        int[][] grid22 = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int[][] grid3 = new int[][]{{1, 1}};

        // 周长
        System.out.println(islandPerimeter(grid3));
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

        grid[row][col] = 2;

        return Perimeter(grid, row - 1, col)
                + Perimeter(grid, row + 1, col)
                + Perimeter(grid, row, col - 1)
                + Perimeter(grid, row, col + 1);
    }


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
