package concurrentUtil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by duanzx on 2018/5/25.
 */
public class CyclicBarrierTest {
    static class Runner implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private String name;

        public Runner(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random().nextInt(5)));
                System.out.println(name + " has OK");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " has GO");
        }
    }

    public static void main(String[] args) throws Exception {
       final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Thread(new Runner(cyclicBarrier, "runner-one")));
        executorService.submit(new Thread(new Runner(cyclicBarrier, "runner-two")));
        executorService.submit(new Thread(new Runner(cyclicBarrier, "runner-three")));
        executorService.shutdown();
    }
}
