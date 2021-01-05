package dateStructure.dsPlay.graph.dfs;

import dateStructure.dsPlay.graph.common.Graph;

import java.util.ArrayList;
import java.util.Collections;

/*

 */
public class SingleSourcePathV2 {

    private Graph G;
    private int s;
    private int[] prev;

    public SingleSourcePathV2(Graph graph, int s) {
        this.G = graph;
        graph.validateVertex(s);
        this.s = s;

        prev = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            prev[i] = -1;
        }

        dfs(this.s, this.s);
    }

    private void dfs(int v, int parent) {
        prev[v] = parent; // 上一个顶点
        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (prev[next] == -1)
                dfs(next, v);
        }
    }

    private boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return prev[t] != -1;
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

        SingleSourcePathV2 graphDFS = new SingleSourcePathV2(graph, 0);
        System.out.println("0 -> 6" + graphDFS.path(6));
        System.out.println("0 -> 5" + graphDFS.path(5));
    }
}
