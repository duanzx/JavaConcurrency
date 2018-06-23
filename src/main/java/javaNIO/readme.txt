Stream的读写是单向的，Channel的读写是双向的。数据是从通道读入缓冲区，然后再从缓冲区输出到通道
Buffer缓冲区本质是是一块可以写入数据，然后可以从其中读取数据的内存
Buffer的flip方法，将Buffer从写模式切换到读模式，这样就可以读取之前写入的数据
一旦读取了数据，就需要将数据从缓冲区清除。清除缓冲区数据有两种数据，clear全部清除，compact 只清除已经读取的数据
capacity 容量   position 开始角标 limit 结束角标,读模式 和 写模式 时候，position limit是不一样的。
