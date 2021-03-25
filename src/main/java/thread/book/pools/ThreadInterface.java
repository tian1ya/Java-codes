package thread.book.pools;

@FunctionalInterface
public interface ThreadInterface {
    Thread createThread(Runnable runnable);
}
