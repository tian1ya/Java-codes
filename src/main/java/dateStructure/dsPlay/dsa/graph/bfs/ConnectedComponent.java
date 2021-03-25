package dateStructure.dsPlay.dsa.graph.bfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectedComponent {
    private Graph G;
    private int[] visited;
    private int cccount;

    public ConnectedComponent(Graph g) {
        G = g;
        this.visited = new int[G.V()];
        for (int i = 0; i < G.V(); i++) this.visited[i] = -1;

        for (int i = 0; i < G.V(); i++) {
            if (visited[i] == -1) {
                bfs(i, cccount);
                cccount++;
            }
        }
    }

    private void bfs(int s, int cccount) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = cccount;

        while (!queue.isEmpty()) {
            Integer topEle = queue.remove();
            for (Integer adj : G.adj(topEle)) {
                if (visited[adj] == -1) {
                    queue.add(adj);
                    visited[adj] = cccount;
                }
            }
        }
    }


    public Iterable[] component() {
        ArrayList[] res = new ArrayList[this.cccount];
        for (int i = 0; i < this.cccount; i++)
            res[i] = new ArrayList();

        for (int j = 0; j < G.V(); j++)
            // visited 值一样的是属于同一个联通分量的
            res[this.visited[j]].add(j);

        return res;
    }

    public boolean hasComponent() {
        return this.cccount > 0;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g4.txt");
        ConnectedComponent graphBFS = new ConnectedComponent(graph);

        Iterable[] component = graphBFS.component();
        for (int i = 0; i < component.length; i++) {
            System.out.print(i + ": ");
            Iterable eles = component[i];
            for (Object ele : eles) {
                System.out.print(ele + " ");
            }
            System.out.println("");
        }
    }
}
