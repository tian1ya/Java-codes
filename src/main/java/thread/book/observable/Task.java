package thread.book.observable;

@FunctionalInterface
public interface Task<T> {
    T call();
}
