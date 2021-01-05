package dateStructure.dsPlay.graph.bfs;

import dateStructure.dsPlay.graph.common.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
    private Graph G;
    private boolean[] visited;
    private int s;

    private ArrayList<Integer> order;

    public GraphBFS(Graph g) {
        G = g;
        this.visited = new boolean[G.V()];
        this.order = new ArrayList<>();

        for (int i = 0; i < G.V(); i++) {
            if (!visited[i])
                bfs(i);
        }
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            Integer topEle = queue.remove();
            order.add(topEle);
            for (Integer adj:G.adj(topEle)) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g4.txt");
        GraphBFS graphBFS = new GraphBFS(graph);
        Iterable<Integer> order = graphBFS.order();
        System.out.println(order);
    }
}
