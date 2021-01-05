package dateStructure.chapt03;

import dateStructure.chapt02.Position;
import dateStructure.chapt02.Queue.DLNode;

public class SequenceDLNode extends ListDLNode implements Sequence {
    private void checkRank(int r, int n) {
        if (r < 0 || r > n)
            throw new ExceptionBoundaryViolation("非法索引， 合法范围[0," + n + ")");
    }

    @Override
    public Position rank2Pos(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        DLNode node;
        // index 小于size 的一半，那么从前开始找
        // rank2Pos(r)做了很小的优化:首先判断秩为 r 的元素究竟处于序列的前半段还是后半段，然后 分别从前端或后端开始扫描
        if (r < getSize() / 2) {
            node = header.getNext();
            for (int i = 0; i < r; i++) {
                node = node.getNext();
            }
        } else {
            // 否则从后开始找
            node = tailer.getPrev();
            for (int i = getSize(); i < r; i--) {
                node = node.getPrev();
            }
        }
        return node;
    }

    @Override
    public int pos2Rank(Position p) throws ExceptionBoundaryViolation {
        DLNode node = header.getNext();
        int r=0;
        while (node != p){
            if (node == p)
                return r;
            r++;
            node = node.getNext();
        }
        throw new ExceptionPositionInvalid("Position 不属于该序列");
    }

    @Override
    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return rank2Pos(r).getEle();
    }

    @Override
    public Object replaceAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        DLNode node = (DLNode) rank2Pos(r);
        return replace(node, obj);
    }

    @Override
    public Object insertAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        if (getSize() == r) {
            insertLast(obj);
        }
        else
            insertBefore(rank2Pos(r), obj);
        return obj;
    }

    @Override
    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return remove(rank2Pos(r));
    }
}
