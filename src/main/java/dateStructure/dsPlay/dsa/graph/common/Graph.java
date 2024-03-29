package dateStructure.dsPlay.dsa.graph.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/*
    如果 V 很大很大那么使用 邻接矩阵(稠密图)是不合适的
 */
public class Graph {
    private int V;
    private int E;
    private TreeSet[] adj;

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Graph(String fileName) {
        String path = "E:\\back-end\\Java-codes\\src\\main\\java\\dateStructure\\dsPlay\\dsa\\graph\\files\\" + fileName;
        File file = new File(path);

        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();

            if (V < 0) throw new RuntimeException("V must bigger than 0");
            adj = new TreeSet[V]; // 有多少个定点

            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet(); // 每个定点的边
            }

            E = scanner.nextInt();

            if (E < 0) throw new RuntimeException("edge must bigger than 0");

            for (int i = 0; i < E; i++) {
                int ik = scanner.nextInt();
                int jk = scanner.nextInt();
                validateVertex(jk);
                validateVertex(ik);

                if (ik == jk) throw new RuntimeException("self loop is not allowed");
                if (adj[ik].contains(jk)) throw new RuntimeException("parallel edge is not allowed");

                adj[ik].add(jk);
                adj[jk].add(ik);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new RuntimeException("vertex " + v + " is invalidate");
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V=%d, E=%d", V, E)).append("\n");
        for (int i = 0; i < V; i++) {
            TreeSet list = adj[i];
            sb.append(String.format("%d",i) + ": ");
            list.forEach(ele -> sb.append(String.format("%d ", ele)));
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int w) {
        validateVertex(w);
        return adj[w];
    }

    public int degree(int w) {
        validateVertex(w);
        return adj[w].size();
    }

    public static void main(String[] args) {
        Graph adjMatrix = new Graph("g.txt");
        System.out.println(adjMatrix.toString());

        System.out.println("adj");
        System.out.println(adjMatrix.adj(2));

        System.out.println("degree");
        System.out.println(adjMatrix.degree(2));

    }

}
