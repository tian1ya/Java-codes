package dateStructure.chapt06;

public class EqualityTesterDefault implements EqualityTester{
    public EqualityTesterDefault() {
    }

    @Override
    public boolean isEqualityTo(Object a, Object b) {
        return a.equals(b);
    }
}
