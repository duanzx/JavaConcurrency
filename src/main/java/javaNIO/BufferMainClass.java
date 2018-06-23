package javaNIO;

import java.nio.LongBuffer;

public class BufferMainClass {

    public static void main(String[] args)throws Exception{
        LongBuffer longBuffer = LongBuffer.allocate(10);
        longBuffer.put(10);
        longBuffer.put(20);
        longBuffer.put(30);
        //wrap arr
        longBuffer.put(2,40);
        System.out.println("写模式");
        System.out.println(longBuffer.position());
        System.out.println(longBuffer.limit());
        longBuffer.flip();
        System.out.println("读模式");
        System.out.println(longBuffer.position());
        System.out.println(longBuffer.limit());
        while(longBuffer.hasRemaining()){
            System.out.println(longBuffer.get());
        }
        longBuffer.clear();


    }

}
