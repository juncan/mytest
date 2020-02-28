package com.test.rpc.core;

import java.util.concurrent.*;

/**
 * @author wujc
 * @ClassName RpcThreadPool
 * @Description: Rpc线程池封装
 */
public class RpcThreadPool {
    //独立出线程池主要是为了应对复杂耗I/O操作的业务，不阻塞netty的handler线程而引入的
    //当然如果业务足够简单，把处理逻辑写入netty的handler(ChannelInboundHandlerAdapter)也未尝不可
    public static Executor getExcutor(int threads, int queues) {
        String name = "RpcThreadPool";
        return new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<>()
                        : (queues < 0 ? new LinkedBlockingDeque<>() : new LinkedBlockingDeque<>(queues)),
                new NamedThreadFactory(name, true), new AbortPolicyWithReport(name));
    }
}
