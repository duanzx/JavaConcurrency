package WaitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duanzx on 2018/5/11.
 */
public class ListQueueTestFirst {
    //Wait�����ͷ�����Notify�������ͷ���
    private volatile static List<Integer> queueList = new ArrayList<Integer>();

    public synchronized void addQueue(int ele) {
        queueList.add(ele);
        System.out.println("---�Ѿ������ " + queueList.size() + " Ԫ��------");
    }

    public int getListSize(){
        return queueList.size();
    }

    //���ʹ���̳߳ش����̣߳���������������
    public static void main(String[] args) throws Exception {
        //t1�̸߳����� queueList�����Ԫ��
        //t2.�������t1������ӵ�Ԫ�صĸ�������5ʱ��t2��Ҫ֪ͨt1,ֹͣ���Ԫ��
        //����ģʽ��ʹ��listģ��һ�����У�������������������

        final ListQueueTestFirst listQueueTestFirst = new ListQueueTestFirst();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int x = 0; x < 10; x++) {
                        listQueueTestFirst.addQueue(x);
                        System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName() + "�����һ��Ԫ��");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(listQueueTestFirst.getListSize()==5){
                        throw new RuntimeException("��ǰ�̣߳�" + Thread.currentThread().getName() +" ֹͣt1�̣߳�����������������");
                    }
                }
            }
        });
        t2.start();
    }
}
