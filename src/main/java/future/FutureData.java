package future;

public class FutureData implements Data {

    private static String returnData;

    public synchronized void setData(final String request) {
        String data = "";
        for (int x = 0; x < 5; x++) {
            System.out.println("第 " + x + " 次正在处理RequestData: " + request);
            data += request + ",";
        }
        returnData = data;
        notify();
    }

    @Override
    public synchronized String getRequestData() {
        if (null == returnData) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return returnData;
    }
}
