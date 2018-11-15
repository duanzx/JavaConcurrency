package singleton;

import org.junit.Test;

public class SingletonTest {

    @Test
    public void testDoubleCheckSingleton(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
                System.out.println(doubleCheckSingleton.hashCode());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
                System.out.println(doubleCheckSingleton.hashCode());
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
                System.out.println(doubleCheckSingleton.hashCode());
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
                System.out.println(doubleCheckSingleton.hashCode());
            }
        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
                System.out.println(doubleCheckSingleton.hashCode());
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
    @Test
    public void testHolderSingleton(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                HolderSingleton holderSingleton = HolderSingleton.getInstance();
                System.out.println(holderSingleton.hashCode());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                HolderSingleton holderSingleton = HolderSingleton.getInstance();
                System.out.println(holderSingleton.hashCode());
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                HolderSingleton holderSingleton = HolderSingleton.getInstance();
                System.out.println(holderSingleton.hashCode());
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                HolderSingleton holderSingleton = HolderSingleton.getInstance();
                System.out.println(holderSingleton.hashCode());
            }
        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                HolderSingleton holderSingleton = HolderSingleton.getInstance();
                System.out.println(holderSingleton.hashCode());
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
