/**
 * Created by duanzx on 2018/5/11.
 */
public class VolatileTest extends Thread {
//    private volatile boolean running=true;
    private boolean running=true;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run(){
        System.out.println(System.currentTimeMillis());
        System.out.println("run--------------------");
        while (running){
//            System.out.println("true-----------------------");
        }
        System.out.println(System.currentTimeMillis());
        System.out.println("false--------------------------");
    }

    public static void main(String[] args)throws Exception{
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.start();
        Thread.sleep(3000);
        volatileTest.setRunning(false); //
        System.out.println("set runing = false");
//        Thread.sleep(1000);
//        System.out.println(volatileTest.running);

    }
}
