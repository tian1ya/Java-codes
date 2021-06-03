package dateStructure.dsPlay.dsa.algrithem.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

    在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

    给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

    输入: 2, [[1,0],[0,1]]
    输出: false
    解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。


 */
public class classAssign {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
            numCourses: 这么多个定点
         */

        ArrayList<List<Integer>> adjacent = new ArrayList<>();
        int[] degree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacent.add(new ArrayList<>());
        }


        for (int[] deps : prerequisites) {
            degree[deps[0]]++;
            adjacent.get(deps[1]).add(deps[0]); // 注意邻接表中的元素，为其依赖课程number
        }

        // 找到入度为0 的 课程
        for (int classNum = 0; classNum < numCourses; classNum++) {
            if (degree[classNum] == 0) {
                queue.add(classNum);
            }
        }

        //DFS
        while (!queue.isEmpty()) {
            Integer classNum = queue.poll();
            numCourses--;

            List<Integer> depClass = adjacent.get(classNum);

            for (Integer clsNum : depClass) {
                if (--degree[clsNum] == 0) {
                    queue.add(clsNum);
                }
            }
        }
        return numCourses == 0;
    }

    // DFS: 原理是通过 DFS 判断图中是否有环。
    public boolean canFinishV2(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);

        for (int i = 0; i < numCourses; i++)
            if (!dfs(adjacency, flags, i)) return false;
        return true;
    }


    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if (flags[i] == 1) return false; // 以及被访问过了
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (Integer j : adjacency.get(i))
            if (!dfs(adjacency, flags, j)) return false;
        flags[i] = -1;
        return true;
    }


    public static void main(String[] args) {
        boolean b = new classAssign().canFinish(2, new int[][]{{1, 0}});
        System.out.println(b);
    }
}
