package dateStructure.chapt05;

public interface PQueue {
    int getSize();
    boolean isEmpty();

    // 若Q 非空，那么返回其中最小的条目(不删除)，否则报错
    Entry getMin() throws ExceptionPQueueEmpty;

    Entry insert(Object key, Object obj) throws ExceptionKeyInvalid;

    Entry delMin() throws ExceptionPQueueEmpty;
}
