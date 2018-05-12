package vector;

import WaitNotify.ListQueueSecond;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class VectorTestSecond {

    public String getLast(List<String> vectorList) {
        int size = vectorList.size();
        if (size == 0) {
            return null;
        }
        return vectorList.get(size - 1);
    }

    public void deleteLast(List<String> vectorList) {
        int size = vectorList.size();
        if (size == 0) {
            return;
        }
        vectorList.remove(size - 1);
    }

    public static void main(String[] args) throws Exception {
        final List<String> list = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            list.add("00" + x);
        }
        VectorTestSecond vectorTest = new VectorTestSecond();
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
