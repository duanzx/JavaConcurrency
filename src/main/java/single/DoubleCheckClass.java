package single;

public class DoubleCheckClass {
    private static DoubleCheckClass singleClass;

    public static DoubleCheckClass getInstance() {
        if (singleClass == null) {
            try {
                Thread.sleep(3000);//初始化对象所花费的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DoubleCheckClass.class) {
                if (singleClass == null) {
                    singleClass = new DoubleCheckClass();
                }
            }
        }
        return singleClass;
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleCheckClass.getInstance().hashCode());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleCheckClass.getInstance().hashCode());
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleCheckClass.getInstance().hashCode());
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
