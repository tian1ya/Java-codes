package dateStructure.dsPlay.dsa.graph.dfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*
    求两个顶点之间的路径：两点在一个联通分量中，那么两点间有路径
 */
public class Path {

    private boolean[] visited;
    private Graph G;
    private int s;
    private int t;
    private int[] prev;

    public Path(Graph graph, int s, int t) {
        graph.validateVertex(s);
        graph.validateVertex(t);

        this.G = graph;
        this.s = s;
        this.t = t;

        visited = new boolean[G.V()];
        prev = new int[graph.V()];

        dfs(this.s, this.s);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        prev[v] = parent; // 上一个顶点

        if (v == t) return true; // 到达 目标点 t
        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (!visited[next])
                if (dfs(next, v)) return true;
        }
        return false;
    }

    private boolean isConnectedTo() {
        return visited[t];
    }

    private Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo()) {
            /*
                s 到 t 没有联通
             */
            return res;
        }
        // s 到 t 有联通，然后再找联通
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

        Path graphDFS = new Path(graph, 0, 6);
        Path graphDFS2 = new Path(graph, 0, 5);
        System.out.println("0 -> 6" + graphDFS.path());
        System.out.println("0 -> 5" + graphDFS2.path());
    }
}
