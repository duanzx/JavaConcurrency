package datastruct.queue;


/**
 * 数组维护两个角标head,tail 入队操作tail++ , 出队操作head++
 * 每次都取head元素
 * 出队时不用搬移数据，如果没有空闲空间了，只需要在入队的时候，在集中触发一次数据的搬移操作
 */
public class JArrayQueue {
    private Object[] items;
    private int head;
    private int tail;
    private int size;

    public JArrayQueue(int size) {
        items = new Object[size];
        this.size = size;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * @param item 入队操作，将item放在队尾
     */
    public boolean enqueue(Object item) {
        if (tail == size) {
            //如果head = 0,说明没有执行过出队操作
            if (head == 0) {
                return false;
            }
            //3 4 5 6  ->  1 2 3 4
            for (int i = head; i < (tail + 1); i++) {
                items[i - head] = items[i];
            }
            head = 0;
            tail = tail - head;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    public Object dequeue() {
        if (tail == head) {
            return null;
        }
        Object item = items[head];
        head++;
        return item;
    }


    public static void main(String[] args) throws Exception {
        JArrayQueue jArrayQueue = new JArrayQueue(10);
        for (int x = 0; x < 12; x++) {
            boolean flag = jArrayQueue.enqueue(x);
            if (!flag) {
                System.out.println(String.format("%d can not enqueue ", x));
            } else {
                System.out.println(String.format("%d can enqueue ,head= %d, tail = %d", x, jArrayQueue.head, jArrayQueue.tail));
            }
        }
        System.out.println("count = " + jArrayQueue.tail);
        for (int x = 0; x < 12; x++) {
            Object item = jArrayQueue.dequeue();
            if (null == item) {
                System.out.println(String.format("%d can not dequeue ", x));
            } else {
                System.out.println(String.format("%d can dequeue %d", x, item));
            }
        }
        System.out.println("count = " + jArrayQueue.tail);
    }
}
