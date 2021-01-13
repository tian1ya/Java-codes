package dateStructure.dsPlay.leetcode;

import java.util.*;

/*
    现在你总共有 n 门课需要选，记为 0 到 n-1。

    在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

    给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

    可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

    输入: 4, [[1,0],[2,0],[3,1],[3,2]]
    输出: [0,1,2,3] or [0,2,1,3]
    解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
         因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。

    说明:
    输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
    你可以假定输入的先决条件中没有重复的边。
    提示:

    这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
    通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
    拓扑排序也可以通过 BFS 完成。

 */
public class classAssignII {

    private boolean isCircleExist(int numCourses, List<List<Integer>> adjacent) {
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfs(visited, adjacent, i)) return true;
        }
        return false;
    }

    private boolean dfs(boolean[] visited, List<List<Integer>> adjacent, int clsNum) {
        if (visited[clsNum])
            return true;

        visited[clsNum] = true;
        for (int dep : adjacent.get(clsNum)) {
            if (dfs(visited, adjacent, dep))
                return true;
        }
        visited[clsNum] = false; // 上面的循环结束之后，如果邻居表中没有环，将位置在置回false，因为其他的课程还会对这个进行依赖，也就是在其他的邻接表
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {


        int[] degree = new int[numCourses];
        int[] classRes = new int[numCourses];

        List<List<Integer>> adjacent = new ArrayList<>();

        // 初始化 adjacent
        for (int i = 0; i < numCourses; i++) {
            adjacent.add(new ArrayList<>());
        }

        // 得到入度以及 adjacent
        for (int[] deps : prerequisites) {
            int curClassNum = deps[0];
            int curClassDep = deps[1];
            degree[curClassNum]++;
            adjacent.get(curClassDep).add(curClassNum);
        }

        // 检查是否存在环，存在则表示不能完成
        if (isCircleExist(numCourses, adjacent))
            return new int[]{};

        Queue<Integer> queue = new LinkedList<>();

        // 得到入度为 0 的课程编号
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }


        // 从入度为 0 的课程开始作为起始课程
        int k = 0;
        while (!queue.isEmpty()) {
            Integer parentClsNum = queue.poll();
            classRes[k++] = parentClsNum;
            numCourses--;

            List<Integer> deps = adjacent.get(parentClsNum);
            for (Integer dep : deps) {
                int clsNumDep = --degree[dep];
                if (clsNumDep == 0) queue.add(dep);
            }
        }
        return classRes;
    }


    public static void main(String[] args) {
//        int[] res = new classAssignII().findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
//        int[] res = new classAssignII().findOrder(4, new int[][]{{0, 1}, {3, 1}, {1, 3}, {3, 2}});
//        int[] res = new classAssignII().findOrder(2, new int[][]{{1, 0}});
//        int[] res = new classAssignII().findOrder(2, new int[][]{{1, 0},{0,1}});
        int[] res = new classAssignII().findOrder(3, new int[][]{{1, 0}, {1, 2}, {0, 1}});
        System.out.println(Arrays.toString(res));
    }
}
