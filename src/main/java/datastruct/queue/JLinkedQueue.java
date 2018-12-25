package datastruct.queue;

/**
 * 入队操作：在链表尾部插入结点
 * 出队操作：头结点的后继结点出队，并将头结点的后继改为它后面的结点
 */
public class JLinkedQueue {
    private int size;
    //队头取数据，队尾添加数据
    private Node head, tail;

    public class Node {
        private Object item;
        private Node next;
    }

    public JLinkedQueue() {
        this.size = 0;
    }

    public void enqueue(Object item) {
        Node node = new Node();
        node.item = item;
        if (head == null) {
            head = node;
            tail = head;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }



    public Object dequeue() {
        if (head == null) {
            return null;
        }
        Node node = head.next;
        Object item = head.item;
        head = node;
        size--;
        return item;
    }

    public static void main(String[] args) throws Exception {
        JLinkedQueue linkedQueue = new JLinkedQueue();
        for (int x = 0; x < 12; x++) {
            linkedQueue.enqueue(x);
            System.out.println(String.format(" Enququq %d , head next : %d ,Tail: %d", x, linkedQueue.head.item, linkedQueue.tail.item));
        }
        System.out.println("count = " + linkedQueue.size);
        for (int x = 0; x < 15; x++) {
            Object item = linkedQueue.dequeue();
            if (null == item) {
                System.out.println(String.format("%d can not dequeue ", x));
            } else {
                System.out.println(String.format("%d can dequeue %d", x, item));
            }
        }
        System.out.println("count = " + linkedQueue.size);
    }

}
