package dateStructure.chapt03;

public class VectorArray implements Vector {
    private int N = 1024;

    // 实际规模
    private int size;
    private Object[] a;

    public VectorArray() {
        a = new Object[N];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        checkInBound(r);
        return a[r];
    }

    @Override
    public Object replaceAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkInBound(r);
        a[r] = obj;
        return r;
    }

    @Override
    public Object insertAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkInBound(r);
        expand();
        if (size + 1 > N) {
            throw new ExceptionBoundaryViolation("出界:溢出");
        }
        for (int i = size; i > r; i--) {
            a[i + 1] = a[i];
        }
        a[r] = obj;
        return obj;
    }

    @Override
    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        checkInBound(r);
        Object obj = a[r];
        for (int i = r; i < size; i++) {
            a[i] = a[i + 1];
        }
        size--;
        return obj;
    }

    public void checkInBound(int r) {
        if (r < 0 || r >= size) {
            throw new ExceptionBoundaryViolation("出界");
        }
    }

    public void expand() {
        if (size + 1 > N) {
            N *= 2;
            Object[] A = new Object[N];
            for (int i = 0; i < a.length; i++) {
                A[i] = a[i];
            }
            a = A;
        }
    }
}
