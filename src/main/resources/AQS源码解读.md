# AQS源码解读       
## acquire 方法     
1. 当同步状态不允许访问时，若当前线程不在阻塞队列，需要将当前线程放入阻塞队列(严格FIFO)，并阻塞当前线程；
该动作是一个死循环，目的是确保在同步状态不满足的时候，能将未加入队列的当前线程放入队列。        
2. 当同步状态满足访问的时候，需要将当前线程从阻塞队列中移除。        
```
while (synchronization state does not allow acquire) {
	    enqueue current thread if not already queued;
	    possibly block current thread;
	}
	dequeue current thread if it was queued;
```
## release 方法     
1. 更新同步状态，若新的同步状态允许阻塞线程acquire，需要将可操作的阻塞线程解除阻塞。    
```
update synchronization state;
	if (state may permit a blocked thread to acquire)
	    unblock one or more queued threads; 
```
## 核心功能        
1. 同步状态的原子性管理       
2. 线程的阻塞与接触阻塞
3. 队列的管理
### 同步状态     
* AQS类使用单个int（32位）来保存同步状态，并暴露出getState、setState以及compareAndSet操作来读取和更新这个状态      
* compareAndSet的实现：通过本地的compare-and-swap或load-linked/store-condition的指令来实现compareAndSet,使得仅当同步状态
有一个期望值得时候，才会被原子地设置成新值。      
```
protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }
    ? unsafe ?? 
    CountDownLatch
    Fulture
    Task
    Semaphore
    ReentrantLock
    Signal
```
### 线程的阻塞/解除阻塞      
* juc包有一个LockSupport类，它的park() , unpark()方法负责阻塞，解除阻塞线程。      
* LockSupport.park方法，负责阻塞当前线程，除非LockSupport.unpark方法被调用（提前调用也可以）。
* LockSupport.unpark方法，没有调用次数限制，在一个park之前多次调用，只会解除一个park操作。     
* park,unpark方法只作用于线程。      
### 阻塞队列的管理         
* 严格遵循FIFO，是一个链表队列，通过两个字段head tail 来存取。这两个字段是可以原子更新的。在初始化的时候都指向一个空节点。       
* 加入新节点：通过原子操作入队。       
```
do {
	    pred = tail;
	} while(!tail.compareAndSet(pred, node));
或
do {
	    Node p = tail;
	} while(!tail.compareAndSet(p, node));
```
* 每一个节点的 释放 状态都保存在其前驱结点中，因此释放节点操作如下：        
```
while (pred.status != RELEASED){
// spin
}
head = node; //自旋后的操作只需将head指向刚刚得到锁的节点
```
* 在自旋锁中，一个节点只需要改变其状态，下一次自旋中，其后继节点就能注意到这个变化。但是在阻塞式同步器中，一个节点需要显式唤醒(unpark)其后继节点。      
```
if(!tryAcquire(arg)) {
	    node = create and enqueue new node;
	    pred = node's effective predecessor;
	    while (pred is not head node || !tryAcquire(arg)) {
	        if (pred's signal bit is set)
	            pard()
	        else
	            compareAndSet pred's signal bit to true;
	        pred = node's effective predecessor;
	    }
	    head = node;
	}
if(tryRelease(arg) && head node's signal bit is set) {
    compareAndSet head's bit to false;
    unpark head's successor, if one exist
}
```
## AQS的条件队列 ConditionObject     
* AQS框架提供了一个ConditionObject类，给维护独占synchronizer对象以及实现Lock接口的类使用。一个锁对象可以关联任意数据的ConditionObject对象 Lock.newCondition()      
* ConditionObject配合 await  signal  signalAll 使用     
* ConditionObject使用了与同步器一样的内部队列节点，但是，是在一个单独的条件队列中维护这些点的。signal通过将节点从条件队列转移到锁队列来实现。      
```
await()
create and add new node to conditon queue;
	release lock;
	block until node is on lock queue;
	re-acquire lock;
	
signal()
transfer the first node from condition queue to lock queue;
```

      

* 暴露出两个方法，tryAcquire tryRelease , 用来控制 acquire 和 release操作
