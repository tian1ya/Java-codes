package dateStructure.dsPlay.dsa.tree;

/*
    二插堆是一颗完全二叉树

    若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，
    这就是完全二叉树。

    完全二插树，就将元素排列成树的形状，树的右下角可能是空的

    堆中某个节点的值，总是不大于其父节点的值，也就是所有节点的值，总是大于等于子节点的值

    这样的堆是你最大堆

    (还可以定义最小堆)

    最大堆和最小堆是可以结合的，因为你大和小是有client 端给的

    以下的2个结论有图示：
    当将数组的第一个位置空出来后：index 从1开始
        其特点: 可以存放在数组中，父子节点的index 是有关系的
            left_node_index = parent_index * 2
            right_node_index = parent_index * 2 + 1

        而且还能很容易知道每个节点的父亲是谁：(一下是正数除法)
            parent_index = left_node_index / 2
            parent_index = right_node_index / 2

     如果数组的第一个位置占用：index 从0开始
        其特点: 可以存放在数组中，父子节点的index 是有关系的
            left_node_index = parent_index * 2 + 1
            right_node_index = parent_index * 2 + 2

        而且还能很容易知道每个节点的父亲是谁：(一下是正数除法)
            parent_index = (left_node_index-1) / 2
            parent_index = (right_node_index-1) / 2


 */

import dateStructure.dsPlay.dsa.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        // 任意数组整理成 heap
        data = new Array<>(arr);
        for (int i = getParentIndex(arr.length-1); i >=0 ; i--) {
            siftDown(i); // 一次 siftDown 将一个元素放置到合理的位置，这里是从最后一个元素的父亲index 开始的，注意不能是最后一个元素开始，最后一个元素是没有子节点的
        }
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getParentIndex(int index) {
        if (index == 0)
            throw new RuntimeException(index + " has no parent");
        return (index - 1) / 2;
    }

    public int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    public int getRightChildIndex(int index) {
        return getLeftChildIndex(index) + 1;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int lastChildIndex) {
        if (lastChildIndex > 0 && data.get(lastChildIndex).compareTo(data.get(getParentIndex(lastChildIndex))) > 0) {
            data.swap(lastChildIndex, getParentIndex(lastChildIndex));
            siftUp(getParentIndex(lastChildIndex));
        }
    }

    public E findMax() {
        return data.getFirst();
    }

    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0);
        return ret;
    }

    private void siftDown(int  topIndex) {
        int leftChildIndex = getLeftChildIndex(topIndex);
        int rightChildIndex = leftChildIndex + 1;

        int nextTopIndex = leftChildIndex;

        if (rightChildIndex < data.getSize() && data.get(rightChildIndex).compareTo(data.get(leftChildIndex)) > 0) {
            // 这类没有对左侧进行 < data.size 判断，原因是 当右侧 data.size， 那么左侧一定也是成立的
            nextTopIndex = rightChildIndex; // 左右孩子中，到这里选出值最大的那个孩子的 index
        }

        if (nextTopIndex < data.getSize() && data.get(topIndex).compareTo(data.get(nextTopIndex)) < 0) {
            // 这里进来说明新的 heap 不符合 maxHeap 规则
            data.swap(topIndex, nextTopIndex);
            siftDown(nextTopIndex);
        }
    }

    public E replace(E e) {
        // 堆顶元素替换
        E max = findMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }
}
