package datastruct.stack;

/*
 * Java版顺序存储的栈实现，每次取都取数组的尾部元素
 * */
public class JArrayStack {
    private final Object[] items;
    private int tail;//当前数组内的有效元素个数
    private int n;//数组元素的总空间大小

    public JArrayStack(int n) {
        items = new Object[n];
        this.n = n;
        this.tail = 0;
    }

    public boolean push(Object item) {
        //如果栈里的元素已经满了，不需要入栈
        if (tail == n) {
            return false;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    public Object pop() {
        //如果栈里的元素已经满了，不需要入栈
        if (tail == 0) {
            return null;
        }
        tail--;
        Object item = items[tail];
        return item;
    }

    public static void main(String[] args) throws Exception {
        JArrayStack jArrayStack = new JArrayStack(10);
        for (int x = 0; x < 12; x++) {
            boolean flag = jArrayStack.push(x);
            if (!flag) {
                System.out.println(String.format("%d can not push ", x));
            } else {
                System.out.println(String.format("%d can push ", x));
            }
        }
        System.out.println("count = " + jArrayStack.tail);
        for (int x = 0; x < 12; x++) {
            Object item = jArrayStack.pop();
            if (null == item) {
                System.out.println(String.format("%d can not pop ", x));
            } else {
                System.out.println(String.format("%d can pop %d", x, item));
            }
        }
        System.out.println("count = " + jArrayStack.tail);

    }
}
