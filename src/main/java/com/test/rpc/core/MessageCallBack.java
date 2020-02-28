package com.test.rpc.core;

import com.test.rpc.model.MessageRequest;
import com.test.rpc.model.MessageResponse;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wujc
 * @ClassName MessageCallBack
 * @Description: Rpc消息回调
 */
public class MessageCallBack {
    private MessageRequest request;
    private MessageResponse response;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public MessageCallBack(MessageRequest request) {
        this.request = request;
    }

    public Object start() throws InterruptedException {
        try{
            lock.lock();
            //设定一个超时时间，rpc服务器太久没有响应的话，就默认返回空吧
            finish.await(10 * 1000, TimeUnit.MILLISECONDS);
            if (this.response != null) {
                return this.response.getResultDesc();
            }else{
                return null;
            }
        }finally {
            lock.unlock();
        }
    }

    public void over(MessageResponse response) {
        try{
            lock.lock();
            finish.signal();
            this.response = response;
        }finally {
            lock.unlock();
        }
    }

}
