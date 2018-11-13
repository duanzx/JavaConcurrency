package singleton;
/**
 * 饿汉式的单例设计模式可以保证多个线程下的唯一实例，getInstance方法性能也比较高，但是无法进行懒加载
 * final 代表不允许被继承
 * */
public final class HangrySingleton {
    private static HangrySingleton instance = new HangrySingleton();
    private HangrySingleton(){

    }
    public HangrySingleton getInstance(){
        return instance;
    }
}
