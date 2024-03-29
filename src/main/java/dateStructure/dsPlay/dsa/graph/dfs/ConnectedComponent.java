package dateStructure.dsPlay.dsa.graph.dfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

import java.util.ArrayList;

/*
    联通分量检测
 */
public class ConnectedComponent {

    private int cccount;
    private int[] visited;
    private Graph G;

    public ConnectedComponent(Graph graph) {
        G = graph;
        visited = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = -1;
        }
        for (int i = 0; i < G.V(); i++) {
            if (visited[i] == -1) {
                // 如果只有一个联通分量，那么经过一个点，那么就可以将所有的点访问到
                dfs(i, cccount);
                 cccount++;
            }
        }
    }

    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (visited[next] == -1)
                dfs(next, ccid);
        }
    }

    public int cccount() {
        for (int i = 0; i < visited.length; i++) {
            System.out.print(visited[i] + " ");
        }
        System.out.println("");
        return this.cccount;
    }

    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[w] == visited[v];
    }

    public ArrayList<Integer>[] component() {
        ArrayList[] res = new ArrayList[this.cccount];
        for (int i = 0; i < this.cccount; i++) {
            res[i] = new ArrayList();
        }

        for (int i = 0; i < G.V(); i++) {
            res[visited[i]].add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g3.txt");

        ConnectedComponent graphDFS = new ConnectedComponent(graph);
        System.out.println(graphDFS.cccount());

        System.out.println(graphDFS.isConnected(0,6));
        System.out.println(graphDFS.isConnected(0,5));

        System.out.println("=============");
        ArrayList<Integer>[] component = graphDFS.component();
        for (int i = 0; i < component.length; i++) {
            System.out.print(i + " : ");
            for (Integer ele : component[i]) {
                System.out.print(ele + " ");
            }
            System.out.println("");
        }
    }

}
