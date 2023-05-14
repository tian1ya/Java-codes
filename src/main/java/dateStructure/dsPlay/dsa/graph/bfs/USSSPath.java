package dateStructure.dsPlay.dsa.graph.bfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
    Unweighted Single Source Shortest Path
 */
public class USSSPath {
    private Graph G;
    private boolean[] visited;

    private int[] pre;
    private int[] dis;
    private int s;

    public USSSPath(Graph g, int s) {
        G = g;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        this.dis = new int[G.V()];
        this.s = s;

        for (int i = 0; i < G.V(); i++) {
            this.pre[i] = -1;
            this.dis[i] = -1;
        }

        bfs(s);

        for (int i = 0; i < this.dis.length; i++) {
            System.out.print(this.dis[i] + " ");
        }
        System.out.println("");
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        this.pre[s] = s;
        this.dis[s] = 0;

        while (!queue.isEmpty()) {
            Integer topEle = queue.remove();
            for (Integer adj:G.adj(topEle)) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                    this.dis[adj] = this.dis[topEle] + 1;
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
        // 反向求解路径
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);
        Collections.reverse(res);
        return res;
    }

    public int distance(int t) {
        G.validateVertex(t);
        return this.dis[t];
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g5.txt");
        USSSPath graphBFS = new USSSPath(graph, 0);
        System.out.println("path 0 to 6: " + graphBFS.path(6));
        System.out.println("distance 0 to 6:  " + graphBFS.distance(6));
    }
}
