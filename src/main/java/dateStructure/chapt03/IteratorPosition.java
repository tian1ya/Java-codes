package dateStructure.chapt03;

import dateStructure.chapt02.Position;

public class IteratorPosition implements Iterator{
    private List list;
    private Position nextPosition;

    public IteratorPosition() {
        this.list = null;
    }
    public IteratorPosition(List list) {
        this.list = list;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();
        }
    }

    @Override
    public boolean hasNext() {
        // 在每次读取 getNext 的时候就将 nextPosition 更新了，所以这里之间判断是否为空。
        return nextPosition != null;
    }

    @Override
    public Object getNext() {
        if (!hasNext()) {
            throw new RuntimeException("no elements");
        }
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()){
            nextPosition = null;
        } else
            nextPosition = list.getNext(currentPosition);
        return currentPosition;
    }
}
