package dateStructure.dsPlay.dsa.graph.dfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*

 */
public class SingleSourcePath {

    private boolean[] visited;
    private Graph G;
    private int s;
    private int[] prev;

    public SingleSourcePath(Graph graph, int s) {
        this.G = graph;
        graph.validateVertex(s);
        this.s = s;

        visited = new boolean[G.V()];
        prev = new int[graph.V()];

        dfs(this.s, this.s);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        prev[v] = parent; // 上一个顶点
        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (!visited[next])
                dfs(next, v);
        }
    }

    private boolean isConnectedTo(int t) {
        G.validateVertex(t);
        /*
            有被访问过，说明是在联通的链里面
            这里仅仅是判断是否和 s 属于一个联通
         */
        return visited[t];
    }

    private Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) {
            /*
                s 到 t 没有联通
             */
            return res;
        }

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = prev[cur];
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g3.txt");

        SingleSourcePath graphDFS = new SingleSourcePath(graph, 0);
        System.out.println("0 -> 6" + graphDFS.path(6));
        System.out.println("0 -> 5" + graphDFS.path(5));
    }
}
