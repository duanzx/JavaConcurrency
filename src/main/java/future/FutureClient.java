package future;

public class FutureClient {

    public Data request(final String request) {
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                futureData.setData(request);
            }
        }).start();
        return futureData;
    }

    public static void main(String[] args) throws Exception {
        FutureClient futureClient = new FutureClient();
        System.out.println("start request");
        Data data = futureClient.request("just for test");
        String result = data.getRequestData();
        System.out.println("end request and result = " + result);
    }
}
