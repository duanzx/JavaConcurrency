package multiThread.singleThreadExecution;
/**
 * 同一时刻只能有一个线程去访问共享资源，就像独木桥一样每次只允许一个人通过。
 * Single Thread Execution 就是采用排他式的操作保证在同一时刻只能有一个线程访问共享资源
 * 如何保证线程安全：多个线程在对某个类的实例同时进行操作时候，不会引起数据不一致的问题。
 * 要保证访问的共享资源是原子性的
 * 在Single Thread Execution中，synchronized关键字起到了决定性的作用，但是synchronized的排他性是以性能的牺牲为代价的，因此在保证线程安全的前提下应该尽量缩小synchronized的作用域。
 */