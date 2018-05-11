/**
 * Created by duanzx on 2018/5/11.
 */
public class VolatileNoAtomic extends Thread {
    private static volatile int count;
    public void  addCount(){
        for(int x=0;x<100;x++){
            count++;
        }
        System.out.println("count: "+count);
    }

    @Override
    public void run(){
        addCount();
    }

    public static void main(String[] args)throws Exception{
        for(int x=0;x<10;x++){
            VolatileNoAtomic noAtomicThread = new VolatileNoAtomic();
            noAtomicThread.start();
        }
    }


}
