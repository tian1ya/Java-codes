package dateStructure.chapt10;

import dateStructure.chapt02.Position;
import dateStructure.chapt03.Iterator;

/*
    (有向)图的顶点结构接口
    无向图也可以看成是有向图，为此，只需将每条无向边替换为对称的一对有向边
 */
public interface Vertex {

    //未发现的定点
    final static int UNDISCOVERED = 0;
    //已发现的定点
    final static int DISCOVERED = 1;
    //已访问过的定点
    final static int VISITED = 2;

    //返回当前定点的信息
    Object getInfo();

    //将当前定点的信息更新为x, 并返回原先的信息
    Object setInfo(Object x);

    //放回当前定点的出、入度
    int outDeg();
    int inDeg();

    //放回当前定点所有关联边、关联边位置的迭代器
    Iterator inEdges();
    Iterator inEdgePosition();
    Iterator outEdges();
    Iterator outEdgePosition();

    //取当前定点在所属的图的顶点集中V中的位置
    Position getVPosInV();

    // 读取、设置顶点的状态
    int getStatus();
    int setStatus(int s);

    // 读取、设置顶点的时间标签
    int getDStamp();
    int setDStamp(int s);
    int getFStamp();
    int setFStamp(int s);

    //读取、设置顶点至起点的最短距离(BFS或BestFS)
    int getDistance();
    int setDistance(int s);

    //读取、设置顶点在的DFS、BFS、BestFS或MST树中的父亲
    Vertex getBFSParent();
    Vertex setBFSParent(Vertex s);
}
