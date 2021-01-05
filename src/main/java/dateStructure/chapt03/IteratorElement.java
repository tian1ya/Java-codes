package dateStructure.chapt03;

import dateStructure.chapt02.Position;

public class IteratorElement implements Iterator {
    private List list;
    private Position nextPosition;

    public IteratorElement() {
        this.list = null;
    }

    public IteratorElement(List list) {
        this.list = list;
        if (list.isEmpty()) {
            nextPosition = null;
        }else {
            nextPosition = list.first();
        }
    }

    @Override
    public boolean hasNext() {
        return nextPosition != null;
    }

    @Override
    public Object getNext() {
        if (nextPosition == null) {
            throw new RuntimeException("xx");
        }

        Position currentPos = nextPosition;
        if (currentPos == list.last()) {
            nextPosition = null;
        }else {
            nextPosition = list.getNext(currentPos);
        }
        return currentPos.getEle();
    }
}
