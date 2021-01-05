package dateStructure.dsPlay.graph.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    如果 V 很大很大那么使用 邻接矩阵(稠密图)是不合适的
 */
public class AdjMatrix {
    private int V;
    private int E;
    private int[][] adj;

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public AdjMatrix(String fileName) {
        String path = "/Users/xuxliu/Ifoods/Java/leetcode/src/main/java/dateStructure/graph/files/" + fileName;
        File file = new File(path);

        try (Scanner scanner = new Scanner(file);) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new RuntimeException("V must bigger than 0");
            }
            E = scanner.nextInt();
            adj = new int[V][V];
            for (int i = 0; i < E; i++) {
                int ik = scanner.nextInt();
                int jk = scanner.nextInt();
                validateVertex(jk);
                validateVertex(ik);

                if (ik == jk) throw new RuntimeException("self loop is not allowed");
                if (adj[ik][jk] == 1) throw new RuntimeException("parallel edge is not allowed");

                adj[ik][jk] = 1;
                adj[jk][ik] = 1;
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new RuntimeException("vertex " + v + " is invalidate");
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V=%d, E=%d", V, E)).append("\n");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v][w]==1;
    }

    public List adj(int w) {
        validateVertex(w);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[w][i] == 1) {
                result.add(i);
            }
        }
        return result;
    }

    public int degree(int w) {
        validateVertex(w);
        return adj(w).size();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix.toString());

        System.out.println("adj");
        System.out.println(adjMatrix.adj(2));

        System.out.println("degree");
        System.out.println(adjMatrix.degree(2));

    }

}
