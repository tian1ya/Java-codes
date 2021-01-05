package dateStructure.chapt05;

public class ComparatorDefault implements Comparator {
    @Override
    public int compare(Object a, Object b) {
        return ((Comparable) a).compareTo(b);
    }
}
