package queue;


import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {
    public static class Task implements Comparable<Task> {
        private String name;
        private int id;

        @Override
        public int compareTo(Task o) {
            return this.id > o.id ? 1 : (this.id == o.id ? 0 : -1);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        Task task1 = new Task();
        task1.setId(4);
        task1.setName("task1");
        Task task2 = new Task();
        task2.setId(2);
        task2.setName("task2");
        Task task3 = new Task();
        task3.setId(3);
        task3.setName("task3");
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>(5);
        queue.offer(task1);
        queue.offer(task2);
        queue.offer(task3);
        for (int x = 0; x < 3; x++) {
            Task task = queue.take();
            System.out.println(task.getId() + "-" + task.getName());//take时有优先级
        }


    }
}
