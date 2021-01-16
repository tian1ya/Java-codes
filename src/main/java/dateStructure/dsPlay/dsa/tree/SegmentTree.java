package dateStructure.dsPlay.dsa.tree;

/*
    线段树：
        为什么使用
        解决什么问题

        针对专注的是区间/线段，就是对区间内的所有元素进行一个操作

        而之前的都是对单个元素的操作

        树节点存储的是一个范围的数组元素

        线段树不是完全二叉树

        线段树是平衡二叉树(最大最小深度差不能超过1)

        heap 也是一个平衡二叉树

        如果区间中有 n 个元素，数组表示需要多少个节点
            0 层： 1 节点
            1 层： 2 节点
            2 层： 4 节点
            ...
            h-1 层： 2^(h-1) 节点

            最后一层的节点数大致等于前面所有层节点之和

         如果区间有 n 个元素，那么数组表示需要多少个节点


 */
public class SegmentTree<E> {

    private Object[] data;

    public SegmentTree(E[] arr) {
        data = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new RuntimeException(index +  " index invalid");

        return (E) data[index];
    }

}
