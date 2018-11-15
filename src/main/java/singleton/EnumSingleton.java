package singleton;

/*
 * 枚举类型不允许被继承、同样是线程安全的并且只能被实例化一次。
 * 但是枚举类型不能被懒加载，对Singleton主动使用。*
 * */
public enum EnumSingleton {
    INSTANCE;

    EnumSingleton() {

    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
