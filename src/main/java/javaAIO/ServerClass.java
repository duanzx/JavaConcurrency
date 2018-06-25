package javaAIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerClass {

    public AsynchronousServerSocketChannel serverSocketChannel;

    public ServerClass(int port) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 10);
            serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
            serverSocketChannel.bind(new InetSocketAddress("localhost", port));
            System.out.println("Server start port:" + port);
            serverSocketChannel.accept(this, new ServerCompletionHandler());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        new ServerClass(8080);
    }
}
