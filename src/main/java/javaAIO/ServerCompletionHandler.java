package javaAIO;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,ServerClass> {
    @Override
    public void completed(AsynchronousSocketChannel result, ServerClass attachment) {
        attachment.serverSocketChannel.accept(attachment, this);
        read(result);
    }

    @Override
    public void failed(Throwable exc, ServerClass attachment) {
        System.out.println("处理客户端请求失败：");
        exc.printStackTrace();
    }

    private void read(AsynchronousSocketChannel socketChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                String requestData = new String(attachment.array()).trim();
                System.out.println("接收到客户端请求数据：" + requestData);
                write(socketChannel, "服务端响应成功数据");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("读取客户端请求数据错误：");
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel socketChannel, String responseData) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(responseData.getBytes());
        byteBuffer.flip();
        try {
            socketChannel.write(byteBuffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}