package datastruct.stack;

/*
 * Java版链式存储的栈实现，每次取都取数组的头部指针
 * */
public class JLinkedStack {
    private Object[] items;
    private int head;

    public JLinkedStack() {
        this.items = new Object[Integer.MAX_VALUE];
        this.head = -1;
    }

    public boolean push(Object item) {


        return false;
    }

    public Object pop() {
        return null;
    }

}
