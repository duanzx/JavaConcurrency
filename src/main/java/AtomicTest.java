import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by duanzx on 2018/5/11.
 */
public class AtomicTest extends Thread{
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void run(){
        for(int x=0;x<100;x++){
//            System.out.println(atomicInteger.incrementAndGet());
//            System.out.println(atomicInteger.get());
            addCount();
        }
    }

//    public synchronized void addCount(){
    public void addCount(){
        for(int x=0;x<10;x++){
//            System.out.println(atomicInteger.incrementAndGet());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.addAndGet(1);
            atomicInteger.addAndGet(2);
            atomicInteger.addAndGet(3);
            atomicInteger.addAndGet(4);//保证不了原子性
            System.out.println(atomicInteger.get());
        }
    }

    public static void main(String[] args)throws Exception{
//        for(int x=0;x<10;x++){
            AtomicTest atomicTest = new AtomicTest();
            atomicTest.start();
//        }
    }
}
