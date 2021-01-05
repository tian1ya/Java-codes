package dateStructure.chapt10;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.Iterator;

public interface Graph {
    // 定点和边的数目
    int vNumber();
    int eNumber();

    //图中所有定点、定点位置的迭代器
    Iterator vertices();
    Iterator vPositions();

    //返回图中所有边、边位置的迭代器
    Iterator edges();
    Iterator ePositions();

    //检测是否有某条边从顶点u到v
    boolean areAdjacent(Vertex u, Vertex v);

    //取从顶点u指向v的边，若不存在，则返回null
    Edge edgeFromTo(Vertex u, Vertex v);

    //将顶点v从顶点集中删除，并返回之
    Vertex remove(Vertex v);

    //将边e从边集中删除，并返回之
    Edge remove(Edge e);

    //在顶点集V中插入新顶点v，并返回其位置
    Position insert(Vertex v);

    Position insert(Edge e);
}
