package multiThread.obserable;

/**
 * 定义一个线程执行的各个阶段，并在各个阶段都有对应的处理函数
 * 开始执行，正在执行，执行结束
 * 总结：向一个线程传入一个事件函数
 * 在接口Obserable中定义与Thread同样的方法用于屏蔽Thread的其它API
 * 将ObserableThread中的run方法修饰为final，或者将ObserableThread类修饰为final，防止子类继承重写，导致整个生命周期的监控失败
 * Task是一个可以返回结果的接口
 * ObserableThread本身的run方法充当了事件源的发起者，而TaskLifeCycle则扮演了事件回调的响应者。
 */
public class ObserableThread<T> extends Thread implements Obserable {
    //代表线程的生命周期状态
    private final TaskLifeCycle<T> lifeCycle;
    private final Task<T> task;
    private Cycle cycle;

    public ObserableThread(TaskLifeCycle lifeCycle, Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("The task is required");
        }
        this.task = task;
        this.lifeCycle = lifeCycle;
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
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifeCycle == null) {
            return;
        }
        try {
            if (cycle == Cycle.STARTED) {
                this.lifeCycle.onStart(currentThread());
                return;
            }
            if (cycle == Cycle.RUNING) {
                this.lifeCycle.onRunning(currentThread());
                return;
            }
            if (cycle == Cycle.DONE) {
                this.lifeCycle.onFinish(currentThread(), result);
            }
            if (cycle == Cycle.ERROR) {
                this.lifeCycle.onError(currentThread(), e);
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }
}
