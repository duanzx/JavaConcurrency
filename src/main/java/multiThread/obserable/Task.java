package multiThread.obserable;

/*Java 8为函数式接口引入了一个新注解@FunctionalInterface，主要用于编译级错误检查，加上该注解，当你写的接口不符合函数式接口定义的时候，编译器会报错。
 * 可以用于lamba函数表达式,相当于一个代码执行块
 * */
@FunctionalInterface
public interface Task<T> {
    T cal();
}
