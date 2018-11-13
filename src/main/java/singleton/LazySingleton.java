package singleton;
/**
 *getInstance在多线程环境下，会导致instance对象实例化多次，并不能保证单例的唯一性
 *
 * */
public final class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){

    }
    public static LazySingleton getInstance(){
        if(null == instance){
            return new LazySingleton();
        }
        return instance;
    }
}
