package thread.book.pools;

public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String s) {
        super(s);
    }
}
