package singleton;

/*
* 利用了内部静态类
* 在HolderSingleton类中并没有instance的静态成员，而是将其放到了静态内部类Holder之中
* 因此在HolderSingleton的初始化过程中并不会创建实例，
* Holder类中定义了HolderSingleton的静态变量，并且直接进行了实例化，当Holder被主动引用的时候则会创建实例
* 这个创建过程在Java程序编辑时期收集至 clinit（）方法，该方法又是同步方法，同步方法可以保证内存的可见性，JVM指令
* 的顺序性和原子性。
* Hodler方式的单例设计是最好的设计之一，也是目前使用比较广泛的设计之一。
* */
public final class HolderSingleton {
    private HolderSingleton(){

    }
    private static class Holder{
        private static HolderSingleton instance = new HolderSingleton();
    }
    public static HolderSingleton getInstance(){
        return Holder.instance;
    }

}
