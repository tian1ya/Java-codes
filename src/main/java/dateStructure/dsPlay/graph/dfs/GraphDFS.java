package dateStructure.dsPlay.graph.dfs;

import dateStructure.dsPlay.graph.common.Graph;

import java.util.*;

/*

 */
public class GraphDFS {

    private ArrayList<Integer> preOrder = new ArrayList<>();
    private ArrayList<Integer> postOrder = new ArrayList<>();
    private boolean[] visited;
    private Graph G;
    public GraphDFS(Graph graph) {
        G = graph;
        visited = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            // 处理存在联通分量的情况
            if (!visited[i])
                dfs(i);
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        preOrder.add(v);
        /*
            这里只能做到联通的图
            这里也是图的先序遍历： [0, 1, 3, 2, 6, 4, 5]
            后续遍历只需要将 order.add(v); 放到函数末尾
            这里没有中序，因为这里不像二插树那也有左右节点
            而多叉树也是没有中序遍历的
         */
        Iterable<Integer> adjs = G.adj(v);
        for (Integer next : adjs) {
            if (!visited[next])
                dfs(next);
        }
        postOrder.add(v);
    }

    private void loopDFS(int s) {
        Stack<Integer> stack = new Stack<>();
        stack.add(s);
        visited[s] = true;

        while (!stack.isEmpty()) {
            Integer topEle = stack.pop();
            for (Integer adj:G.adj(topEle)) {
                if (!visited[adj]) {
                    stack.add(adj);
                    visited[adj] = true;
                }
            }
        }
    }

    public Iterable<Integer> preOrder() {
        return preOrder;
    }

    public Iterable<Integer> postOrder() {
        return postOrder;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g3.txt");

        GraphDFS graphDFS = new GraphDFS(graph);

        Iterable<Integer> orderDFS = graphDFS.preOrder();
        System.out.println("preOrder"); // [0, 1, 3, 2, 6, 4, 5]
        System.out.println(orderDFS);

        Iterable<Integer> integers = graphDFS.postOrder();
        System.out.println("postOrder"); // [6, 2, 3, 4, 1, 0, 5]
        System.out.println(integers);

    }

}
