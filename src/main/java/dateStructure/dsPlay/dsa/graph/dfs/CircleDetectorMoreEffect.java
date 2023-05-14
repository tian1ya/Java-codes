package dateStructure.dsPlay.dsa.graph.dfs;

import dateStructure.dsPlay.dsa.graph.common.Graph;

/*
    可以提前终止
 */
public class CircleDetectorMoreEffect {

    private boolean[] visited;
    private Graph G;
    private boolean hasCircle = false;

    public CircleDetectorMoreEffect(Graph graph) {
        G = graph;
        visited = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i])
                if (dfs(i,i)) {
                    hasCircle = true;
                    break;
                }
        }
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (!visited[next]){
                if (dfs(next, v)) return true;
            }
            else if (next != parent)
                return true;
        }
        return false;
    }

    public boolean isHasCircle() {
         return hasCircle;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g3.txt");
        CircleDetectorMoreEffect graphDFS = new CircleDetectorMoreEffect(graph);
        System.out.println(graphDFS.isHasCircle());

        Graph graph22 = new Graph("g32.txt");
        CircleDetectorMoreEffect graph2 = new CircleDetectorMoreEffect(graph22);
        System.out.println(graph2.isHasCircle());

    }
}
