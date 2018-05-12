package threadlocal;

public class ThreadLocalTest {
    public final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setGlobal(String str) {
        threadLocal.set(str);
    }

    public String getGlobal() {
        return threadLocal.get();
    }

    public static void main(String[] args) throws Exception {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalTest.setGlobal("zhangsan");
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocalTest.getGlobal());
            }
        });
        t2.start();
    }
}
