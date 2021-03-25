package thread.book.observable;

public interface TaskLifecycle<T> {

    // 任务启动的时候出发
    void onStart(Thread thread);

    // 任务正在运行的时候触发
    void onRunning(Thread thread);

    // 任务运行结束之后会触发，其中result 时任务执行结束之后的结果
    void onFinish(Thread thread, T result);

    // 任务执行报错触发onError
    void onError(Thread thread, Exception e);


    // 生命周期接口的空实现(Adapter)
    // 主要是为了让使用者保存对Thread 类的使用习惯
    class EmptyLifecycle<T> implements TaskLifecycle<T> {
        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
