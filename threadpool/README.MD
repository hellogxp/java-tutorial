## Java Thread Poll

### Introduction

1. What is thread poll
2. Why should we use thread pool    
   The thread pool enables applications to more fully and reasonably coordinate and utilize system resources such as
   CPU, memory, network, and I/O. The creation of threads requires the creation of thread-private memory spaces such as
   virtual machine stacks, native method stacks, and program counters. These system resources need to be reclaimed when
   the thread is destroyed. Frequently creating and destroying threads will waste a lot of system resources and increase
   the risk of concurrent programming. Also, how do you make new threads wait or gracefully deny service when the server
   is under heavy load? These are things that the thread itself cannot resolve. Therefore, it is necessary to coordinate
   multiple threads through the thread pool, and implement tasks such as primary and secondary thread isolation, timing
   execution, and periodic execution. The role of the thread pool includes:
    * Use the thread pool to manage and reuse threads, control the maximum number of concurrency, etc.
    * Implement task thread queue caching strategy and rejection mechanism.
    * Implement some time-related functions, such as timing execution, periodic execution, etc.
    * Isolate the thread environment. For example, if the transaction service and the search service are on the same
      server, and two thread pools are opened respectively, the resource consumption of the transaction thread is
      obviously larger. Therefore, by configuring an independent thread pool, the slower transaction service and the
      search service are isolated. Avoid each service thread affecting each other.

### Executor Frame

### Constructor of ThreadPoolExecutor

```java
    public ThreadPoolExecutor(int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler){
    if(corePoolSize< 0||
    maximumPoolSize<=0||
    maximumPoolSize<corePoolSize ||
    keepAliveTime< 0)
    throw new IllegalArgumentException();
    if(workQueue==null||threadFactory==null||handler==null)
    throw new NullPointerException();
```    

`corePoolSize` indicates the number of resident core threads. If it is equal to 0, after tasks are executed, threads of
the thread pool will be destroyed when no requests enter. If it is greater than 0, the core thread will not be destroyed
even if the local task finishes executing. The setting of this value is very critical. Setting it too large will waste
resources, and setting it too small will cause threads to be created or destroyed frequently.    

`maximumPoolSize` 


### Executors

### The best practice