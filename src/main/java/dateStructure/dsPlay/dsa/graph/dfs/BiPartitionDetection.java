package dateStructure.dsPlay.dsa.graph.dfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;
import java.util.Stack;

/*
    二分图检测
 */
public class BiPartitionDetection {

    private boolean[] visited;
    private int[] colors;
    private Graph G;
    private boolean isBipartition = true;

    public BiPartitionDetection(Graph graph) {
        G = graph;
        visited = new boolean[G.V()];
        this.colors = new int[G.V()];
        // -1 表示没有被染色
        for (int i = 0; i < G.V(); i++) colors[i] = -1;

        for (int i = 0; i < G.V(); i++) {
            if (!visited[i])
                if (!dfs(i, 0)) {
                    isBipartition = false;
                    break;
                }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;

        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (!visited[next]) {
                // 只考虑 1 和 0 两种染色
                if (!dfs(next, 1 - color)) return false;
            } else if (colors[v] == colors[next]) {
                // 已经被访问过了，且颜色和当前一样，那么同一个边上包含2个颜色一样的顶点
                return false;
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }

    public static void main(String[] args) {
//        Graph graph = new Graph("g6.txt");
        Graph graph = new Graph("g62.txt");

        BiPartitionDetection graphDFS = new BiPartitionDetection(graph);
        System.out.println(graphDFS.isBipartition);

    }

}
