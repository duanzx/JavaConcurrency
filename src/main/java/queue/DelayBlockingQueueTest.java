package queue;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayBlockingQueueTest {
    public static class Task implements Delayed {
        private String name;
        private Date endTime;

        public Task(String name, Date endTime) {
            this.name = name;
            this.endTime = endTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return endTime.getTime() - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Task task = (Task) o;
            return this.getDelay(TimeUnit.SECONDS) - task.getDelay(TimeUnit.SECONDS) > 0 ? 1 : 0;
        }
    }

    public static void main(String[] args) throws Exception {
        final DelayQueue queue = new DelayQueue();
        Date now = new Date();
        queue.put(new Task("task1", new Date(now.getTime() + 8000)));
        //在同一线程里无论时间设置的大小是多少，都先取这个元素，如果设置的值大于后面的元素，则后面的元素直接取出，没有延时
        queue.put(new Task("task2", new Date(now.getTime() + 5000)));
        queue.put(new Task("task3", new Date(now.getTime() + 3000)));
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task task = null;
                try {
                    task = (Task) queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "取出task:" + task.getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task task = null;
                try {
                    task = (Task) queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "取出task:" + task.getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Task task = null;
                try {
                    task = (Task) queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "取出task:" + task.getName());
            }
        }).start();
    }
}
