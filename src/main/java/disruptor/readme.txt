1.简单介绍一下Disruptor：是一个Java的并发编程框架，大大简化了并发程序开发的难度。在性能上比Java本身提供的一些并发包要好。
能够在无锁的情况下实现网络的QUEUE并发操作

2.Disruptor的核心：RingBuffer是整个模式的核心，同时最重要的是Disruptor对RingBuffer的访问控制策略。
3.什么是RingBuffer：
RingBuffer是一个首尾相接的环，可以用它在不同线程之间传递数据。
RingBuffer没有尾指针
RingBuffer每个区块里都存放着一个序号，这个序号指向下一个可用的元素。随着你不断的填充，这个序号（queue mod array.length）会不断的增长，直到绕过这个环
PS：如果槽的个数是2的N次方，会更有利于基于二进制的计算机进行计算
与队列之间的区别：不会删除RingBuffer里的数据，只会被新的数据覆盖，RingBuffer本身并不控制是否重叠
在可靠消息的传递时有更高的性能：
因为是数组，所以比链表要快，数组内元素的内存地址是连续性存储的，对CPU缓存也好。另外数组中的元素都是预先被加载的。所以CPU不需要时不时去主内存加载数组的下一个
元素
另外可以为数组预先分配内存，是的数组对象一直存在，不需要花费大量的时间进行垃圾回收。另外不像链表一样，需要为每一个添加到其上面的对象创造一一对应的节点。
链表在删除的时候也要执行响应的内存清理工作。
如何从RingBuffer里读取数据



