package singleton;
/**
 * 在getInstance方法上加上synchronized修饰符，增加同步的约束
 * 但是synchronized关键字天生的排他性导致了getInstance方法只能在同一时刻被一个性能访问，性能较低
 * */
public final class LazyAsyncSingleton {
    private static LazyAsyncSingleton instance;
    private LazyAsyncSingleton(){

    }
    public static synchronized LazyAsyncSingleton  getInstance(){
        if(instance == null){
            return new LazyAsyncSingleton();
        }
        return instance;
    }
}
