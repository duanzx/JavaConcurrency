package threadpool;

/**
 * Created by duanzx on 2018/5/18.
 */
public class Task implements Runnable {
    private String id;
    private String name;

    public Task(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行task:"+id+",名称："+name);
    }
}
