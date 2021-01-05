package dateStructure.dsPlay.graph.algorithems;

/*
    题目描述:
       给定一个无向图graph，当这个图为二分图时返回true。

        如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，
        一个来自B集合，我们就将这个图称为二分图。
        graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。
        这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。

    实例：
       输入: [[1,3], [0,2], [1,3], [0,2]]
        输出: true
        解释:
        无向图如下:
        0----1
        |    |
        |    |
        3----2
        我们可以将节点分成两组: {0, 2} 和 {1, 3}。


     思路：一个节点染成红色,我们把它相连的节点染成蓝色,如果当两种颜色相同的也连接了,说明不是二分图.
 */
public class Bipartite {

    private boolean[] vidited;
    private int[] colors;
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        this.graph = graph;
        this.vidited = new boolean[V];
        this.colors = new int[V];

        for (int v = 0; v < V; v++) {
            if (!vidited[v])
                if (!dfs(v, 0)) return false;
        }
        return true;
    }

    public boolean dfs(int v, int color) {
        /*
            对节点 v 进行染色 color
         */

        vidited[v] = true;
        colors[v] = color;
        for (int w : graph[v]) {
            if (!vidited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[v] == colors[w]) {
                // 同一个边上的两个节点染了相同的颜色，这是矛盾点
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        Bipartite bipartite = new Bipartite();
        int[][] ints = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        boolean b = bipartite.isBipartite(ints);
        System.out.println(b == true);
    }
}
