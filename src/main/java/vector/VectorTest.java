package vector;

import java.util.Vector;//同步容器，线程不安全
//保证线程安全：将他们的状态封装起来，并对每个公有方法都进行同步，使得每次只有一个线程能访问容器的状态

public class VectorTest {
    public String getLast(Vector<String> vectorList) {
        synchronized (vectorList) {
            int size = vectorList.size();
            if (size == 0) {
                return null;
            }
            return vectorList.get(size - 1);
        }
    }

    public void deleteLast(Vector<String> vectorList) {
        synchronized (vectorList) {
            int size = vectorList.size();
            if (size == 0) {
                return;
            }
            vectorList.remove(size - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        final Vector<String> list = new Vector<>();
        for (int x = 0; x < 10; x++) {
            list.add("00" + x);
        }
        VectorTest vectorTest = new VectorTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    vectorTest.getLast(list);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    vectorTest.deleteLast(list);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
