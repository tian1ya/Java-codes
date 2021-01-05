package dateStructure.chapt03;

public interface Vector {
    boolean isEmpty();

    int getSize();

    Object getAtRank(int r) throws ExceptionBoundaryViolation;

    Object replaceAtRank(int r, Object obj) throws ExceptionBoundaryViolation;

    Object insertAtRank(int r, Object obj) throws ExceptionBoundaryViolation;

    Object removeAtRank(int r) throws ExceptionBoundaryViolation;
}
