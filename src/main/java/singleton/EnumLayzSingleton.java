package singleton;

public class EnumLayzSingleton {
    private EnumLayzSingleton(){

    }
    private enum  Holder{
        INSTANCE;
        private EnumLayzSingleton instance;
        Holder(){
            instance = new EnumLayzSingleton();
        }
        private EnumLayzSingleton getInstance(){
            return instance;
        }
    }
    public static EnumLayzSingleton getInstance(){
        return Holder.INSTANCE.getInstance();
    }
}
