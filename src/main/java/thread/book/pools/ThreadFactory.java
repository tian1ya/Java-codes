package thread.book.pools;

@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
