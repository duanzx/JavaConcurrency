package multiThread.obserable;

/*Java 8为函数式接口引入了一个新注解@FunctionalInterface，主要用于编译级错误检查，加上该注解，当你写的接口不符合函数式接口定义的时候，编译器会报错。
 * 可以用于lamba函数表达式,相当于一个代码执行块
 * http://www.cnblogs.com/chenpi/p/5890144.html
 * 函数式接口里允许定义静态方法
 * 函数式接口里允许定义默认方法
 * */
@FunctionalInterface
public interface Task<T> {
    T call();

    default T callOne() {
        return null;
    }

    static void other() {
        System.out.println("Hello");
    }
}
