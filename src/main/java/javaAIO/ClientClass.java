package javaAIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class ClientClass implements Runnable {
    private AsynchronousSocketChannel socketChannel;

    public ClientClass(){
        try {
            socketChannel = AsynchronousSocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost",8080));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String requestData){
        try{
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(requestData.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer).get();
            read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void read(){
        try{
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer).get();
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            System.out.println("客户端接收到数据："+new String(bytes).trim());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){

        }
    }

    public static void main(String[] args)throws Exception{
        ClientClass c1 = new ClientClass();
        ClientClass c2 = new ClientClass();
        ClientClass c3 = new ClientClass();
        new Thread(c1,"c1").start();
        new Thread(c2,"c2").start();
        new Thread(c3,"c3").start();
        Thread.sleep(1000);
        c1.write("request c1 data");
        c2.write("request c2 data");
        c3.write("request c3 data");
    }
}
