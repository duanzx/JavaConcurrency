package datastruct.stack;

/**
 * Java版链式存储的栈实现，每次取都取数组的头部指针
 */
public class JLinkedStack {
    /**
     * head指针
     */
    private Node head;
    private int size;

    private class Node {
        /**
         * 数据域
         */
        private Object item;
        /**
         * 指针域
         */
        private Node next;
    }

    /**
     * 初始化head指针，不存储任何元素
     */
    public JLinkedStack() {
        this.head = new Node();
        size = 0;
    }

    /**
     * @param item 用于存储新的元素
     * @return 返回head指针
     */
    public Node push(Object item) {
        Node newNode = new Node();
        newNode.item = item;
        //将新的节点转为head指针
        newNode.next = head;
        head = newNode;
        size++;
        return newNode;
    }

    public Object pop() {
        //判断head指针的下一个引用是否为NULL,如果为Null，表示没有元素可以pop
        if (head.next == null) {
            return null;
        }
        //取出head指针
        Node node = head;
        //将下一个节点作为head指针
        head = head.next;
        size--;
        return node.item;
    }

    public static void main(String[] args) throws Exception {
        JLinkedStack jLinkedStack = new JLinkedStack();
        for (int x = 0; x < 12; x++) {
            Node headNode = jLinkedStack.push(x);
            System.out.println("push node "+ x + ", next node = "+ (null == headNode.next.item?"Null":headNode.next.item));
        }
        System.out.println("count = " + jLinkedStack.size);
        for (int x = 0; x < 15; x++) {
            Object item = jLinkedStack.pop();
            if (null == item) {
                System.out.println(String.format("%d can not pop ", x));
            } else {
                System.out.println(String.format("%d can pop %d", x, item));
            }
        }
        System.out.println("count = " + jLinkedStack.size);
    }

}
