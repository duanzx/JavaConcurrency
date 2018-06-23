package javaNIO.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerClass implements Runnable {

    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public ServerClass(int port) {
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //将服务的Socket注册到Selector
    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                System.out.println("已经监听完成某个客户端对应的事件");
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey key = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            this.accept(key);
                        }
                        if (key.isReadable()) {
                            this.read(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 将客户端Socket注册到Selector，多个
    public void accept(SelectionKey key) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            this.byteBuffer.clear();
            int count = socketChannel.read(byteBuffer);
            if (count == -1) {
                socketChannel.close();
                key.cancel();
                return;
            }
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            System.out.println("接收客户端请求的数据：" + new String(bytes).trim());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        new Thread(new ServerClass(8080)).start();
    }
}
