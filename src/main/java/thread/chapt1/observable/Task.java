package thread.chapt1.observable;

@FunctionalInterface
public interface Task<T> {
    T call();
}
