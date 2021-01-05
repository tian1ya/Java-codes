package thread.chapt1.pools;

@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
