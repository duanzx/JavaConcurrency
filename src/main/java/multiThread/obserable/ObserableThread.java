package multiThread.obserable;

public class ObserableThread<T> extends Thread implements Obserable {
    //代表线程的生命周期状态
    private final TaskLifeCycle<T> lifeCycle;
    private final Task<T> task;
    private Cycle cycle;

    public ObserableThread(Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("The task is required");
        }
        this.task = task;
        this.lifeCycle = new EmptyLifeCycle<>();
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    /**
     * 重写父类的run方法，并且将其修饰为final类型，不允许子类再次对其进行重写
     * run 方法在线程的运行期间，可监控任务在执行过程中的各个生命周期阶段
     * 任务每经过一个阶段相当于发生了一次事件
     * update 方法用于通知事件的监听者，此时任务在执行过程中发生了什么，追重要的通知是异常的处理。
     */
    @Override
    public final void run() {

    }

    private void update() {

    }
}
