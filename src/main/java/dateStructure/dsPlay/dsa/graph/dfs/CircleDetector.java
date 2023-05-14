package dateStructure.dsPlay.dsa.graph.dfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;
import java.util.Stack;

/*

 */
public class CircleDetector {

    private boolean[] visited;
    private Graph G;
    private boolean hasCircle;

    public CircleDetector(Graph graph) {
        G = graph;
        visited = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i])
                dfs(i,i);
        }
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (!visited[next])
                dfs(next, v);
            else if (next != parent)
                // 之前已经访问过了，且访问过的节点不是当前节点的父节点
                hasCircle = true;
        }
    }

    public boolean isHasCircle() {
         return hasCircle;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g3.txt");
        CircleDetector graphDFS = new CircleDetector(graph);
        System.out.println(graphDFS.isHasCircle());

        Graph graph22 = new Graph("g32.txt");
        CircleDetector graph2 = new CircleDetector(graph22);
        System.out.println(graph2.isHasCircle());

    }
}
