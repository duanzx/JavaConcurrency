package single;

//静态内部类
public class InnerSingleClass {
    private static class Single {
        private static InnerSingleClass innerSingleClass = new InnerSingleClass();
    }

    public static InnerSingleClass getInstance() {
        return Single.innerSingleClass;
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(InnerSingleClass.getInstance().hashCode());
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(InnerSingleClass.getInstance().hashCode());
            }
        });
        t2.start();
    }
}
