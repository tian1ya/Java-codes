package dateStructure.dsPlay.dsa.graph.bfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SingleSourcePath {
    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> order;
    private int[] pre;
    private int s;

    public SingleSourcePath(Graph g, int s) {
        G = g;
        this.visited = new boolean[G.V()];
        this.order = new ArrayList<>();
        this.pre = new int[G.V()];
        this.s = s;

        for (int i = 0; i < G.V(); i++) this.pre[i] = -1;

        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        this.pre[s] = s;

        while (!queue.isEmpty()) {
            Integer topEle = queue.remove();
            order.add(topEle);
            for (Integer adj:G.adj(topEle)) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                    pre[adj] = topEle;
                }
            }
        }
    }

    public boolean isConnetedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnetedTo(t)) return res;
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);
        Collections.reverse(res);
        return res;
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g4.txt");
        SingleSourcePath graphBFS = new SingleSourcePath(graph, 0);
//        Iterable<Integer> order = graphBFS.order();
//        System.out.println(order);
        System.out.println("0 -> 6 " + graphBFS.path(6));
    }
}
