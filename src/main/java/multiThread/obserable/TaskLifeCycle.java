package multiThread.obserable;

public interface TaskLifeCycle<T> {
    //任务启动时会触发onStart方法
    void onStart(Thread thread);

    //任务正在运行时会触发onRunning方法
    void onRunning(Thread thread);

    //任务运行结束的时候会触发onFinish方法，其中result是任务执行结束后的结果
    void onFinish(Thread thread, T result);

    //任务执行出错时候
    void onError(Thread thread, Exception e);

}
