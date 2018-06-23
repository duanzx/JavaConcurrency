package javaNIO.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.UUID;

public class ClientClass {

    public static void main(String[] args) throws Exception {
        for(int x = 0 ;x<1000;x++){
            request(x+"-----"+UUID.randomUUID().toString().toUpperCase());
        }
    }

    public static void request(String requestData) throws Exception {
       SocketChannel socketChannel=null;
        try {
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
            socketChannel = SocketChannel.open();
            socketChannel.connect(address);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(requestData.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socketChannel != null) {
                socketChannel.close();
            }
        }


    }
}
