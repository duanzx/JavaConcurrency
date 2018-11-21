package multiThread.obserable;

import java.sql.SQLSyntaxErrorException;

public class MainClass {
    public static void main(String[] args) throws Exception {
        /*在这里要求执行结果是一个函数*/
        TaskLifeCycle taskLifeCycle = new TaskLifeCycle<Task>() {
            @Override
            public void onStart(Thread thread) {
                System.out.println("******开始执行******");
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRunning(Thread thread) {
                System.out.println("******正在执行******");
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish(Thread thread, Task result) {
                System.out.println("******执行结束******");
                result.call();
            }

            @Override
            public void onError(Thread thread, Exception e) {
                System.out.printf("******执行时遇到错误：%s******%n", e.getMessage());
            }
        };
        ObserableThread obserableThread = new ObserableThread(taskLifeCycle, new Task<Task>() {
            @Override
            public Task call() {
                System.out.println("*****线程执行结束*****");
                return new Task() {
                    @Override
                    public Object call() {
                        System.out.println("执行结果函数");
                        return null;
                    }
                };
            }
        });
        obserableThread.start();
    }
}
