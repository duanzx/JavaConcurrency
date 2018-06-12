package falseSharing;

/**
 * Created by duanzx on 2018/6/12.
 */
public final class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    public FalseSharing(final  int arrayindex){
        this.arrayIndex = arrayindex;
    }
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static {
        for(int i=0;i<longs.length;i++){
            longs[i] = new VolatileLong();
        }
    }

    public static void main(final String[] args)throws Exception{
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration = " + (System.currentTimeMillis() - start));
    }

    private static void runTest()throws Exception{
        Thread[] threads = new Thread[NUM_THREADS];
        for(int i=0;i<threads.length;i++){
           threads[i] = new Thread(new FalseSharing(i));
        }
        for(Thread t : threads){
            t.start();
        }
        for(Thread t : threads) {
            t.join();//就像主线程是开会一样，会议（主线程）正在进行中，这时候需要一个人join进来（子线程启动），并执行他自己的操作，如果他的操作没有执行完毕，则会议（主线程）不能结束
            //你要洗澡需要先烧水，再准备沐浴的东西，但是烧水的时间太长，所以你先去烧水（join），在去准备沐浴的东西，但是如果水没有烧好，你是洗不了澡的
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS+1;
        while (0 != --i){
            longs[arrayIndex].value = i;
        }
    }
}
