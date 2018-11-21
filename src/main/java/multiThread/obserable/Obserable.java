package multiThread.obserable;

public interface Obserable {
    //定义生命周期的枚举类型
    enum Cycle {
        STARTED, RUNING, DONE, ERROR;
    }

    //获取当前任务的生命周期状态
    Cycle getCycle();

    //定义启动线程方法，主要作用是为了屏蔽Thread的其它方法
    void start();

    //定义线程的打断方法，作用于start方法一样，也是为了屏蔽Thread的其它方法
    void interrupt();
}
