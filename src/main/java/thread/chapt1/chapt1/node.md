### 线程的生命周期

NEW -> RUNNABLE -> RUNNING -> BLOCKED -> TERMINATED

NEW: 新创建的一个线程，当调用 start 方法的时候，状态会变到 RUNNABLE

RUNNABLE：这个状态只是准备了一个待运行的线程，至于这个线程什么时候运行，是依靠 CPU 的调度的，这个状态只能意外终止(TERMINATED) 或者进入 RUNNING

RUNNING：真正的开始执行， 再这里可以直接进入到意外终止(TERMINATED)，BLOCKED 状态(调用了sleep)，CPU 也会放弃调度，也会到RUNNABLE 状态，

BLOCKED：

TERMINATED:


