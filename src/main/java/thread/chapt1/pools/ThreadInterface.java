package thread.chapt1.pools;

@FunctionalInterface
public interface ThreadInterface {
    Thread createThread(Runnable runnable);
}
